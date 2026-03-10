package com.lifestyle.service;

import com.lifestyle.dto.PreorderRequest;
import com.lifestyle.entity.*;
import com.lifestyle.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifestyle.repository.PreorderRepository;
import com.lifestyle.repository.ProductRepository;
import com.lifestyle.repository.UserRepository;

@Service
public class PreorderService {
    private final PreorderRepository preorders;
    private final ProductRepository products;
    private final UserRepository users;
    private final InventoryService inventory;

    public PreorderService(PreorderRepository preorders, ProductRepository products,
                           UserRepository users, InventoryService inventory) {
        this.preorders = preorders; this.products = products; this.users = users; this.inventory = inventory;
    }

    @Transactional
    public Preorder create(PreorderRequest req){
        User customer = users.findById(req.getCustomerId()).orElseThrow();
        Product product = products.findById(req.getProductId()).orElseThrow();
        Store store = product.getStore();

        int qty = req.getQuantity();
        double unitPrice = product.getPrice();
        double total = unitPrice * qty;
        double advance = Math.round(total * 0.20 * 100.0) / 100.0;

        Preorder p = new Preorder();
        p.setCustomer(customer); p.setProduct(product); p.setStore(store);
        p.setQuantity(qty); p.setUnitPrice(unitPrice); p.setAdvanceAmount(advance);
        p.setStatus(PreorderStatus.CREATED);
        return preorders.save(p);
    }

    @Transactional
    public Preorder payAdvance(Long preorderId){
        Preorder p = preorders.findById(preorderId).orElseThrow();
        p.setStatus(PreorderStatus.ADVANCE_PAID);
        inventory.decrement(p.getProduct(), p.getQuantity());
        p.setStatus(PreorderStatus.RESERVED);
        return preorders.save(p);
    }

    @Transactional
    public Preorder complete(Long preorderId){
        Preorder p = preorders.findById(preorderId).orElseThrow();
        p.setStatus(PreorderStatus.COMPLETED);
        return preorders.save(p);
    }
}