package com.lifestyle.service;

import com.lifestyle.entity.Store;

import org.springframework.stereotype.Service;

import com.lifestyle.repository.StoreRepository;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository repo;
    public StoreService(StoreRepository repo){ this.repo = repo; }

    public List<Store> nearby(double lat, double lon, double radiusKm){
        return repo.findStoresWithinRadius(lat, lon, radiusKm);
    }
    public Store save(Store s){ return repo.save(s); }
    public Store get(Long id){ return repo.findById(id).orElseThrow(); }
    public List<Store> byOwner(Long ownerId){ return repo.findByOwnerId(ownerId); }
}