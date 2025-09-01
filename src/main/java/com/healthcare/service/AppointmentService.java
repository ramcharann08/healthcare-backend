package com.healthcare.service;

import com.healthcare.entity.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment book(Appointment a);
    List<Appointment> byPatient(Long patientId);
    List<Appointment> byProvider(Long providerId);
    List<Appointment> all();

    Appointment updateStatus(Long appointmentId, String status);
}
