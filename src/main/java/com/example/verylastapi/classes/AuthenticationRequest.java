package com.example.verylastapi.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
public class AuthenticationRequest {
    private String username;
     String password;
}
