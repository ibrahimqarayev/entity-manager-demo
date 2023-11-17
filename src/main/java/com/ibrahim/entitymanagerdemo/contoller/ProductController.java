package com.ibrahim.entitymanagerdemo.contoller;

import com.ibrahim.entitymanagerdemo.domain.Product;
import com.ibrahim.entitymanagerdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<Product> getAll() {
        return productService.findAll();
    }


}
