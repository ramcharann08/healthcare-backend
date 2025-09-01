package com.healthcare.service.impl;

import com.healthcare.entity.WellnessService;
import com.healthcare.repository.WellnessServiceRepository;
import com.healthcare.service.WellnessServiceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WellnessServiceServiceImpl implements WellnessServiceService {

    private final WellnessServiceRepository repo;

    public WellnessServiceServiceImpl(WellnessServiceRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<WellnessService> all() {
        return repo.findAll();
    }
}
