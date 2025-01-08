package com.example.blogserver.dtos.mappingUtils;

import com.example.blogserver.dtos.SubmitCommentDto;
import com.example.blogserver.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMappingUtils {

    public SubmitCommentDto mapToCommentDto(Comment comment) {
        SubmitCommentDto submitCommentDto = new SubmitCommentDto();
        submitCommentDto.setContent(comment.getContent());

        return submitCommentDto;
    }
}
