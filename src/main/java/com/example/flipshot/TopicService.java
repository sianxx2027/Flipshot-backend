package com.example.flipshot;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicResponse addTopic(TopicRequest request) {

        Topic topic = new Topic();
        topic.setTitle(request.getTitle());

        return convertToResponse(topicRepository.save(topic));
    }

    public List<TopicResponse> getTopics() {

        return topicRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public boolean deleteTopicById(int id) {

        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private TopicResponse convertToResponse(Topic topic) {

        TopicResponse response = new TopicResponse();

        response.setId(topic.getId());
        response.setTitle(topic.getTitle());

        return response;
    }
}