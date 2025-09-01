package com.healthcare.service.impl;

import com.healthcare.entity.Provider;
import com.healthcare.repository.ProviderRepository;
import com.healthcare.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository repo;

    public ProviderServiceImpl(ProviderRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Provider> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Provider> getAll() {
        return repo.findAll();
    }
}
