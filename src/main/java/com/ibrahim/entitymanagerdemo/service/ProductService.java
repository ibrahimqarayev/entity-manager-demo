package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.domain.Brand;
import com.ibrahim.entitymanagerdemo.domain.Category;
import com.ibrahim.entitymanagerdemo.domain.Product;
import com.ibrahim.entitymanagerdemo.dto.request.CreateProductRequest;
import com.ibrahim.entitymanagerdemo.dto.request.UpdateProductRequest;
import com.ibrahim.entitymanagerdemo.dto.response.ProductResponse;
import com.ibrahim.entitymanagerdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final DtoConversionService conversionService;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return conversionService.toProductResponseList(products);
    }

    public ProductResponse create(CreateProductRequest createRequest) {
        Product product = Product.builder()
                .name(createRequest.getName())
                .price(createRequest.getPrice())
                .description(createRequest.getDescription())
                .category(categoryService.findByName(createRequest.getCategoryName()))
                .brand(brandService.findByName(createRequest.getBrandName()))
                .build();
        return save(product);
    }

    public ProductResponse update(int productId, UpdateProductRequest updateRequest) {
        Category category = categoryService.findByName(updateRequest.getCategoryName());
        Brand brand = brandService.findByName(updateRequest.getBrandName());

        Product product = findById(productId);
        product.setName(updateRequest.getName());
        product.setPrice(updateRequest.getPrice());
        product.setDescription(updateRequest.getDescription());
        product.setCategory(category);
        product.setBrand(brand);
        Product updated = productRepository.update(product);
        return conversionService.toProductResponse(updated);
    }

    public void deleteById(int productId) {
        Product product = findById(productId);
        productRepository.delete(product);
    }

    public ProductResponse save(Product product) {
        Product saved = productRepository.save(product);
        return conversionService.toProductResponse(saved);
    }

    protected Product findById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    protected Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow();
    }

}
