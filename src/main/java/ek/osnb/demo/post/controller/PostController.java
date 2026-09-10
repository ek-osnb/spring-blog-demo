package ek.osnb.demo.post.controller;

import ek.osnb.demo.post.dto.CreateCommentDto;
import ek.osnb.demo.post.dto.CreatePostDto;
import ek.osnb.demo.post.dto.PostWithComments;
import ek.osnb.demo.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<PostWithComments> getPostById(@PathVariable Long id) {
        PostWithComments postById = postService.getPostById(id);
        if (postById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postById);
    }

    @PostMapping
    public PostWithComments createPost(@RequestBody CreatePostDto dto) {
        return postService.createPost(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostWithComments> updatePost(@PathVariable Long id, @RequestBody CreatePostDto dto) {
        PostWithComments updatedPost = postService.updatePost(id, dto);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<PostWithComments> addCommentToPost(@PathVariable Long id, @RequestBody CreateCommentDto dto) {
        PostWithComments postWithComments = postService.addComment(id, dto);
        if (postWithComments == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postWithComments);
    }
}
