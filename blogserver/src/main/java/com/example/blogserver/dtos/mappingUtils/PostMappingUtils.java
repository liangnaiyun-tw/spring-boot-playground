package com.example.blogserver.dtos.mappingUtils;

import com.example.blogserver.dtos.SubmitPostDto;
import com.example.blogserver.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMappingUtils {

    public SubmitPostDto mapToPostDto(Post post) {
        SubmitPostDto submitPostDto = new SubmitPostDto();
        submitPostDto.setTitle(post.getTitle());
        submitPostDto.setContent(post.getContent());
        submitPostDto.setCategory(post.getCategory());
        submitPostDto.setTags(post.getTags());
        return submitPostDto;
    }
}
