package com.healthcare.controller;

import com.healthcare.dto.PaymentDto;
import com.healthcare.entity.*;
import com.healthcare.repository.*;
import com.healthcare.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;
    private final PatientRepository patientRepo;
    private final AppointmentRepository appointmentRepo;
    private final com.healthcare.repository.WellnessServiceRepository wsRepo;

    public PaymentController(PaymentService service, PatientRepository patientRepo,
                             AppointmentRepository appointmentRepo, com.healthcare.repository.WellnessServiceRepository wsRepo) {
        this.service = service;
        this.patientRepo = patientRepo;
        this.appointmentRepo = appointmentRepo;
        this.wsRepo = wsRepo;
    }

    @PostMapping
    public ResponseEntity<?> process(@RequestBody PaymentDto dto) {
        Payment p = new Payment();
        p.setPatient(patientRepo.findById(dto.getPatientId()).orElseThrow());
        p.setAppointment(dto.getAppointmentId() != null ? appointmentRepo.findById(dto.getAppointmentId()).orElse(null) : null);
        p.setService(dto.getServiceId() != null ? wsRepo.findById(dto.getServiceId()).orElse(null) : null);
        p.setPaymentStatus(Payment.Status.PENDING);
        return ResponseEntity.ok(service.process(p));
    }
}
