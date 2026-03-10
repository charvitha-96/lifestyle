package com.lifestyle.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PreorderRequest {
    @NotNull private Long customerId;
    @NotNull private Long productId;
    @Min(1) private Integer quantity = 1;

    public Long getCustomerId() { return customerId; } public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Long getProductId() { return productId; } public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; } public void setQuantity(Integer quantity) { this.quantity = quantity; }
}