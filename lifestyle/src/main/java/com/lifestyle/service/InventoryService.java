package com.lifestyle.service;

import com.lifestyle.entity.Product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifestyle.entity.InventoryItem;
import com.lifestyle.repository.InventoryRepository;

@Service
public class InventoryService {
    private final InventoryRepository repo;
    public InventoryService(InventoryRepository repo){ this.repo = repo; }

    public InventoryItem ensure(Product p){
        return repo.findByProductId(p.getId())
                .orElseGet(() -> {
                    InventoryItem i = new InventoryItem();
                    i.setProduct(p); i.setQuantityAvailable(0);
                    return repo.save(i);
                });
    }

    @Transactional
    public void decrement(Product p, int qty){
        InventoryItem inv = ensure(p);
        int left = inv.getQuantityAvailable() - qty;
        if(left < 0) throw new IllegalArgumentException("Insufficient inventory");
        inv.setQuantityAvailable(left);
        repo.save(inv);
    }
}