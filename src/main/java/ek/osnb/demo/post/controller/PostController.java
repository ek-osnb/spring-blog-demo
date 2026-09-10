package ek.osnb.demo.post.controller;

import ek.osnb.demo.post.model.Post;
import ek.osnb.demo.post.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
