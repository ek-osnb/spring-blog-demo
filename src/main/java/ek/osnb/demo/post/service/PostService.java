package ek.osnb.demo.post.service;

import ek.osnb.demo.post.dto.CommentDetails;
import ek.osnb.demo.post.dto.PostWithComments;
import ek.osnb.demo.post.model.Comment;
import ek.osnb.demo.post.model.Post;
import ek.osnb.demo.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}
