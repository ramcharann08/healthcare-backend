package com.healthcare.controller;

import com.healthcare.dto.EnrollmentDto;
import com.healthcare.entity.*;
import com.healthcare.repository.*;
import com.healthcare.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;
    private final PatientRepository patientRepo;
    private final com.healthcare.repository.WellnessServiceRepository wsRepo;

    public EnrollmentController(EnrollmentService service, PatientRepository patientRepo,
                                com.healthcare.repository.WellnessServiceRepository wsRepo) {
        this.service = service;
        this.patientRepo = patientRepo;
        this.wsRepo = wsRepo;
    }

    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody EnrollmentDto dto) {
        Enrollment e = new Enrollment();
        e.setPatient(patientRepo.findById(dto.getPatientId()).orElseThrow());
        e.setService(wsRepo.findById(dto.getServiceId()).orElseThrow());
        e.setStartDate(LocalDate.parse(dto.getStartDate()));
        e.setEndDate(LocalDate.parse(dto.getEndDate()));
        e.setProgress(dto.getProgress() != null ? dto.getProgress() : 0);
        return ResponseEntity.ok(service.enroll(e));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Enrollment>> byPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.byPatient(patientId));
    }
}
