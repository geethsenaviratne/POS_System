package com.zosh.controller;


import com.zosh.payload.dto.CategoryDto;
import com.zosh.payload.response.ApiResponse;
import com.zosh.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.ok(
                categoryService.createCategory(categoryDto)
        );

    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByStore(
            @PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(
                categoryService.getAllCategoriesByStore(storeId)
        );

    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(
                categoryService.updateCategory(id,categoryDto)
        );

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable Long id) throws Exception {

        categoryService.updateCategory(id,categoryDto);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted Category");

        return ResponseEntity.ok(
                    apiResponse
        );

    }
}
