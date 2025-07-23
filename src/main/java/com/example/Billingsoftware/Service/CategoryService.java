package com.example.Billingsoftware.Service;

import com.example.Billingsoftware.io.CategoryRequest;
import com.example.Billingsoftware.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
   CategoryResponse add(CategoryRequest request, MultipartFile file);
   List<CategoryResponse> read();
   void delete(String categoryId);
}
