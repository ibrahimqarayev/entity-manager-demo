package com.ibrahim.entitymanagerdemo.repository;

import com.ibrahim.entitymanagerdemo.domain.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BrandRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Brand> findAll() {
        String jpql = "SELECT b FROM Brand b";
        TypedQuery<Brand> query = entityManager.createQuery(jpql, Brand.class);
        return query.getResultList();
    }

    public Optional<Brand> findById(int id) {
        String jpql = "SELECT b FROM Brand b WHERE b.id =:id";
        TypedQuery<Brand> query = entityManager.createQuery(jpql, Brand.class);
        query.setParameter("id", id);
        Brand brand = query.getSingleResult();
        return Optional.ofNullable(brand);
    }

    public Optional<Brand> findByName(String name) {
        String jpql = "SELECT b FROM Brand b WHERE b.name =:name";
        TypedQuery<Brand> query = entityManager.createQuery(jpql, Brand.class);
        query.setParameter("name", name);
        Brand brand = query.getSingleResult();
        return Optional.ofNullable(brand);
    }

}
