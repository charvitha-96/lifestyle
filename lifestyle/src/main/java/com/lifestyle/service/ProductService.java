package com.lifestyle.service;

import com.lifestyle.entity.Product;

import org.springframework.stereotype.Service;

import com.lifestyle.entity.Category;
import com.lifestyle.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo){ this.repo = repo; }

    public List<Product> forStore(Long storeId){ return repo.findByStoreId(storeId); }
    public List<Product> forStoreAndCat(Long storeId, Category category){ return repo.findByStoreIdAndCategory(storeId, category); }
    public Product get(Long id){ return repo.findById(id).orElseThrow(); }
    public Product save(Product p){ return repo.save(p); }
}
