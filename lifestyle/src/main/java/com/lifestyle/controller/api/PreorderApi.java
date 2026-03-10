package com.lifestyle.controller.api;

import com.lifestyle.dto.PreorderRequest;
import com.lifestyle.entity.Preorder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lifestyle.service.PreorderService;

@RestController
@RequestMapping("/api/preorders")
public class PreorderApi {
    private final PreorderService service;
    public PreorderApi(PreorderService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Preorder> create(@Valid @RequestBody PreorderRequest req){
        return ResponseEntity.ok(service.create(req));
    }

    @PostMapping("/{id}/pay-advance")
    public ResponseEntity<Preorder> payAdvance(@PathVariable Long id){
        return ResponseEntity.ok(service.payAdvance(id));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Preorder> complete(@PathVariable Long id){
        return ResponseEntity.ok(service.complete(id));
    }
}