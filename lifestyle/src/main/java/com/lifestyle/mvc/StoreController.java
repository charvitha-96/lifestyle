package com.lifestyle.mvc;

import com.lifestyle.entity.Store;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lifestyle.entity.Category;
import com.lifestyle.service.ProductService;
import com.lifestyle.service.StoreService;

@Controller
@RequestMapping("/stores")
public class StoreController {
    private final StoreService stores;
    private final ProductService products;

    public StoreController(StoreService stores, ProductService products) {
        this.stores = stores; this.products = products;
    }

    @GetMapping
    public String list(){ return "stores/list"; }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         @RequestParam(required = false) Category category,
                         HttpSession session, Model model){
        Store s = stores.get(id);
        model.addAttribute("store", s);
        model.addAttribute("products", category == null ?
                products.forStore(id) : products.forStoreAndCat(id, category));
        model.addAttribute("selectedCategory", category);
        model.addAttribute("userId", session.getAttribute("userId"));
        return "stores/detail";
    }
}