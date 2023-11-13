package com.sbms.springbootmicroservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "Name can not be empty")
    private String name;
    @Email(message = "Enter a valid email")
    private String email;
    @NotNull
    @Size(min = 6, max = 12, message = "Password must be at least 6 character and maximum 12 character.")
   private String password;

}
