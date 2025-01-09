package com.example.blogserver.controller;

import com.example.blogserver.dtos.SubmitPostDto;
import com.example.blogserver.dtos.mappingUtils.PostMappingUtils;
import com.example.blogserver.model.Post;
import com.example.blogserver.services.IPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static com.example.blogserver.utils.Translator.translate;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class PostController {

    private final IPostService postService;
    private final PostMappingUtils postMappingUtils;

    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody SubmitPostDto post) {
        postService.createPost(post);

        return ResponseEntity.ok(translate("Post created successfully"));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updatePost(@RequestBody SubmitPostDto post, @PathVariable int id) {
        postService.updatePost(post, id);


        return ResponseEntity.ok(translate("Post updated successfully"));
    }

    @GetMapping("")
    public CollectionModel<EntityModel<Post>> allPosts() {
        List<EntityModel<Post>> posts = postService.allPosts().stream()
                .map(post -> EntityModel.of(post,
                        linkTo(methodOn(PostController.class).createPost(postMappingUtils.mapToPostDto(post))).withRel("createPost"),
                        linkTo(methodOn(PostController.class).deletePost(post.getId())).withRel("deletePost"),
                        linkTo(methodOn(PostController.class).getPostById(post.getId())).withRel("getPostById"),
                        linkTo(methodOn(PostController.class).postsByUser(post.getUserId())).withRel("postsByUser"),
                        linkTo(methodOn(PostController.class).updatePost(postMappingUtils.mapToPostDto(post), post.getId())).withRel("updatePost")
                        ))
                .collect(Collectors.toList());

        return CollectionModel.of(posts, linkTo(methodOn(PostController.class).allPosts()).withSelfRel());
    }

    @GetMapping("/userId")
    public CollectionModel<EntityModel<Post>> postsByUser(@RequestParam int userId) {
        List<EntityModel<Post>> posts = postService.postsByUser(userId).stream()
                .map(post -> EntityModel.of(post,
                        linkTo(methodOn(PostController.class).createPost(postMappingUtils.mapToPostDto(post))).withRel("createPost"),
                        linkTo(methodOn(PostController.class).deletePost(post.getId())).withRel("deletePost"),
                        linkTo(methodOn(PostController.class).getPostById(post.getId())).withRel("getPostById"),
                        linkTo(methodOn(PostController.class).allPosts()).withRel("allPosts"),
                        linkTo(methodOn(PostController.class).updatePost(postMappingUtils.mapToPostDto(post), post.getId())).withRel("updatePost")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(posts, linkTo(methodOn(PostController.class).postsByUser(userId)).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Post> getPostById(@PathVariable int id) {
        Post post = postService.postById(id);

        return EntityModel.of(post,
                linkTo(methodOn(PostController.class).createPost(postMappingUtils.mapToPostDto(post))).withRel("createPost"),
                linkTo(methodOn(PostController.class).deletePost(post.getId())).withRel("deletePost"),
                linkTo(methodOn(PostController.class).postsByUser(post.getUserId())).withRel("postsByUser"),
                linkTo(methodOn(PostController.class).allPosts()).withRel("allPosts"),
                linkTo(methodOn(PostController.class).updatePost(postMappingUtils.mapToPostDto(post), post.getId())).withRel("updatePost")
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        postService.deletePost(id);

        return ResponseEntity.ok(translate("Post deleted successfully"));
    }


}
