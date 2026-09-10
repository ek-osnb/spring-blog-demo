package ek.osnb.demo.post.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne
    @JsonBackReference
    private Post post;

    protected Comment() {}

    public static Comment create(String message, Post post) {
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setPost(post);
        return comment;
    }

    public static Comment create(String message) {
        Comment comment = new Comment();
        comment.setMessage(message);
        return comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
