//package com.bets.friendlybet.repository;
//
//import com.bets.friendlybet.entity.User;
//import com.bets.friendlybet.repository.UserRepository;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class UserRepositoryIntegrationTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Before
//    public void setUp() {
//        User user = new User("johnny", "passwordTest");
//        entityManager.persist(user);
//    }
//
//    @Test
//    public void whenFindUserByName_thenReturnUser() {
//
//        User user = userRepository.findUserByUsername("johnny");
//        assertThat(user.getPassword()).isEqualTo("passwordTest");
//
//        user = userRepository.findUserByUsername("jack");
//        assertThat(user).isNull();
//
//    }
//
//    @Test
//    public void whenFindAll_thenReturnUserList() {
//
//        List<User> founds = userRepository.findAll();
//        assertThat(founds).hasSize(1);
//    }
//
//    @After
//    public void clear() {
//        entityManager.flush();
//    }
//}
