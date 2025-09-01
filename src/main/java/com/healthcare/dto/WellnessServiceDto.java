package com.healthcare.dto;

import java.math.BigDecimal;

public class WellnessServiceDto {
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal fee;

    public WellnessServiceDto() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public BigDecimal getFee() { return fee; }
    public void setFee(BigDecimal fee) { this.fee = fee; }
}
