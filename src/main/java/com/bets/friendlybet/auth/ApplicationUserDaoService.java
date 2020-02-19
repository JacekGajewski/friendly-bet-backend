package com.bets.friendlybet.auth;

import com.bets.friendlybet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("postgres")
public class ApplicationUserDaoService implements ApplicationUserDAO{

    private final PasswordEncoder PASSWORD_ENCODER;
    private final UserRepository USER_REPOSITORY;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder password_encoder, UserRepository user_repository) {
        PASSWORD_ENCODER = password_encoder;
        USER_REPOSITORY = user_repository;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers()
    {
        List<ApplicationUser> collect = USER_REPOSITORY.findAll().stream()
                .map(user -> new ApplicationUser(
                        user.getAuthorities().stream()
                                .map(authority -> authority.getName().getGrantedAuthorities())
                                .flatMap(Set::stream)
                                .collect(Collectors.toSet()),
                        PASSWORD_ENCODER.encode(user.getPassword()),
                        user.getUsername(),
                        true,
                        true,
                        true,
                        true
                )).
                        collect(Collectors.toList());
        return collect;
    }
}
