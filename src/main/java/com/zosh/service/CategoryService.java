package com.zosh.service;


import com.zosh.exceptions.UserException;
import com.zosh.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory (CategoryDto dto) throws Exception;
    List<CategoryDto> getAllCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto dto ) throws Exception;
    void deleteCategory(Long id) throws Exception;
}
