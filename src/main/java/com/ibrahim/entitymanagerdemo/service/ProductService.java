package com.ibrahim.entitymanagerdemo.service;

import com.ibrahim.entitymanagerdemo.domain.Brand;
import com.ibrahim.entitymanagerdemo.domain.Category;
import com.ibrahim.entitymanagerdemo.domain.Product;
import com.ibrahim.entitymanagerdemo.dto.request.CreateProductRequest;
import com.ibrahim.entitymanagerdemo.dto.request.UpdateProductRequest;
import com.ibrahim.entitymanagerdemo.dto.response.ProductResponse;
import com.ibrahim.entitymanagerdemo.exception.ResourceNotFoundException;
import com.ibrahim.entitymanagerdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<ProductResponse> findByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategory(categoryName);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findByBrand(String brandName) {
        List<Product> products = productRepository.findByBrand(brandName);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByStockThreshold(int stockThreshold) {
        List<Product> products = productRepository.findProductsByStockThreshold(stockThreshold);
        return conversionService.toProductResponseList(products);
    }

    protected Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow();
    }

    protected Product findById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + id));
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

    public List<ProductResponse> findByNameLike(String name) {
        List<Product> products = productRepository.findByNameLike(name);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productRepository.findProductsByPriceRange(minPrice, maxPrice);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByNameStartsWithAndCategory(String prefix, String categoryName) {
        List<Product> products = productRepository.findProductsByNameStartsWithAndCategory(prefix, categoryName);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByNameStartsWithAndCategoryAndBrand(String prefix, String categoryName, String brandName) {
        List<Product> products = productRepository.findProductsByNameStartsWithAndCategoryAndBrand(prefix, categoryName, brandName);
        return conversionService.toProductResponseList(products);
    }

    public int countAllProducts() {
        return productRepository.countAllProducts();
    }

    public List<ProductResponse> sortByPriceHighToLow() {
        List<Product> products = productRepository.sortByPriceHighToLow();
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> sortByPriceLowToHigh() {
        List<Product> products = productRepository.sortByPriceLowToHigh();
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> sortByStockQuantityToHigh() {
        List<Product> products = productRepository.sortByStockQuantityToHigh();
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> sortByStockQuantityToLow() {
        List<Product> products = productRepository.sortByStockQuantityToLow();
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByCategoryAndBrand(String categoryName, String brandName) {
        List<Product> products = productRepository.findProductsByCategoryAndBrand(categoryName, brandName);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByCategoryAndPriceRange(String categoryName, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productRepository.findProductsByCategoryAndPriceRange(categoryName, minPrice, maxPrice);
        return conversionService.toProductResponseList(products);
    }

    public List<ProductResponse> findProductsByCategoryAndBrandAndPriceRange(String categoryName, String brandName, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productRepository.findProductsByCategoryAndBrandAndPriceRange(categoryName, brandName, minPrice, maxPrice);
        return conversionService.toProductResponseList(products);
    }

}
