package com.ibrahim.entitymanagerdemo.repository;

import com.ibrahim.entitymanagerdemo.domain.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Category> findAll() {
        String jpql = "SELECT c FROM Category c";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        return query.getResultList();
    }

    public Optional<Category> findById(int id) {
        String jpql = "SELECT c FROM Category c WHERE c.id =:id";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("id", id);
        Category category = query.getSingleResult();
        return Optional.ofNullable(category);
    }

    public Optional<Category> findByName(String name) {
        String jpql = "SELECT c FROM Category c WHERE c.name =:name";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("name", name);
        Category category = query.getSingleResult();
        return Optional.of(category);
    }

}
