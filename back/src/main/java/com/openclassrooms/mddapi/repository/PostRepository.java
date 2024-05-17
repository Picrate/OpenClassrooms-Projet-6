package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String>{
    Post findByTitle(String title);
    List<Post> findByTopicEqualsIgnoreCaseOrderByCreatedAtDesc(String topic);
}
