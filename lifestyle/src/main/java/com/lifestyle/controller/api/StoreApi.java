package com.lifestyle.controller.api;

import com.lifestyle.entity.Product;
import com.lifestyle.entity.Store;

import org.springframework.web.bind.annotation.*;

import com.lifestyle.entity.Category;
import com.lifestyle.service.ProductService;
import com.lifestyle.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreApi {
    private final StoreService stores;
    private final ProductService products;

    public StoreApi(StoreService stores, ProductService products) {
        this.stores = stores; this.products = products;
    }

    @GetMapping("/nearby")
    public List<Store> nearby(@RequestParam double lat, @RequestParam double lon,
                              @RequestParam(defaultValue = "5") double radiusKm){
        return stores.nearby(lat, lon, radiusKm);
    }

    @GetMapping("/{storeId}/products")
    public List<Product> products(@PathVariable Long storeId,
                                  @RequestParam(required = false) Category category){
        return category == null ? products.forStore(storeId)
                                : products.forStoreAndCat(storeId, category);
    }
}
