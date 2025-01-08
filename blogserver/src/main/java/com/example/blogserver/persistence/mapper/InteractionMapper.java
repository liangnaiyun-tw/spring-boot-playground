package com.example.blogserver.persistence.mapper;

import com.example.blogserver.model.Interaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InteractionMapper {
    public int insertInteraction(Interaction interaction);
    public int deleteInteraction(int id);
    public int updateInteraction(Interaction interaction);
    @Select("SELECT * FROM interaction")
    public List<Interaction> selectAllInteractions();

    public List<Interaction> selectInteractionsByPostId(int postId);
    public List<Interaction> selectInteractionsByUserId(int userId);
    public Interaction selectInteractionById(int id);
    public List<Interaction> selectInteractionsByType(int type);

    public List<Interaction> selectInteractionByPostIdAndType(@Param("postId") int postId, @Param("type") int type);

}
