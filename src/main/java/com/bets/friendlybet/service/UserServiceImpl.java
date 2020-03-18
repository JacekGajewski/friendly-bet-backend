package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.PasswordDTO;
import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.exception.UserNotFoundException;
import com.bets.friendlybet.exception.BadPasswordException;
import com.bets.friendlybet.exception.NotUniqueUsernameException;
import com.bets.friendlybet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bets.friendlybet.security.UserRole.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityService  authorityService;
    private final BetService betService;
    private final UsersAuthoritiesService usersAuthoritiesService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException("User: " + userId + " not found"));
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    @Override
    public void updateUser(UserDTO user) {
        userRepository.save(userDtoToUserEntity(user));
    }

    @Override
    public void changePassword(int userId, PasswordDTO passwordDTO) {
        User user = getUser(userId);
        if (!user.getPassword().equals(passwordDTO.getOldPassword())){
            throw new BadPasswordException("Wrong password");
        }
        user.setPassword(passwordDTO.getNewPassword());
    }

    @Override
    public void changeUsername(int userId, String newUsername) {
        if (getUser(newUsername) != null) {
            throw new NotUniqueUsernameException("Username already exists");
        }
        User user = getUser(userId);
        user.setUsername(newUsername);
    }

    @Override
    public void createUser(UserDTO user) {
        if (getUser(user.getUsername()) != null) {
            throw new NotUniqueUsernameException("Username already exists");
        }
        authorityService.doShit();
        User newUser = userDtoToUserEntity(user);
        authorityService.createAuthority(STUDENT, userRepository.save(newUser));
    }

    @Override
    public void deleteUser(int userId) {
        betService.deleteUserFromBets(userId);
        usersAuthoritiesService.deleteUserAuthorities(userId);
        userRepository.deleteById(userId);
    }

    private User userDtoToUserEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword()
        );
    }

//    private User addAuthorityToUser(User user, UserRole userRole){
//
//        Authority authority = authorityService.createAuthority(userRole);
//        Set<Authority> authorities = new HashSet<>();
//        authorities.add(authority);
//        user.setAuthorities(authorities);
//        return user;
//    }


}
