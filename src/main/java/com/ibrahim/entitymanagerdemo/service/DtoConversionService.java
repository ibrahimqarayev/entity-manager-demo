package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.domain.Brand;
import com.ibrahim.entitymanagerdemo.domain.Category;
import com.ibrahim.entitymanagerdemo.domain.Product;
import com.ibrahim.entitymanagerdemo.dto.response.BrandResponse;
import com.ibrahim.entitymanagerdemo.dto.response.CategoryResponse;
import com.ibrahim.entitymanagerdemo.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoConversionService {

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .brand(product.getBrand().getName())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    public List<ProductResponse> toProductResponseList(List<Product> products) {
        return products.stream().map(this::toProductResponse).collect(Collectors.toList());
    }

    public BrandResponse toBrandResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .build();
    }

    public List<BrandResponse> toBrandResponseList(List<Brand> brands) {
        return brands.stream().map(this::toBrandResponse).collect(Collectors.toList());
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        return categories.stream().map(this::toCategoryResponse).collect(Collectors.toList());
    }
}
