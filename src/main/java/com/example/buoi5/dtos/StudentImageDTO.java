package com.example.buoi5.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentImageDTO {
    @JsonProperty("student_id")
    @Min(value=1,message = "Id cua student phai lon hon 0")
    private Long studentId;

    @Size(min = 5,max = 200,message = "Ten cua hinh anh tu 5-200 ki tu")
    @JsonProperty("image_url")
    private String imageUrl;

}
