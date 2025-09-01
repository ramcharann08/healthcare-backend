package com.healthcare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(optional=false)
    @JoinColumn(name = "service_id")
    private WellnessService service;

    @Column(nullable=false)
    private LocalDate startDate;

    @Column(nullable=false)
    private LocalDate endDate;

    private Integer progress; // 0-100

    public Enrollment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public WellnessService getService() { return service; }
    public void setService(WellnessService service) { this.service = service; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this.progress = progress; }
}
