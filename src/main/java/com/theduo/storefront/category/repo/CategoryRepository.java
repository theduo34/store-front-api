package com.theduo.storefront.category.repo;

import com.theduo.storefront.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
    boolean existsByName(String name);
}
