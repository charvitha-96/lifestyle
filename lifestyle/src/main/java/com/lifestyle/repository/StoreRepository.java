package com.lifestyle.repository;

import com.lifestyle.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value =
        "SELECT s.*, (" +
        "  6371 * acos(" +
        "    cos(radians(:lat)) * cos(radians(s.latitude)) * " +
        "    cos(radians(s.longitude) - radians(:lon)) + " +
        "    sin(radians(:lat)) * sin(radians(s.latitude))" +
        "  )" +
        ") AS distance " +
        "FROM stores s " +
        "WHERE s.active = true " +
        "HAVING distance <= :radiusKm " +
        "ORDER BY distance",
        nativeQuery = true)
    List<Store> findStoresWithinRadius(@Param("lat") double lat,
                                       @Param("lon") double lon,
                                       @Param("radiusKm") double radiusKm);

    List<Store> findByOwnerId(Long ownerId);
}