package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.entity.Authority;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.UserRepository;
import com.bets.friendlybet.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.bets.friendlybet.security.UserRole.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthorityService  authorityService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void updateUser(UserDTO user) {
        userRepository.save(userDtoToUserEntity(user));
    }

    @Override
    @Transactional
    public void createUser(UserDTO user) {
        User newUser = userDtoToUserEntity(user);
        authorityService.createAuthority(STUDENT, userRepository.save(newUser));
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public int getUserId(String username) {
        return userRepository.findUserByUsername(username).getId();
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
