package com.example.buoi4.responses;

import com.example.buoi4.model.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse extends BaseResponse{
    private Long id;
    private String name;
    public static CategoryResponse fromCategory(Category category){
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .name(category.getName())
                .id(category.getId())
                .build();

        categoryResponse.setCreatedAt(category.getCreateAt());
        categoryResponse.setCreatedAt(category.getUpdateAt());
        return categoryResponse;
    }
}
