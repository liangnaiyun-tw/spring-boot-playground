package com.example.blogserver.services;

import com.example.blogserver.dtos.SubmitInteractionDto;
import com.example.blogserver.model.Interaction;
import com.example.blogserver.model.User;
import com.example.blogserver.persistence.mapper.InteractionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InteractionServiceImpl implements IInteractionService{

    private final InteractionMapper interactionMapper;
    private final UserServiceImpl userService;
    @Override
    public void insertInteraction(SubmitInteractionDto submitInteractionDto, int postId) {
        String username = userService.getCurrentUserName();
        User currentUser = userService.findByUsername(username).orElseThrow();
        Interaction interaction = new Interaction();
        interaction.setPostId(postId);
        interaction.setUserId(currentUser.getId());
        interaction.setInteractionType(submitInteractionDto.getTypeId());
        interaction.setCreateDate(new Date());

        interactionMapper.insertInteraction(interaction);

    }

    @Override
    public void deleteInteraction(int id) {
        interactionMapper.deleteInteraction(id);
    }

    @Override
    public void updateInteraction(SubmitInteractionDto submitInteractionDto, int interactionId) {
        Interaction interaction = new Interaction();
        interaction.setInteractionType(submitInteractionDto.getTypeId());
        interaction.setId(interactionId);
        interactionMapper.updateInteraction(interaction);
    }

    @Override
    public List<Interaction> selectInteractionsByPostId(int postId) {
        return interactionMapper.selectInteractionsByPostId(postId);
    }

    @Override
    public List<Interaction> selectInteractionsByUserId(int userId) {
        return interactionMapper.selectInteractionsByUserId(userId);
    }

    @Override
    public Interaction selectInteractionById(int id) {
        return interactionMapper.selectInteractionById(id);
    }

    @Override
    public List<Interaction> selectAllInteractions() {
        return interactionMapper.selectAllInteractions();
    }

    @Override
    public List<Interaction> selectInteractionsByType(int type) {
        return interactionMapper.selectInteractionsByType(type);
    }

    @Override
    public List<Interaction> selectInteractionByPostIdAndType(int postId, int type) {
        return interactionMapper.selectInteractionByPostIdAndType(postId, type);
    }
}
