package com.example.verylastapi.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private  String username;
    private String password;
    private  String email;
    private String phoneNumber;
}
