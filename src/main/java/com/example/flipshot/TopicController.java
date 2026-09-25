package com.example.flipshot;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
@CrossOrigin(origins = {
	    "https://flipshot-opal.vercel.app",
	    "http://localhost:4200"
	})
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public TopicResponse addTopic(
            @RequestBody TopicRequest request) {

        return topicService.addTopic(request);
    }

    @GetMapping
    public List<TopicResponse> getTopics() {
        return topicService.getTopics();
    }

    @DeleteMapping("/{id}")
    public String deleteTopic(@PathVariable int id) {

        boolean deleted = topicService.deleteTopicById(id);

        if (deleted) {
            return "Topic deleted successfully";
        }

        return "Topic not found";
    }
}