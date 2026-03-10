package com.lifestyle.repository;

import com.lifestyle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lifestyle.entity.Category;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);
    List<Product> findByStoreIdAndCategory(Long storeId, Category category);
}