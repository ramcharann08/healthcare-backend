package com.healthcare.controller;

import com.healthcare.dto.AppointmentDto;
import com.healthcare.entity.Appointment;
import com.healthcare.entity.Patient;
import com.healthcare.entity.Provider;
import com.healthcare.repository.PatientRepository;
import com.healthcare.repository.ProviderRepository;
import com.healthcare.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*") // ✅ allow frontend calls
public class AppointmentController {

    private final AppointmentService service;
    private final PatientRepository patientRepo;
    private final ProviderRepository providerRepo;

    public AppointmentController(AppointmentService service,
                                 PatientRepository patientRepo,
                                 ProviderRepository providerRepo) {
        this.service = service;
        this.patientRepo = patientRepo;
        this.providerRepo = providerRepo;
    }

    // ✅ Book a new appointment
    @PostMapping
    public ResponseEntity<?> book(@RequestBody AppointmentDto dto) {
        if (dto.getAppointmentDate() == null || dto.getAppointmentDate().isBlank()) {
            return ResponseEntity.badRequest().body("Appointment date/time is required");
        }

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dto.getAppointmentDate());
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use: yyyy-MM-ddTHH:mm");
        }

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Provider provider = providerRepo.findById(dto.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setProvider(provider);
        appointment.setAppointmentDate(dateTime);
        appointment.setStatus(Appointment.Status.PENDING);
        appointment.setNotes(dto.getNotes());

        return ResponseEntity.ok(service.book(appointment));
    }

    // ✅ Get appointments by patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> byPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.byPatient(patientId));
    }

    // ✅ Get appointments by provider
    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Appointment>> byProvider(@PathVariable Long providerId) {
        return ResponseEntity.ok(service.byProvider(providerId));
    }

    // ✅ Get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> all() {
        return ResponseEntity.ok(service.all());
    }

    // ✅ Update appointment status (approve/reject)
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody AppointmentDto dto) {
        if (dto.getStatus() == null || dto.getStatus().isBlank()) {
            return ResponseEntity.badRequest().body("Status is required");
        }
        Appointment updated = service.updateStatus(id, dto.getStatus());
        return ResponseEntity.ok(updated);
    }
}
