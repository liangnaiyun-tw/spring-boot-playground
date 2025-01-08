package com.example.blogserver.model;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private int id;
    private int userId;
    private String title;
    private String content;
    private String category;
    private Date publicationDate;
    private String tags;

}
