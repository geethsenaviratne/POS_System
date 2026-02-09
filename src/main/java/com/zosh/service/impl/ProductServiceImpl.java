package com.zosh.service.impl;

import com.zosh.mapper.ProductMapper;
import com.zosh.model.Category;
import com.zosh.model.Product;
import com.zosh.model.Store;
import com.zosh.model.User;
import com.zosh.payload.dto.ProductDto;
import com.zosh.repository.CategoryRepository;
import com.zosh.repository.ProductRepository;
import com.zosh.repository.StoreRepository;
import com.zosh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(productDto.getStoreId()
        ).orElseThrow( ()-> new Exception("Store not found")
        );

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow (()-> new Exception("Category not found"));

        Product product = ProductMapper.toEntity(productDto, store, category);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );


        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(product.getImage());
        product.setMrp(product.getMrp());
        product.setSellingPrice(product.getSellingPrice());
        product.setBrand(product.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        if(productDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    ()-> new Exception("category not found")
            );

            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );

        productRepository.delete(product);

    }


    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);

        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepository.searchByKeyword(storeId, keyword);

        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }
}
