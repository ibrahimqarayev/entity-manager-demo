package com.ibrahim.entitymanagerdemo.repository;

import com.ibrahim.entitymanagerdemo.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public Optional<Product> findById(int id) {
        Product product = entityManager.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    public Optional<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p.name =:name";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", name);
        Product product = query.getSingleResult();
        return Optional.ofNullable(product);
    }

    public List<Product> findByBrand(String brandName) {
        String jpql = "SELECT p FROM Product p WHERE p.brand.name =:brandName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("brandName", brandName);
        return query.getResultList();
    }

    public List<Product> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.price BETWEEN :minPrice " +
                "AND :maxPrice";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

    public List<Product> findProductsByNameStartsWith(String prefix) {
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :prefix%";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }

    public List<Product> findProductsByNameStartsWithAndCategory(String prefix, String categoryName) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.name LIKE :prefix% " +
                "AND p.category.name =:categoryName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("prefix", prefix);
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }

    public List<Product> findProductsByNameStartsWithAndCategoryAndBrand(String prefix, String categoryName, String brandName) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.name LIKE :prefix% " +
                "AND p.category.name =:categoryName " +
                "AND p.brand.name =:brandName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("prefix", prefix);
        query.setParameter("categoryName", categoryName);
        query.setParameter("brandName", brandName);
        return query.getResultList();
    }

    public long countAllProducts() {
        String jpql = "SELECT COUNT(p) FROM Product p";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }

    public List<Product> sortByPriceHighToLow() {
        String jpql = "SELECT p FROM Product p WHERE p.price DESC";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public List<Product> sortByPriceLowToHigh() {
        String jpql = "SELECT p FROM Product p WHERE p.price ASC";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public List<Product> findProductsByCategoryAndBrand(String categoryName, String brandName) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.category.name =:categoryName " +
                "AND p.brand.name =:brandName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("categoryName", categoryName);
        query.setParameter("brandName", brandName);
        return query.getResultList();
    }

    public List<Product> findProductsByCategoryAndPriceRange(String categoryName, BigDecimal minPrice, BigDecimal maxPrice) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.category.name =:categoryName " +
                "AND p.price BETWEEN :minPrice " +
                "AND :maxPrice";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("categoryName", categoryName);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        return query.getResultList();
    }

}
