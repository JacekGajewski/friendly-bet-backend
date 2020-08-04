package com.bets.friendlybet.service;

import com.bets.friendlybet.dto.UserDTO;
import com.bets.friendlybet.dto.UserResponseDTO;
import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.exception.UserNotFoundException;
import com.bets.friendlybet.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AuthorityService authorityService;

    @Mock
    UserMapper userMapper;

    @Mock
    BetMapper betMapper;

    UserService userService;

    Optional<User> optionalUser1;
    Optional<User> optionalUser2;
    Optional<User> optionalUser3;
    Optional<User> optionalUser4;

    String username1;
    String username2;
    String username3;
    String username4;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, authorityService, userMapper, betMapper);

        username1 = "username";
        username2 = "john";
        username3 = " ";
        username4 = "BIfa97y319quho79%&$";

        optionalUser1 = Optional.of(new User(1, username1, "password"));
        optionalUser2 = Optional.of(new User(2, username2, "LIUBF9p7a"));
        optionalUser3 = Optional.of(new User(30, username3, "adminadmin"));
        optionalUser4 = Optional.of(new User(11241, username4, "gaFFnwrehjp8(YN"));
    }

    @Test
    void getUserEntity_CorrectUserId_Found() {

        doReturn(optionalUser1).when(userRepository).findById(1);
        doReturn(optionalUser2).when(userRepository).findById(2);
        doReturn(optionalUser3).when(userRepository).findById(30);
        doReturn(optionalUser4).when(userRepository).findById(11241);

        User userFound1 = userService.getUserEntity(1);
        User userFound2 = userService.getUserEntity(2);
        User userFound3 = userService.getUserEntity(30);
        User userFound4 = userService.getUserEntity(11241);

        assertTrue(optionalUser1.isPresent());
        assertTrue(optionalUser2.isPresent());
        assertTrue(optionalUser3.isPresent());
        assertTrue(optionalUser4.isPresent());

        assertEquals(optionalUser1.get(), userFound1);
        assertEquals(optionalUser2.get(), userFound2);
        assertEquals(optionalUser3.get(), userFound3);
        assertEquals(optionalUser4.get(), userFound4);

        assertNotEquals(optionalUser1.get(), userFound2);
        assertNotEquals(optionalUser1.get(), userFound3);
        assertNotEquals(optionalUser1.get(), userFound4);
        assertNotEquals(optionalUser2.get(), userFound4);
    }

    @Test
    void getUserEntity_IncorrectUserId_ExceptionThrown() {

        int wrongId1 = 0;
        int wrongId2 = 12345678;
        int wrongId3 = -1;

        doThrow(new UserNotFoundException("User: " + wrongId1 + " not found")).when(userRepository).findById(wrongId1);
        doThrow(new UserNotFoundException("User: " + wrongId2 + " not found")).when(userRepository).findById(wrongId2);
        doThrow(new UserNotFoundException("User: " + wrongId3 + " not found")).when(userRepository).findById(wrongId3);

        UserNotFoundException userNotFoundException1 = assertThrows(UserNotFoundException.class, () -> userService.getUserEntity(wrongId1));
        UserNotFoundException userNotFoundException2 = assertThrows(UserNotFoundException.class, () -> userService.getUserEntity(wrongId2));
        UserNotFoundException userNotFoundException3 = assertThrows(UserNotFoundException.class, () -> userService.getUserEntity(wrongId3));

        String expectedMessage1 = "User: " + wrongId1+ " not found";
        String expectedMessage2 = "User: " + wrongId2+ " not found";
        String expectedMessage3 = "User: " + wrongId3+ " not found";

        String actualMessage1 = userNotFoundException1.getMessage();
        String actualMessage2 = userNotFoundException2.getMessage();
        String actualMessage3 = userNotFoundException3.getMessage();

        assertEquals(expectedMessage1, actualMessage1);
        assertEquals(expectedMessage2, actualMessage2);
        assertEquals(expectedMessage3, actualMessage3);

        assertNotEquals(expectedMessage3, actualMessage1);
        assertNotEquals(expectedMessage2, actualMessage3);
        assertNotEquals(expectedMessage2, actualMessage1);
    }

    @Test
    void getUserEntity_CorrectUsername_Found() {

        doReturn(optionalUser1).when(userRepository).findUserByUsername(username1);
        doReturn(optionalUser2).when(userRepository).findUserByUsername(username2);
        doReturn(optionalUser3).when(userRepository).findUserByUsername(username3);
        doReturn(optionalUser4).when(userRepository).findUserByUsername(username4);

        User userFound1 = userService.getUserEntity(username1);
        User userFound2 = userService.getUserEntity(username2);
        User userFound3 = userService.getUserEntity(username3);
        User userFound4 = userService.getUserEntity(username4);

        assertTrue(optionalUser1.isPresent());
        assertTrue(optionalUser2.isPresent());
        assertTrue(optionalUser3.isPresent());
        assertTrue(optionalUser4.isPresent());

        assertEquals(optionalUser1.get(), userFound1);
        assertEquals(optionalUser2.get(), userFound2);
        assertEquals(optionalUser3.get(), userFound3);
        assertEquals(optionalUser4.get(), userFound4);

        assertEquals(optionalUser1.get().getUsername(), userFound1.getUsername());
        assertEquals(optionalUser2.get().getId(), userFound2.getId());
        assertEquals(optionalUser3.get().getPassword(), userFound3.getPassword());

        assertNotEquals(optionalUser1.get(), userFound2);
        assertNotEquals(optionalUser1.get(), userFound3);
        assertNotEquals(optionalUser1.get(), userFound4);
        assertNotEquals(optionalUser2.get(), userFound4);

    }

    @Test
    void getUserEntity_IncorrectUsername_NullReturned() {

        String wrongUsername1 = "notPresent";
        String wrongUsername2 = "johnny";
        String wrongUsername3 = "";

        Optional<User> emptyOptional = Optional.empty();

        doReturn(emptyOptional).when(userRepository).findUserByUsername(wrongUsername1);
        doReturn(emptyOptional).when(userRepository).findUserByUsername(wrongUsername2);
        doReturn(emptyOptional).when(userRepository).findUserByUsername(wrongUsername3);

        assertNull(userService.getUserEntity(wrongUsername1));
        assertNull(userService.getUserEntity(wrongUsername2));
        assertNull(userService.getUserEntity(wrongUsername3));

    }

    @Test
    void getAllUsers() {

        User user1 = optionalUser1.get();
        User user2 = optionalUser2.get();
        User user3 = optionalUser3.get();
        List<User> listOfAllUsers = Arrays.asList(
                user1,
                user2,
                user3);

        List<UserResponseDTO> userDTOList = Arrays.asList(
                new UserResponseDTO(user1.getId(), user1.getUsername()),
                new UserResponseDTO(user2.getId(), user2.getUsername()),
                new UserResponseDTO(user3.getId(), user3.getUsername())
        );

        doReturn(listOfAllUsers).when(userRepository).findAll();
        doReturn(userDTOList).when(userMapper).usersListToUsersResponseDtoList(listOfAllUsers);

        List<UserResponseDTO> allUsersFound = userService.getAllUsers();

        assertEquals(userDTOList, allUsersFound);
        assertEquals(userDTOList.size(), allUsersFound.size());
        assertEquals(user1.getUsername(), allUsersFound.get(0).getUsername());

        assertNotEquals(user2.getUsername(), allUsersFound.get(0).getUsername());
        assertNotEquals(user3.getId().intValue(), allUsersFound.get(0).getUserId());

    }
    @Test
    void getAllUserBets() {

    }

    @Test
    void getBetsByStatus() {
    }
}