package com.example.blogserver.controller;

import com.example.blogserver.dtos.LoginUserDto;
import com.example.blogserver.model.User;
import com.example.blogserver.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;


@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final IUserService userService;


    @GetMapping("/me")
    public ResponseEntity<User> authencatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("")
    public CollectionModel<EntityModel<User>> allUsers(){
        List<EntityModel<User>> users = userService.allUsers().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).authencatedUser()).withRel("me")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).allUsers()).withSelfRel());
    }


}

