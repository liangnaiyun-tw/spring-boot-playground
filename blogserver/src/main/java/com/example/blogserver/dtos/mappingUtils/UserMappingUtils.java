package com.example.blogserver.dtos.mappingUtils;

import com.example.blogserver.dtos.LoginUserDto;
import com.example.blogserver.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMappingUtils {

    public User mapToUser(LoginUserDto loginUserDto) {
        User user = new User();
        user.setUsername(loginUserDto.getUsername());
        user.setPassword(loginUserDto.getPassword());
        return user;
    }


}
