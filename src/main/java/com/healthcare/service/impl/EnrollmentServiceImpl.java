package com.healthcare.service.impl;

import com.healthcare.entity.Enrollment;
import com.healthcare.repository.EnrollmentRepository;
import com.healthcare.service.EnrollmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository repo;

    public EnrollmentServiceImpl(EnrollmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Enrollment enroll(Enrollment e) {
        return repo.save(e);
    }

    @Override
    public List<Enrollment> byPatient(Long patientId) {
        return repo.findByPatientId(patientId);
    }
}
