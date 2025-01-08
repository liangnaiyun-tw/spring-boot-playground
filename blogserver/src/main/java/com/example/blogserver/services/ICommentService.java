package com.example.blogserver.services;

import com.example.blogserver.dtos.SubmitCommentDto;
import com.example.blogserver.model.Comment;

import java.util.List;

public interface ICommentService {
    public void insertComment(SubmitCommentDto submitCommentDto, int postId);
    public void deleteComment(int id);
    public void updateComment(SubmitCommentDto submitCommentDto, int commentId);
    public List<Comment> selectCommentsByPostId(int postId);
    public List<Comment> selectCommentsByUserId(int userId);
    public Comment selectCommentById(int id);
    public List<Comment> selectAllComments();
}
