package com.springboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private List<CategoryDto> content;
    private int pageNo;
    private int pageSize;
    private boolean last;
    private long totalElements;
    private int totalPages;
}
