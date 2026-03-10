package com.lifestyle.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "preorders")
public class Preorder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Product product;
    @ManyToOne(optional = false) private User customer;
    @ManyToOne(optional = false) private Store store;

    private Integer quantity;
    private Double unitPrice;
    private Double advanceAmount;

    @Enumerated(EnumType.STRING)
    private PreorderStatus status = PreorderStatus.CREATED;

    @Column(updatable = false)
    private Instant createdAt = Instant.now();

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Product getProduct() { return product; } public void setProduct(Product product) { this.product = product; }
    public User getCustomer() { return customer; } public void setCustomer(User customer) { this.customer = customer; }
    public Store getStore() { return store; } public void setStore(Store store) { this.store = store; }
    public Integer getQuantity() { return quantity; } public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getUnitPrice() { return unitPrice; } public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Double getAdvanceAmount() { return advanceAmount; } public void setAdvanceAmount(Double advanceAmount) { this.advanceAmount = advanceAmount; }
    public PreorderStatus getStatus() { return status; } public void setStatus(PreorderStatus status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; } public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}