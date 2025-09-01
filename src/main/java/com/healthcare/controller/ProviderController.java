package com.healthcare.controller;

import com.healthcare.entity.Provider;
import com.healthcare.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@CrossOrigin(origins = "*") // 👈 allows frontend calls
public class ProviderController {

    private final ProviderService service;

    public ProviderController(ProviderService service) {
        this.service = service;
    }

    // Get provider by ID
    @GetMapping("/{id}")
    public ResponseEntity<Provider> get(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all providers
    @GetMapping
    public ResponseEntity<List<Provider>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
