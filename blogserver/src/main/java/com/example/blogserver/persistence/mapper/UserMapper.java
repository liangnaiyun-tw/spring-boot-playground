package com.example.blogserver.persistence.mapper;
import com.example.blogserver.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    int register(User user);

    <Optional> User findByUsername(String username);

    @Select("SELECT * FROM user;")
    List<User> allUsers();


}
