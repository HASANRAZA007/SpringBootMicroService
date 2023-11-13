package com.sbms.springbootmicroservice.service;

import com.sbms.springbootmicroservice.dto.UserDto;
import com.sbms.springbootmicroservice.exception.CustomServiceException;
import com.sbms.springbootmicroservice.model.User;
import com.sbms.springbootmicroservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private  final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    public UserDtoResponse updateUser(UserDto userDto){
        User user=userRepository.findUserByEmail(userDto.getEmail());
        UserDtoResponse response = new UserDtoResponse();
        if(user!=null){
            user.setId(user.getId());
            user.setEmail(user.getEmail());
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("User updated successfully");
            response.setUserDto(this.modelMapper.map(user, UserDto.class));
        }else {
            response.setSuccess(false);
            response.setMessage("User not found");
        }
        return response;
    }
    public UserDtoResponse getUser(String userName) {
        User user=userRepository.findUserByEmail(userName);
        UserDtoResponse response = new UserDtoResponse();
        if (user!=null){
            response.setSuccess(true);
            response.setMessage("User Get Successfully");
            response.setUserDto(this.modelMapper.map(user, UserDto.class));
        }
        else {
            response.setSuccess(false);
            response.setMessage("User not found");
        }
        return response;
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
    public UserDtoResponse deleteUser(String userName){
        User user=userRepository.findUserByEmail(userName);
        UserDtoResponse response=new UserDtoResponse();
        if(user!=null){
            userRepository.deleteUserByEmail(userName);
            response.setSuccess(true);
            response.setMessage("User Deleted Successfully");
        }
        else {
            response.setSuccess(false);
            response.setMessage("User not found");
        }
        return response;
    }
}
