package com.example.Billingsoftware.controller;

import com.example.Billingsoftware.Service.CategoryService;
import com.example.Billingsoftware.io.CategoryRequest;
import com.example.Billingsoftware.io.CategoryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestPart("category") String categoryString ,
                                        @RequestPart("file")MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryRequest request = null;
        try{
            request = objectMapper.readValue(categoryString,CategoryRequest.class);
            return categoryService.add(request,file);

        }
        catch(JsonProcessingException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Exception occed while parsing the json: "+ex.getMessage());

        }
    }

    @GetMapping("/categories")
    public List<CategoryResponse> fetchCategory() {
        return categoryService.read();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/categories/{categoryId}")
    public void remove(@PathVariable String categoryId) {
        try {
            categoryService.delete(categoryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
