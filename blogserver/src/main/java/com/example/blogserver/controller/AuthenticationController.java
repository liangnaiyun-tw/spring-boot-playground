package com.example.blogserver.controller;

import com.example.blogserver.dtos.LoginResponseDto;
import com.example.blogserver.dtos.LoginUserDto;
import com.example.blogserver.dtos.RegisterUserDto;
import com.example.blogserver.model.User;
import com.example.blogserver.services.AuthenticationService;
import com.example.blogserver.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.blogserver.utils.Translator.translate;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto){
        authenticationService.register(registerUserDto);

        return ResponseEntity.ok(translate("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto){
        try {
            log.info("User logging in: {}", loginUserDto);
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String token = jwtService.generateToken(authenticatedUser);

            LoginResponseDto loginResponseDto = new LoginResponseDto().setExpiresIn(jwtService.getExpirationTime()).setToken(token);
            return ResponseEntity.ok(loginResponseDto);
        } finally {
            SecurityContextHolder.clearContext();
        }

    }
}
