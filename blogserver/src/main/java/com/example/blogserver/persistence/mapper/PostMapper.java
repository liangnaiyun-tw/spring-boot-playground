package com.example.blogserver.persistence.mapper;

import com.example.blogserver.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    int createPost(Post post);
    @Select("SELECT * FROM post;")
    List<Post> allPosts();
    List<Post> postsByUser(int userId);
    Post postById(int id);
    int updatePost(Post post);
    int deletePost(int id);

}
