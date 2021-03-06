package com.bets.friendlybet.auth;

import com.bets.friendlybet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ApplicationUserDaoService implements ApplicationUserDAO{

    private final UserRepository userRepository;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers()
    {
        return userRepository.findAll().stream()
                .map(user -> new ApplicationUser(
                        user.getAuthorities().stream()
                                .map(authority -> authority.getId().getAuthority().getName().getGrantedAuthorities())
                                .flatMap(Set::stream)
                                .collect(Collectors.toSet()),
                        user.getPassword(),
                        user.getUsername(),
                        true,
                        true,
                        true,
                        true,
                        user.getId())).
                        collect(Collectors.toList());
    }
}
