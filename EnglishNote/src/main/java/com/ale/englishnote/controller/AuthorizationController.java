package com.ale.englishnote.controller;

import com.ale.englishnote.dto.insert.LoginRequest;
import com.ale.englishnote.dto.view.LoginResponse;
import com.ale.englishnote.security.JwtTokenProvider;
import com.ale.englishnote.security.UserInfo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthorizationController {
    AuthenticationProvider authenticationProvider;
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/authorize")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new ResponseEntity(new LoginResponse(loginRequest.getUsername(), jwt), HttpStatus.OK);
    }
}
