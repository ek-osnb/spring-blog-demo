package ek.osnb.demo.post.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonPropertyOrder({"id", "title", "comments"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "post")
    // Does not impact db tables
    // This is only a java reference
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @Embedded
    private Auditable audit = new Auditable();

    public Post() {}

    public static Post create(String title) {
        Post post = new Post();
        post.title = title;
        return post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.audit.update();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
        this.audit.update();
    }

    public List<Comment> getComments() {
        return comments;
    }
}
