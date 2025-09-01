package com.healthcare.service.impl;

import com.healthcare.entity.Patient;
import com.healthcare.repository.PatientRepository;
import com.healthcare.service.PatientService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repo;

    public PatientServiceImpl(PatientRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Patient update(Long id, Patient p) {
        Patient existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        existing.setName(p.getName());
        existing.setPhone(p.getPhone());
        existing.setAddress(p.getAddress());
        existing.setDob(p.getDob());
        existing.setGender(p.getGender());
        return repo.save(existing);
    }
}
