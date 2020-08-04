package com.bets.friendlybet.repository;

import com.bets.friendlybet.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    String username1 = "username";
    String username2 = "john";
    String username3 = " ";
    String username4 = "BIfa97y319quho79%&$";

    User user1;
    User user2;
    User user3;
    User user4;

    @BeforeEach
    void setUp() {

        String username1 = "username";
        String username2 = "john";
        String username3 = " ";
        String username4 = "BIfa97y319quho79%&$";

        user1 = new User(username1, "password");
        user2 = new User(username2, "LIUBF9p7a");
        user3 = new User(username3, "adminadmin");
        user4 = new User(username4, "gaFFnwrehjp8(YN");

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.persist(user4);
        entityManager.flush();
    }

    @Test
    void findUserByUsername_CorrectUser_Found() {

        Optional<User> userFound1 = userRepository.findUserByUsername(username1);
        Optional<User> userFound2 = userRepository.findUserByUsername(username2);
        Optional<User> userFound3 = userRepository.findUserByUsername(username3);
        Optional<User> userFound4 = userRepository.findUserByUsername(username4);

        assertTrue(userFound1.isPresent());
        assertTrue(userFound2.isPresent());
        assertTrue(userFound3.isPresent());
        assertTrue(userFound4.isPresent());

        assertEquals(user1, userFound1.get());
        assertEquals(user2, userFound2.get());
        assertEquals(user3, userFound3.get());
        assertEquals(user4, userFound4.get());
    }

    @Test
    void findUserByUsername_IncorrectUser_NotFound() {

        String wrongUsername1 = "notPresent";
        String wrongUsername2 = "johnny";
        String wrongUsername3 = "";

        Optional<User> userNotFound1 = userRepository.findUserByUsername(wrongUsername1);
        Optional<User> userNotFound2 = userRepository.findUserByUsername(wrongUsername2);
        Optional<User> userNotFound3 = userRepository.findUserByUsername(wrongUsername3);

        assertFalse(userNotFound1.isPresent());
        assertFalse(userNotFound2.isPresent());
        assertFalse(userNotFound3.isPresent());
    }
}