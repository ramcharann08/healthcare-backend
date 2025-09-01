package com.healthcare.controller;

import com.healthcare.entity.WellnessService;
import com.healthcare.service.WellnessServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class WellnessServiceController {

    private final WellnessServiceService service;

    public WellnessServiceController(WellnessServiceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WellnessService>> all() {
        return ResponseEntity.ok(service.all());
    }
}
