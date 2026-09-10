package ek.osnb.demo.post.dto;

import java.util.List;

public record PostWithComments(Long id, String title, List<CommentDetails> comments) {
}
