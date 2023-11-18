package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

}
