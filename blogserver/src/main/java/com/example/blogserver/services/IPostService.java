package com.example.blogserver.services;

import com.example.blogserver.dtos.SubmitPostDto;
import com.example.blogserver.model.Post;

import java.util.List;

public interface IPostService {

    int createPost(SubmitPostDto post);
    List<Post> allPosts();
    List<Post> postsByUser(int userId);
    Post postById(int id);
    int updatePost(SubmitPostDto post, int id);
    int deletePost(int id);
}
