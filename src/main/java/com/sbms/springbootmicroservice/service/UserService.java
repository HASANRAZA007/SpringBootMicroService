package com.sbms.springbootmicroservice.service;

import com.sbms.springbootmicroservice.dto.UserDto;
import com.sbms.springbootmicroservice.exception.CustomServiceException;
import com.sbms.springbootmicroservice.model.User;
import com.sbms.springbootmicroservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private  final ModelMapper modelMapper;
    private  final UserRepository userRepository;
    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }
    public UserDto updateUser(UserDto userDto){
        User user=userRepository.findUserByUserName(userDto.getUserName());
        if(user!=null){
            user.setId(user.getId());
            user.setUserName(userDto.getUserName());
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            return this.modelMapper.map(user,UserDto.class);
        }else {
            throw new CustomServiceException(404,"User Not Exist");
        }
    }
    public UserDto getUser(String userName) {
        Optional<User> user=userRepository.findByUserName(userName);
        if (user.isPresent()){
            return this.modelMapper.map(user, UserDto.class);
        }
        else {
            throw new CustomServiceException(404,"User Not Found");
        }
    }
    public List<UserDto> getAllUsers(){
        List<User> userList=userRepository.findAll();
        List<UserDto> userDtoList;
        userDtoList=userList.stream().map(user -> modelMapper.map(user,UserDto.class))
                .toList();
        if (userDtoList.isEmpty()){
            throw new CustomServiceException(404,"List is empty");
        }else {
            return userDtoList;
        }
    }
}
