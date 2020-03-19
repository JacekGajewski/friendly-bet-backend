package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.PasswordDTO;
import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.dto.UserResponseDTO;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.exception.UserNotFoundException;
import com.bets.friendlybet.exception.BadPasswordException;
import com.bets.friendlybet.exception.NotUniqueUsernameException;
import com.bets.friendlybet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    private final UserMapper userMapper;

    @Override
    public User getUserEntity(int userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException("User: " + userId + " not found"));
    }

    @Override
    public User getUserEntity(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    @Override
    public UserResponseDTO getUser(int userId) {
        User user = getUserEntity(userId);
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDTO getUser(String username) {
        User user = userRepository.findUserByUsername(username).orElse(null);
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersListToUsersResponseDtoList(users);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User user = userMapper.userDtoToUserEntity(userDTO);
        userRepository.save(user);
    }

    @Override
    public void changePassword(int userId, PasswordDTO passwordDTO) {
        User user = getUserEntity(userId);
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
        User user = getUserEntity(userId);
        user.setUsername(newUsername);
    }

    @Override
    public void createUser(UserDTO userDTO) {
        if (getUser(userDTO.getUsername()) != null) {
            throw new NotUniqueUsernameException("Username already exists");
        }
//        authorityService.initAuthorities();
        User user = userMapper.userDtoToUserEntity(userDTO);
        User userSaved = userRepository.save(user);
        authorityService.createAuthority(STUDENT, userSaved);
    }

    @Override
    public void deleteUser(int userId) {
        betService.deleteUserFromBets(userId);
        usersAuthoritiesService.deleteUserAuthorities(userId);
        userRepository.deleteById(userId);
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
