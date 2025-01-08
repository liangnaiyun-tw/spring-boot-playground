package com.example.blogserver.dtos;

import lombok.Data;

@Data
public class SubmitPostDto {
    private String title;
    private String content;
    private String tags;
    private String category;
}
