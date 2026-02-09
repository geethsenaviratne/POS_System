package com.zosh.service.impl;

import com.zosh.domain.UserRole;
import com.zosh.exceptions.UserException;
import com.zosh.mapper.CategoryMapper;
import com.zosh.model.Category;
import com.zosh.model.Store;
import com.zosh.model.User;
import com.zosh.payload.dto.CategoryDto;
import com.zosh.repository.CategoryRepository;
import com.zosh.repository.StoreRepository;
import com.zosh.repository.UserRepository;
import com.zosh.service.CategoryService;
import com.zosh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;


    @Override
    public CategoryDto createCategory(CategoryDto dto) throws Exception {
        User user = userService.getCurrentUser();

        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );

        Category category = Category.builder()
                .store(store)
                .name(dto.getName())
                .build();

        checkAuthority(user, category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }


    @Override
    public List<CategoryDto> getAllCategoriesByStore(Long storeId) {

        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream()
                .map(
                        CategoryMapper::toDTO
                ).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not found")
        );
        User user = userService.getCurrentUser();

        category.setName(dto.getName());
        checkAuthority(user, category.getStore());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {

        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Category not found")
        );
        User user = userService.getCurrentUser();

        checkAuthority(user, category.getStore());

        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new Exception("You don't have permission to manage this category");
        }
    }
}
