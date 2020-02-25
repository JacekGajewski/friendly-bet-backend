package com.bets.friendlybet.service;

import com.bets.friendlybet.entity.User;
import com.bets.friendlybet.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    public void test() {

//        UserServiceImpl userService = new UserServiceImpl(userRepository);
//        User user = new User("dupa", "dupa");
//        String
    }
}
