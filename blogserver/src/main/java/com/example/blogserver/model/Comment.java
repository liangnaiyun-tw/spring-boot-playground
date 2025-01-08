package com.example.blogserver.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int id;
    private int postId;
    private int userId;
    private String content;
    private Date createDate;
}
