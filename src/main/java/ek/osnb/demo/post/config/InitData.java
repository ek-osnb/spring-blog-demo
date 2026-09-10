package ek.osnb.demo.post.config;

import ek.osnb.demo.post.model.Comment;
import ek.osnb.demo.post.model.Post;
import ek.osnb.demo.post.repository.CommentRepository;
import ek.osnb.demo.post.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public InitData(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Post post1 = Post.create("First Post");
        Post post2 = Post.create("Second Post");
        Post post3 = Post.create("Thrid Post");
        Post post4 = Post.create("Fourth Post");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);

        Comment comment1 = Comment.create("You suck", post1);
        post1.addComment(comment1);
        commentRepository.save(comment1);
    }
}
