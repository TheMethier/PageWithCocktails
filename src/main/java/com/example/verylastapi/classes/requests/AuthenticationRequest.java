package com.example.verylastapi.classes.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class AuthenticationRequest {
    @NotBlank(message = "username cannot be blank")
    @NotNull(message = "username cannot be null")
    private String username;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "username cannot be blank")

    private String password;

}
