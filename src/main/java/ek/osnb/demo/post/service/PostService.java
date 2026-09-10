package ek.osnb.demo.post.service;

import ek.osnb.demo.post.dto.CommentDetails;
import ek.osnb.demo.post.dto.CreateCommentDto;
import ek.osnb.demo.post.dto.CreatePostDto;
import ek.osnb.demo.post.dto.PostWithComments;
import ek.osnb.demo.post.model.Comment;
import ek.osnb.demo.post.model.Post;
import ek.osnb.demo.post.repository.CommentRepository;
import ek.osnb.demo.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<PostWithComments> getPostsWithComments() {
        List<Post> posts = postRepository.findAll();
        List<PostWithComments> postWithCommentsList = new ArrayList<>();
        for (var p : posts) {
            List<CommentDetails> commentDetails = new ArrayList<>();
            for (Comment comment : p.getComments()) {
//                commentDetails.add(new CommentDetails(comment.getId(), comment.getMessage()));
                commentDetails.add(new CommentDetails(comment.getMessage()));
            }
            postWithCommentsList.add(new PostWithComments(p.getId(), p.getTitle(), commentDetails));
        }
        return postWithCommentsList;
    }

    public PostWithComments createPost(CreatePostDto dto) {
        Post newPost = Post.create(dto.title());
        postRepository.save(newPost);
        return new PostWithComments(newPost.getId(), newPost.getTitle(), new ArrayList<>());
    }

    public PostWithComments getPostById(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            List<CommentDetails> commentDetails = new ArrayList<>();
            for (Comment comment : byId.get().getComments()) {
                commentDetails.add(new CommentDetails(comment.getMessage()));
            }
            return new PostWithComments(byId.get().getId(), byId.get().getTitle(),commentDetails);
        }
        return null;
    }

    public void deletePost(Long id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            commentRepository.deleteAll(byId.get().getComments());
            postRepository.deleteById(id);
        }
    }

    public PostWithComments updatePost(Long id, CreatePostDto dto) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            Post postToUpdate = byId.get();
            postToUpdate.setTitle(dto.title());
            postRepository.save(postToUpdate);
            List<CommentDetails> commentDetails = new ArrayList<>();
            for (Comment comment : postToUpdate.getComments()) {
                commentDetails.add(new CommentDetails(comment.getMessage()));
            }
            return new PostWithComments(postToUpdate.getId(), postToUpdate.getTitle(), commentDetails);
        }
        return null;
    }

    public PostWithComments addComment(Long id ,CreateCommentDto dto) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            Post post = byId.get();
            Comment newComment = Comment.create(dto.comment());
            post.addComment(newComment);
            postRepository.save(post);
            commentRepository.save(newComment);
            List<CommentDetails> commentDetails = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                commentDetails.add(new CommentDetails(comment.getMessage()));
            }
            return new PostWithComments(post.getId(), post.getTitle(), commentDetails);
        }
        return null;
    }
}
