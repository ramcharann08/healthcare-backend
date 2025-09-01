package com.healthcare.dto;

public class AppointmentDto {
    private Long patientId;
    private Long providerId;
    private String appointmentDate; // yyyy-MM-ddTHH:mm
    private String notes;           // reason/notes
    private String status;          // for approve/reject

    public AppointmentDto() {}

    // Getters and Setters
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getProviderId() { return providerId; }
    public void setProviderId(Long providerId) { this.providerId = providerId; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
