package com.example.ProdTrack.DTO;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
    private String role;
}
