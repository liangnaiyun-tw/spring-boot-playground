package com.example.blogserver.services;

import com.example.blogserver.dtos.SubmitInteractionDto;
import com.example.blogserver.model.Interaction;

import java.util.List;


public interface IInteractionService {
    public void insertInteraction(SubmitInteractionDto submitInteractionDto, int postId);
    public void deleteInteraction(int id);
    public void updateInteraction(SubmitInteractionDto submitInteractionDto, int interactionId);
    public List<Interaction> selectInteractionsByPostId(int postId);
    public List<Interaction> selectInteractionsByUserId(int userId);
    public Interaction selectInteractionById(int id);
    public List<Interaction> selectAllInteractions();
    public List<Interaction> selectInteractionsByType(int type);
    public List<Interaction> selectInteractionByPostIdAndType(int postId, int type);
}
