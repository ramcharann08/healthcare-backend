package com.healthcare.service.impl;

import com.healthcare.entity.Appointment;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentServiceImpl(AppointmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Appointment book(Appointment a) {
        return repo.save(a);
    }

    @Override
    public List<Appointment> byPatient(Long patientId) {
        return repo.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> byProvider(Long providerId) {
        return repo.findByProviderId(providerId);
    }

    @Override
    public List<Appointment> all() {
        return repo.findAll();
    }

    @Override
    public Appointment updateStatus(Long appointmentId, String status) {
        Appointment appointment = repo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        String normalized = status.toUpperCase();
        Appointment.Status newStatus;

        switch (normalized) {
            case "APPROVED":
                newStatus = Appointment.Status.CONFIRMED;
                break;
            case "REJECTED":
                newStatus = Appointment.Status.CANCELLED;
                break;
            default:
                try {
                    newStatus = Appointment.Status.valueOf(normalized);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid status: " + status +
                            ". Allowed: PENDING, APPROVED, REJECTED, CONFIRMED, COMPLETED, CANCELLED.");
                }
        }

        appointment.setStatus(newStatus);
        return repo.save(appointment);
    }
}
