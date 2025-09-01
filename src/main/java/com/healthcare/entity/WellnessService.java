package com.healthcare.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wellness_services")
public class WellnessService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Lob
    private String description;

    private Integer duration; // days

    @Column(nullable=false, precision=10, scale=2)
    private BigDecimal fee;

    public WellnessService() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }
}
