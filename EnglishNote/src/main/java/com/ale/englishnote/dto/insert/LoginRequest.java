package com.ale.englishnote.dto.insert;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Check user name")
    private String username;
    @NotBlank(message = "Check password")
    private String password;
}
