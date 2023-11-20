package com.ibrahim.entitymanagerdemo.contoller;

import com.ibrahim.entitymanagerdemo.dto.request.CreateProductRequest;
import com.ibrahim.entitymanagerdemo.dto.request.UpdateProductRequest;
import com.ibrahim.entitymanagerdemo.dto.response.ProductResponse;
import com.ibrahim.entitymanagerdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.findAll();
    }

    @PostMapping
    public ProductResponse create(@RequestBody CreateProductRequest createRequest) {
        return productService.create(createRequest);
    }

    @GetMapping("/product")
    public List<ProductResponse> getByNameLike(@RequestParam("name") String name) {
        return productService.findByNameLike(name);
    }

    @GetMapping("/price")
    public List<ProductResponse> getByPriceRange(@RequestParam("min") BigDecimal minPrice, @RequestParam("max") BigDecimal maxPrice) {
        return productService.findProductsByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/category")
    public List<ProductResponse> getByBrand(@RequestParam("name") String brandName) {
        return productService.findByBrand(brandName);
    }

    @GetMapping("/brand")
    public List<ProductResponse> getByCategory(@RequestParam("name") String categoryName) {
        return productService.findByCategory(categoryName);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int productId) {
        productService.deleteById(productId);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable("id") int productId, @RequestBody UpdateProductRequest updateRequest) {
        return productService.update(productId, updateRequest);
    }


}
