package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    public Topic topicDtoToTopic(TopicDto topicDto);
    public TopicDto topicToTopicDto(Topic topic);
    public List<Topic> topicDtoToTopicList(List<TopicDto> topicDtoList);
    public List<TopicDto> topicListToTopicDtoList(List<Topic> topicList);
}
