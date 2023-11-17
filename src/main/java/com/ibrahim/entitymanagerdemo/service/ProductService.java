package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.domain.Product;
import com.ibrahim.entitymanagerdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow();
    }

}
