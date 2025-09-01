package com.healthcare.service;

import com.healthcare.entity.Provider;
import java.util.List;
import java.util.Optional;

public interface ProviderService {
    Optional<Provider> getById(Long id);
    List<Provider> getAll();
}
