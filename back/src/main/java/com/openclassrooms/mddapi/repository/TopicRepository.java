package com.openclassrooms.mddapi.repository;


import com.openclassrooms.mddapi.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TopicRepository extends MongoRepository<Topic, String> {
    public List<Topic> getAllByOrderByTitleAsc();
    public List<Topic> getTopicByTitleContainingIgnoreCase(String title);
}
