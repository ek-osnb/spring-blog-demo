package ek.osnb.demo.post.config;

import ek.osnb.demo.post.model.Post;
import ek.osnb.demo.post.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final PostRepository postRepository;

    public InitData(PostRepository postRepository) {
        this.postRepository = postRepository;
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
    }
}
