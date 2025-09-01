package com.healthcare.service;

import com.healthcare.entity.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment enroll(Enrollment e);
    List<Enrollment> byPatient(Long patientId);
}
