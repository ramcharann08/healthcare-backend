package com.healthcare.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private WellnessService service;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status paymentStatus = Status.PENDING;

    private LocalDateTime paymentDate = LocalDateTime.now();

    private String transactionId;

    public Payment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public WellnessService getService() { return service; }
    public void setService(WellnessService service) { this.service = service; }

    public Status getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(Status paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public enum Status { PENDING, SUCCESS, FAILED }
}
