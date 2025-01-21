package com.example.blogserver.controller;

import com.example.blogserver.dtos.SubmitInteractionDto;
import com.example.blogserver.dtos.mappingUtils.InteractionMappingUtils;
import com.example.blogserver.model.Interaction;
import com.example.blogserver.services.IInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.blogserver.utils.Translator.translate;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InteractionController {

    private final IInteractionService interactionService;
    private final InteractionMappingUtils interactionMappingUtils;


    @PostMapping("/posts/{postId}/interactions")
    public ResponseEntity<String> addInteraction(@RequestBody SubmitInteractionDto submitInteractionDto, @PathVariable int postId) {
        interactionService.insertInteraction(submitInteractionDto, postId);

        return ResponseEntity.ok(translate("Interaction added successfully"));

    }

    @DeleteMapping("/interactions/{id}")
    public ResponseEntity<String> deleteInteraction(@PathVariable int id) {
        interactionService.deleteInteraction(id);

        return ResponseEntity.ok(translate("Interaction deleted successfully"));
    }

    @PostMapping("/interactions/{id}")
    public ResponseEntity<String> updateInteraction(@RequestBody SubmitInteractionDto submitInteractionDto, @PathVariable int id) {
        interactionService.updateInteraction(submitInteractionDto, id);

        return ResponseEntity.ok(translate("Interaction updated successfully"));

    }

    @GetMapping("/posts/{postId}/interactions")
    public CollectionModel<EntityModel<Interaction>> getInteractionsByPostId(@PathVariable int postId) {
        List<EntityModel<Interaction>> interactions = interactionService.selectInteractionsByPostId(postId).stream()
                .map(interaction -> EntityModel.of(interaction,
                        linkTo(methodOn(InteractionController.class).addInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getPostId())).withRel("addInteraction"),
                        linkTo(methodOn(InteractionController.class).deleteInteraction(interaction.getId())).withRel("deleteInteraction"),
                        linkTo(methodOn(InteractionController.class).updateInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getId())).withRel("updateInteraction"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByUserId(interaction.getUserId())).withRel("getInteractionsByUserId"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByType(interaction.getInteractionType())).withRel("getInteractionsByType"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostIdAndType(interaction.getPostId(), interaction.getInteractionType())).withRel("getInteractionsByPostIdAndType"),
                        linkTo(methodOn(InteractionController.class).getAllInteractions()).withRel("allInteractions"),
                        linkTo(methodOn(InteractionController.class).getInteractionById(interaction.getId())).withRel("getInteractionById")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(interactions, linkTo(methodOn(InteractionController.class).getInteractionsByPostId(postId)).withSelfRel());
    }

    @GetMapping("/interactions")
    public CollectionModel<EntityModel<Interaction>> getAllInteractions() {
        List<EntityModel<Interaction>> interactions = interactionService.selectAllInteractions().stream()
                .map(interaction -> EntityModel.of(interaction,
                        linkTo(methodOn(InteractionController.class).addInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getPostId())).withRel("addInteraction"),
                        linkTo(methodOn(InteractionController.class).deleteInteraction(interaction.getId())).withRel("deleteInteraction"),
                        linkTo(methodOn(InteractionController.class).updateInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getId())).withRel("updateInteraction"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByUserId(interaction.getUserId())).withRel("getInteractionsByUserId"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByType(interaction.getInteractionType())).withRel("getInteractionsByType"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostIdAndType(interaction.getPostId(), interaction.getInteractionType())).withRel("getInteractionsByPostIdAndType"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostId(interaction.getPostId())).withRel("getInteractionsByPostId"),
                        linkTo(methodOn(InteractionController.class).getInteractionById(interaction.getId())).withRel("getInteractionById")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(interactions, linkTo(methodOn(InteractionController.class).getAllInteractions()).withSelfRel());
    }

    @GetMapping("/interactions/{id}")
    public EntityModel<Interaction> getInteractionById(@PathVariable int id) {
        Interaction interaction = interactionService.selectInteractionById(id);

        return EntityModel.of(interaction,
                linkTo(methodOn(InteractionController.class).addInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getPostId())).withRel("addInteraction"),
                linkTo(methodOn(InteractionController.class).deleteInteraction(interaction.getId())).withRel("deleteInteraction"),
                linkTo(methodOn(InteractionController.class).updateInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getId())).withRel("updateInteraction"),
                linkTo(methodOn(InteractionController.class).getInteractionsByUserId(interaction.getUserId())).withRel("getInteractionsByUserId"),
                linkTo(methodOn(InteractionController.class).getInteractionsByType(interaction.getInteractionType())).withRel("getInteractionsByType"),
                linkTo(methodOn(InteractionController.class).getInteractionsByPostIdAndType(interaction.getPostId(), interaction.getInteractionType())).withRel("getInteractionsByPostIdAndType"),
                linkTo(methodOn(InteractionController.class).getInteractionsByPostId(interaction.getPostId())).withRel("getInteractionsByPostId"),
                linkTo(methodOn(InteractionController.class).getAllInteractions()).withRel("getAllInteractions"),
                linkTo(methodOn(InteractionController.class).getInteractionById(id)).withSelfRel()
        );
    }

    @GetMapping("/posts/{postId}/interactions/{type}")
    public CollectionModel<EntityModel<Interaction>> getInteractionsByPostIdAndType(@PathVariable int postId, @PathVariable int type) {
        List<EntityModel<Interaction>> interactions = interactionService.selectInteractionByPostIdAndType(postId, type).stream()
                .map(interaction -> EntityModel.of(interaction,
                        linkTo(methodOn(InteractionController.class).addInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getPostId())).withRel("addInteraction"),
                        linkTo(methodOn(InteractionController.class).deleteInteraction(interaction.getId())).withRel("deleteInteraction"),
                        linkTo(methodOn(InteractionController.class).updateInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getId())).withRel("updateInteraction"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByUserId(interaction.getUserId())).withRel("getInteractionsByUserId"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByType(interaction.getInteractionType())).withRel("getInteractionsByType"),
                        linkTo(methodOn(InteractionController.class).getInteractionById(interaction.getId())).withRel("getInteractionById"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostId(interaction.getPostId())).withRel("getInteractionsByPostId"),
                        linkTo(methodOn(InteractionController.class).getAllInteractions()).withRel("getAllInteractions")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(interactions, linkTo(methodOn(InteractionController.class).getInteractionsByPostIdAndType(postId, type)).withSelfRel());
    }

    @GetMapping("/interactions/types/{type}")
    public CollectionModel<EntityModel<Interaction>> getInteractionsByType(@PathVariable int type) {
        List<EntityModel<Interaction>> interactions = interactionService.selectInteractionsByType(type).stream()
                .map(interaction -> EntityModel.of(interaction,
                        linkTo(methodOn(InteractionController.class).addInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getPostId())).withRel("addInteraction"),
                        linkTo(methodOn(InteractionController.class).deleteInteraction(interaction.getId())).withRel("deleteInteraction"),
                        linkTo(methodOn(InteractionController.class).updateInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getId())).withRel("updateInteraction"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByUserId(interaction.getUserId())).withRel("getInteractionsByUserId"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostIdAndType(interaction.getPostId(), interaction.getInteractionType())).withRel("getInteractionsByPostIdAndType"),
                        linkTo(methodOn(InteractionController.class).getInteractionById(interaction.getId())).withRel("getInteractionById"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostId(interaction.getPostId())).withRel("getInteractionsByPostId"),
                        linkTo(methodOn(InteractionController.class).getAllInteractions()).withRel("getAllInteractions")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(interactions, linkTo(methodOn(InteractionController.class).getInteractionsByType(type)).withSelfRel());
    }

    @GetMapping("/users/{userId}/interactions")
    public CollectionModel<EntityModel<Interaction>> getInteractionsByUserId(@PathVariable int userId) {
        List<EntityModel<Interaction>> interactions = interactionService.selectInteractionsByUserId(userId).stream()
                .map(interaction -> EntityModel.of(interaction,
                        linkTo(methodOn(InteractionController.class).addInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getPostId())).withRel("addInteraction"),
                        linkTo(methodOn(InteractionController.class).deleteInteraction(interaction.getId())).withRel("deleteInteraction"),
                        linkTo(methodOn(InteractionController.class).updateInteraction(interactionMappingUtils.mapToInteractionDto(interaction), interaction.getId())).withRel("updateInteraction"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByType(interaction.getInteractionType())).withRel("getInteractionsByType"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostIdAndType(interaction.getPostId(), interaction.getInteractionType())).withRel("getInteractionsByPostIdAndType"),
                        linkTo(methodOn(InteractionController.class).getInteractionById(interaction.getId())).withRel("getInteractionById"),
                        linkTo(methodOn(InteractionController.class).getInteractionsByPostId(interaction.getPostId())).withRel("getInteractionsByPostId"),
                        linkTo(methodOn(InteractionController.class).getAllInteractions()).withRel("getAllInteractions")
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(interactions, linkTo(methodOn(InteractionController.class).getInteractionsByUserId(userId)).withSelfRel());
    }



}
