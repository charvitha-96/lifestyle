package com.lifestyle.entity;

import com.lifestyle.entity.Category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Store store;

    @NotBlank private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull private Double price;
    private String imageUrl;
    private boolean active = true;

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Store getStore() { return store; } public void setStore(Store store) { this.store = store; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public Category getCategory() { return category; } public void setCategory(Category category) { this.category = category; }
    public Double getPrice() { return price; } public void setPrice(Double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; } public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public boolean isActive() { return active; } public void setActive(boolean active) { this.active = active; }
}