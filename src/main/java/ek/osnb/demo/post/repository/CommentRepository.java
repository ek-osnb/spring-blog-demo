package ek.osnb.demo.post.repository;

import ek.osnb.demo.post.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
