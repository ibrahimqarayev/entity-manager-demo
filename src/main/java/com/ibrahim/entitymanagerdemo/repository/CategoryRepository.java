package com.ibrahim.entitymanagerdemo.repository;

import com.ibrahim.entitymanagerdemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
