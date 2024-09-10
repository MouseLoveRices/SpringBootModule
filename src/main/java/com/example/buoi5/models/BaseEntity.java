package com.example.buoi5.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(name="create_at",updatable=false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="update_at")
    private LocalDateTime updatedAt;
}
