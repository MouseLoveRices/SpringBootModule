package com.example.buoi5.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentListResponse {
    private List<StudentResponse> studentResponseList;
    private int totalPages;
}
