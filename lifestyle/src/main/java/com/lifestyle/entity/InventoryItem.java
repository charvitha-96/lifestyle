package com.lifestyle.entity;

import com.lifestyle.entity.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Product product;

    private Integer quantityAvailable = 0;

    @Version
    private Long version;

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Product getProduct() { return product; } public void setProduct(Product product) { this.product = product; }
    public Integer getQuantityAvailable() { return quantityAvailable; } public void setQuantityAvailable(Integer quantityAvailable) { this.quantityAvailable = quantityAvailable; }
    public Long getVersion() { return version; } public void setVersion(Long version) { this.version = version; }
}