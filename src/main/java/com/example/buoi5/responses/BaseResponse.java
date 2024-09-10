package com.example.buoi5.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseResponse {
    @JsonProperty("create_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
