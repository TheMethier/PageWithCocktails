package com.example.verylastapi.classes.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.annotation.RegEx;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "username cannot be blank")
    @NotNull(message = "username cannot be null")
    private  String username;
    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    private String password;
    @NotBlank(message = "email cannot be blank")
    @Email(message = "email not valid")
    private  String email;
    @NotBlank(message = "phone number cannot be null")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "number not valid")
    private String phoneNumber;
}
