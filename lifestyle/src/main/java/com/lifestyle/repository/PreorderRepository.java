package com.lifestyle.repository;

import com.lifestyle.entity.Preorder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreorderRepository extends JpaRepository<Preorder, Long> {
    List<Preorder> findByCustomerId(Long customerId);
    List<Preorder> findByStoreId(Long storeId);
}