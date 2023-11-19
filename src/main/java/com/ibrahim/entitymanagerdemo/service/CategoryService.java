package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.domain.Category;
import com.ibrahim.entitymanagerdemo.dto.response.CategoryResponse;
import com.ibrahim.entitymanagerdemo.exception.ResourceNotFoundException;
import com.ibrahim.entitymanagerdemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final DtoConversionService conversionService;

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return conversionService.toCategoryResponseList(categories);
    }

    protected Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + name));
    }

}
