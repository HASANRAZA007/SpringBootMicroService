package com.sbms.springbootmicroservice.security.auth;

import com.sbms.springbootmicroservice.dto.UserDto;
import com.sbms.springbootmicroservice.dto.UserSignIn;
import com.sbms.springbootmicroservice.exception.CustomServiceException;
import com.sbms.springbootmicroservice.model.User;
import com.sbms.springbootmicroservice.repository.UserRepository;
import com.sbms.springbootmicroservice.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public AuthenticationService(ModelMapper modelMapper,UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper=modelMapper;
    }

    public AuthenticationResponse register(UserDto request) {
        Optional<User> user=userRepository.findByEmail(request.getEmail());
        if(user.isPresent()){
            throw new CustomServiceException(200,"User is already Exist");
        } else if(request.getEmail().isEmpty() || request.getName().isEmpty()
                || request.getPassword().isEmpty()) {
            throw new CustomServiceException(400, "Some Data is Missing");
        }
        User user1 = modelMapper.map(request, User.class);
        user1.setPassword(passwordEncoder.encode(request.getPassword()));
        user1.setEmail(request.getEmail());
        user1.setName(request.getName());
        User savedUser = userRepository.save(user1);
        String token = jwtService.generateToken(savedUser);
        UserDto userDTO = modelMapper.map(savedUser,UserDto.class);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse logIn(UserSignIn request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
