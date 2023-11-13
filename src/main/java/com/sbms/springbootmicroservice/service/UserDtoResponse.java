package com.sbms.springbootmicroservice.service;

import com.sbms.springbootmicroservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoResponse {
    private boolean success;
    private String message;
    private UserDto userDto;
}
