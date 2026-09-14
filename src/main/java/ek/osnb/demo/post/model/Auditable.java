package ek.osnb.demo.post.model;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Auditable {
    private LocalDateTime createdAt = LocalDateTime.now(); // created_at
    private LocalDateTime updatedAt= LocalDateTime.now(); // updated_at

    protected Auditable() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
