package com.example.blogserver.controller;

import com.example.blogserver.dtos.SubmitCommentDto;
import com.example.blogserver.dtos.mappingUtils.CommentMappingUtils;
import com.example.blogserver.model.Comment;
import com.example.blogserver.services.ICommentService;
import com.example.blogserver.utils.Translator;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final ICommentService commentService;
    private final CommentMappingUtils commentMappingUtils;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addComment(@RequestBody SubmitCommentDto submitCommentDto, @PathVariable int postId) {
        commentService.insertComment(submitCommentDto, postId);

        return ResponseEntity.ok(translate("Comment added successfully"));

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);

        return ResponseEntity.ok(translate("Comment deleted successfully"));
    }

    @PostMapping("/comments/{id}")
    public ResponseEntity<String> updateComment(@RequestBody SubmitCommentDto submitCommentDto, @PathVariable int id) {
        commentService.updateComment(submitCommentDto, id);

        return ResponseEntity.ok(translate("Comment updated successfully"));

    }

    @GetMapping("/posts/{postId}/comments")
    public CollectionModel<EntityModel<Comment>> getCommentsByPostId(@PathVariable int postId) {
        List<EntityModel<Comment>> comments = commentService.selectCommentsByPostId(postId).stream()
            .map(comment -> EntityModel.of(comment,
                    linkTo(methodOn(CommentController.class).addComment(commentMappingUtils.mapToCommentDto(comment), comment.getPostId())).withRel("addComment"),
                    linkTo(methodOn(CommentController.class).deleteComment(comment.getId())).withRel("deleteComment"),
                    linkTo(methodOn(CommentController.class).updateComment(commentMappingUtils.mapToCommentDto(comment), comment.getId())).withRel("updateComment"),
                    linkTo(methodOn(CommentController.class).getCommentsByUserId(comment.getUserId())).withSelfRel(),
                    linkTo(methodOn(CommentController.class).allComments()).withRel("allComments"),
                    linkTo(methodOn(CommentController.class).getCommentById(comment.getId())).withRel("getCommentById")
            ))
            .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(CommentController.class).getCommentsByPostId(postId)).withSelfRel());
    }


    @GetMapping("/comments/users/{userId}")
    public CollectionModel<EntityModel<Comment>> getCommentsByUserId(@PathVariable int userId) {
        List<EntityModel<Comment>> comments = commentService.selectCommentsByUserId(userId).stream()
                .map(comment -> EntityModel.of(comment,
                        linkTo(methodOn(CommentController.class).addComment(commentMappingUtils.mapToCommentDto(comment), comment.getPostId())).withRel("addComment"),
                        linkTo(methodOn(CommentController.class).deleteComment(comment.getId())).withRel("deleteComment"),
                        linkTo(methodOn(CommentController.class).updateComment(commentMappingUtils.mapToCommentDto(comment), comment.getId())).withRel("updateComment"),
                        linkTo(methodOn(CommentController.class).getCommentsByPostId(comment.getPostId())).withRel("getCommentsByPostId"),
                        linkTo(methodOn(CommentController.class).allComments()).withRel("allComments"),
                        linkTo(methodOn(CommentController.class).getCommentById(comment.getId())).withRel("getCommentById")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(CommentController.class).getCommentsByUserId(userId)).withSelfRel());
    }


    @GetMapping("/comments/{id}")
    public EntityModel<Comment> getCommentById(@PathVariable int id) {
        Comment comment = commentService.selectCommentById(id);

        return EntityModel.of(comment,
                linkTo(methodOn(CommentController.class).addComment(commentMappingUtils.mapToCommentDto(comment), comment.getPostId())).withRel("addComment"),
                linkTo(methodOn(CommentController.class).deleteComment(comment.getId())).withRel("deleteComment"),
                linkTo(methodOn(CommentController.class).updateComment(commentMappingUtils.mapToCommentDto(comment), comment.getId())).withRel("updateComment"),
                linkTo(methodOn(CommentController.class).getCommentsByPostId(comment.getPostId())).withRel("getCommentsByPostId"),
                linkTo(methodOn(CommentController.class).getCommentsByUserId(comment.getUserId())).withRel("getCommentsByUserId"),
                linkTo(methodOn(CommentController.class).allComments()).withRel("allComments"),
                linkTo(methodOn(CommentController.class).getCommentById(comment.getId())).withSelfRel()
        );
    }

    @GetMapping("/comments")
    public CollectionModel<EntityModel<Comment>> allComments() {
        List<EntityModel<Comment>> comments = commentService.selectAllComments().stream()
            .map(comment -> EntityModel.of(comment,
                linkTo(methodOn(CommentController.class).addComment(commentMappingUtils.mapToCommentDto(comment), comment.getPostId())).withRel("addComment"),
                linkTo(methodOn(CommentController.class).deleteComment(comment.getId())).withRel("deleteComment"),
                linkTo(methodOn(CommentController.class).updateComment(commentMappingUtils.mapToCommentDto(comment), comment.getId())).withRel("updateComment"),
                linkTo(methodOn(CommentController.class).getCommentsByPostId(comment.getPostId())).withRel("getCommentsByPostId"),
                linkTo(methodOn(CommentController.class).getCommentsByUserId(comment.getUserId())).withRel("getCommentsByUserId"),
                linkTo(methodOn(CommentController.class).getCommentById(comment.getId())).withRel("getCommentById")
            ))
            .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(CommentController.class).allComments()).withSelfRel());
    }
}
