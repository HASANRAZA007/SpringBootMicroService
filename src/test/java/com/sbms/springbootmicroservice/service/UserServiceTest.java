//package com.sbms.springbootmicroservice.service;
//
//import com.sbms.springbootmicroservice.dto.UserDto;
//import com.sbms.springbootmicroservice.exception.CustomServiceException;
//import com.sbms.springbootmicroservice.model.User;
//import com.sbms.springbootmicroservice.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    @Autowired
//    private  UserService userService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
////    @Test
////    void updateUser() {
////        UserDto updatedUserDto = new UserDto();
////        updatedUserDto.setEmail("TestUser001@gmail.com");
////        updatedUserDto.setName("Amir");
////        updatedUserDto.setPassword("Amir123");
////
////        User existingUser = new User();
////        existingUser.setEmail(updatedUserDto.getEmail());
////        existingUser.setName("Amir");
////        existingUser.setPassword(passwordEncoder.encode("Amir123"));
////
////        when(userRepository.findUserByEmail(updatedUserDto.getEmail())).thenReturn(existingUser);
////        assertThrows(CustomServiceException.class,()->userService.updateUser(updatedUserDto));
////        assertNotNull(existingUser);
////        verify(userRepository).save(existingUser);
////    }
//    @Test
//    void getUser() {
//        String userName = "TestUser001";
//        when(userRepository.findByEmail(userName)).thenReturn(Optional.empty());
//        assertThrows(CustomServiceException.class, () -> userService.getUser(userName));
//    }
//
//    @Test
//    void getAllUsers() {
//        when(userRepository.findAll()).thenReturn(Collections.emptyList());
//        assertThrows(CustomServiceException.class, () -> userService.getAllUsers());
//        verify(userRepository).findAll();
//    }
//    @Test
//    void deleteUser() {
//    }
//}