package com.ibrahim.entitymanagerdemo.repository;

import com.ibrahim.entitymanagerdemo.domain.Product;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Transactional
    public Product update(Product product) {
        return entityManager.merge(product);
    }

    @Transactional
    public void delete(Product product) {
        entityManager.remove(product);
    }

    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public Optional<Product> findById(int id) {
        try {
            Product product = entityManager.find(Product.class, id);
            return Optional.of(product);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Product> findByCategory(String categoryName) {
        String jpql = "SELECT p FROM Product p WHERE p.category.name =:categoryName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }

    public List<Product> findByNameLike(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name LIKE :name";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", name + "%");
        return query.getResultList();
    }

    public Optional<Product> findByName(String name) {
        String jpql = "SELECT p FROM Product p.name =:name";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("name", name);
        try {
            Product product = query.getSingleResult();
            return Optional.of(product);
        } catch (NoResultException e) {
            return Optional.empty();
        }
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


    public List<Product> findProductsByNameStartsWithAndCategory(String prefix, String categoryName) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.name LIKE :prefix% " +
                "AND p.category.name =:categoryName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("prefix", prefix + "%");
        query.setParameter("categoryName", categoryName);
        return query.getResultList();
    }

    public List<Product> findProductsByNameStartsWithAndCategoryAndBrand(String prefix, String categoryName, String brandName) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.name LIKE :prefix% " +
                "AND p.category.name =:categoryName " +
                "AND p.brand.name =:brandName";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("prefix", prefix + "%");
        query.setParameter("categoryName", categoryName);
        query.setParameter("brandName", brandName);
        return query.getResultList();
    }

    public List<Product> sortByStockQuantityToHigh() {
        String jpql = "SELECT p FROM Product p ORDER BY p.stockQuantity ASC";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public List<Product> sortByStockQuantityToLow() {
        String jpql = "SELECT p FROM Product p ORDER BY p.stockQuantity DESC";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public int countAllProducts() {
        String jpql = "SELECT COUNT(p) FROM Product p";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        return query.getSingleResult();
    }

    public List<Product> sortByPriceHighToLow() {
        String jpql = "SELECT p FROM Product p ORDER BY p.price DESC";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        return query.getResultList();
    }

    public List<Product> sortByPriceLowToHigh() {
        String jpql = "SELECT p FROM Product p ORDER BY p.price ASC";
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

    public List<Product> findProductsByCategoryAndBrandAndPriceRange(String categoryName, String brandName,
                                                                     BigDecimal minPrice, BigDecimal maxPrice) {
        String jpql = "SELECT p FROM Product p " +
                "WHERE p.category.name =:categoryName " +
                "AND p.brand.name =:brandName " +
                "AND p.price BETWEEN :minPrice AND :maxPrice";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("categoryName", categoryName);
        query.setParameter("brandName", brandName);
        return query.getResultList();
    }
}
