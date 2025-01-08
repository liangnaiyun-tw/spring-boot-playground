package com.example.blogserver.model;

import lombok.Data;

import java.util.Date;

@Data
public class Interaction {

    private int id;
    private int userId;
    private int postId;
    private int interactionType;
    private Date createDate;

}
