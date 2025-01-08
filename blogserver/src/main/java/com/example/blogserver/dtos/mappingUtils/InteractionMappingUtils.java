package com.example.blogserver.dtos.mappingUtils;

import com.example.blogserver.dtos.SubmitCommentDto;
import com.example.blogserver.dtos.SubmitInteractionDto;
import com.example.blogserver.model.Interaction;
import org.springframework.stereotype.Component;

@Component
public class InteractionMappingUtils {

    public SubmitInteractionDto mapToInteractionDto(Interaction interaction) {
        SubmitInteractionDto submitInteractionDto = new SubmitInteractionDto();
        submitInteractionDto.setTypeId(interaction.getInteractionType());

        return submitInteractionDto;
    }
}
