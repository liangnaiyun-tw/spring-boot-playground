package com.example.blogserver.services;

import com.example.blogserver.dtos.SubmitCommentDto;
import com.example.blogserver.model.Comment;
import com.example.blogserver.model.User;
import com.example.blogserver.persistence.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements ICommentService{

    private final CommentMapper commentMapper;
    private final UserServiceImpl userService;
    @Override
    public void insertComment(SubmitCommentDto submitCommentDto, int postId) {
        String username = UserServiceImpl.getCurrentUserName();
        User current = userService.findByUsername(username).orElseThrow();

        Comment comment = new Comment();
        comment.setContent(submitCommentDto.getContent());
        comment.setPostId(postId);
        comment.setUserId(current.getId());
        comment.setCreateDate(new Date());

        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentMapper.deleteComment(id);
    }

    @Override
    public void updateComment(SubmitCommentDto submitCommentDto, int commentId) {
        Comment comment = new Comment();
        comment.setContent(submitCommentDto.getContent());
        comment.setId(commentId);
        commentMapper.updateComment(comment);
    }

    @Override
    public List<Comment> selectCommentsByPostId(int postId) {

        return commentMapper.selectCommentsByPostId(postId);
    }

    @Override
    public List<Comment> selectCommentsByUserId(int userId) {
        return commentMapper.selectCommentsByUserId(userId);
    }

    @Override
    public Comment selectCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }

    @Override
    public List<Comment> selectAllComments() {
        return commentMapper.selectAllComments();
    }
}
