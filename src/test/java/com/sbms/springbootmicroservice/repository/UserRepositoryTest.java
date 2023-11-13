//package com.sbms.springbootmicroservice.repository;
//
//import com.sbms.springbootmicroservice.model.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@SpringBootTest
//class UserRepositoryTest {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    @Autowired
//    UserRepositoryTest(UserRepository userRepository,PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder=passwordEncoder;
//    }
//
//    @Test
//    void findUserByUserName() {
//        User user=User.builder().name("Amir").email("TestUser001").password(passwordEncoder.encode("amir123")).build();
//        userRepository.save(user);
//        User actualResult = userRepository.findUserByEmail("TestUser001");
//        assertThat(actualResult.getUsername()).isEqualTo("TestUser001");
//    }
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("tear up");
//    }
//
//    @AfterEach
//    void tearDown() {
//        System.out.println("tear down");
//        userRepository.deleteAll();
//    }
//
//    @Test
//    void  deleteUserByUserName(){
//
//    }
//}