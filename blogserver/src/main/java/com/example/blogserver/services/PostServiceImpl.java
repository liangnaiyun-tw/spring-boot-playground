package com.example.blogserver.services;

import com.example.blogserver.dtos.SubmitPostDto;
import com.example.blogserver.model.Post;
import com.example.blogserver.model.User;
import com.example.blogserver.persistence.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PostServiceImpl implements IPostService{

    private final PostMapper postMapper;
    private final UserServiceImpl userService;

    @Override
    public int createPost(SubmitPostDto postDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User currUser = userService.findByUsername(auth.getName()).orElseThrow();
        log.info("Auth get principal: {}", auth.getPrincipal());
        log.info("Auth get name: {}", auth.getName());
        log.info("Current user: {}", currUser);

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(postDto.getCategory());
        post.setTags(postDto.getTags());
        post.setPublicationDate(new Date());
        post.setUserId(currUser.getId());
        return postMapper.createPost(post);
    }

    @Override
    public List<Post> allPosts() {
        return postMapper.allPosts();
    }

    @Override
    public List<Post> postsByUser(int userId) {
        return postMapper.postsByUser(userId);
    }

    @Override
    public Post postById(int id) {
        return postMapper.postById(id);
    }

    @Override
    public int updatePost(SubmitPostDto postDto, int id) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(postDto.getCategory());
        post.setTags(postDto.getTags());
        log.info("Post to update: {}", post);

        return postMapper.updatePost(post);
    }

    @Override
    public int deletePost(int id) {
        return postMapper.deletePost(id);
    }
}
