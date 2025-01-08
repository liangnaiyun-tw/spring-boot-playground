package com.example.blogserver.persistence.mapper;

import com.example.blogserver.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comments")
    public List<Comment> selectAllComments();
    public void insertComment(Comment comment);
    public void deleteComment(int id);
    public void updateComment(Comment comment);
    public List<Comment> selectCommentsByPostId(int postId);
    public List<Comment> selectCommentsByUserId(int userId);
    public Comment selectCommentById(int id);
}
