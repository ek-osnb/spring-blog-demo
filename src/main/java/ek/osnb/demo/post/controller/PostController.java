package ek.osnb.demo.post.controller;

import ek.osnb.demo.post.dto.PostWithComments;
import ek.osnb.demo.post.model.Post;
import ek.osnb.demo.post.repository.PostRepository;
import ek.osnb.demo.post.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    List<PostWithComments> getAllPosts() {
        return postService.getPostsWithComments();
    }
}
