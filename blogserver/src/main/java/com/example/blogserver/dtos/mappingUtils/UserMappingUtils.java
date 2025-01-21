package com.example.blogserver.dtos.mappingUtils;

import com.example.blogserver.dtos.LoginUserDto;
import com.example.blogserver.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMappingUtils {

    public User mapToUser(LoginUserDto loginUserDto) {
        User user = User.builder()
                .username(loginUserDto.getUsername())
                .password(loginUserDto.getPassword())
                .build();
        return user;
    }


}
