package com.example.blogserver.services;


import com.example.blogserver.dtos.LoginUserDto;
import com.example.blogserver.dtos.RegisterUserDto;
import com.example.blogserver.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IUserService userService;

    public void register(RegisterUserDto registerUserDto){
        User user = User.builder()
                .username(registerUserDto.getUsername())
                .password(registerUserDto.getPassword())
                .email(registerUserDto.getEmail())
                .build();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setEmail(registerUserDto.getEmail());

        userService.register(user);
    }

    public User authenticate(LoginUserDto loginUserDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getUsername(),
                        loginUserDto.getPassword()
                )
        );

        return userService.findByUsername(loginUserDto.getUsername())
                .orElseThrow();
    }
}
