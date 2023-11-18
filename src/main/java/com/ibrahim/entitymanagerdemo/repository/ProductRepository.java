package com.ibrahim.entitymanagerdemo.repository;

import com.ibrahim.entitymanagerdemo.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

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

    public List<Product> findByBrand(String brand) {
        String jpql = "SELECT p FROM Product p WHERE p.brand =:brand";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("brand", brand);
        return query.getResultList();
    }

}
