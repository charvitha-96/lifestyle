package com.lifestyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifestyle.entity.InventoryItem;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByProductId(Long productId);
}