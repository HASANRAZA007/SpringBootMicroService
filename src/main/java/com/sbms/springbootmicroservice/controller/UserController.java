package com.sbms.springbootmicroservice.controller;

import com.sbms.springbootmicroservice.dto.UserDto;
import com.sbms.springbootmicroservice.dto.UserSignIn;
import com.sbms.springbootmicroservice.security.auth.AuthenticationResponse;
import com.sbms.springbootmicroservice.security.auth.AuthenticationService;
import com.sbms.springbootmicroservice.service.UserDtoResponse;
import com.sbms.springbootmicroservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    private  final UserService userService;
    private final AuthenticationService authenticationService;
    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody UserDto request){
        return ResponseEntity.ok(authenticationService.register(request));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logInUser(@Valid @RequestBody UserSignIn request){
        return ResponseEntity.ok(authenticationService.logIn(request));

    }
    @PutMapping("/update")
    public ResponseEntity<UserDtoResponse> updateUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{userName}")
    public ResponseEntity<UserDtoResponse> getUser(@PathVariable String userName){
        return ResponseEntity.ok(userService.getUser(userName));
    }
    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<UserDtoResponse> deleteUser(@PathVariable String userName){
        return ResponseEntity.ok(userService.deleteUser(userName));
    }
}
