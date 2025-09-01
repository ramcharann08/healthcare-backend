package com.healthcare.service;

import com.healthcare.entity.Patient;
import java.util.Optional;

public interface PatientService {
    Optional<Patient> getById(Long id);
    Patient update(Long id, Patient p);
}
