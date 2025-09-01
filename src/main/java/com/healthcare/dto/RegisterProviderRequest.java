package com.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterProviderRequest {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    @NotBlank private String password;
    private String specialization;
    private String phone;
    @NotBlank private String role; // DOCTOR / WELLNESS_PROVIDER

    public RegisterProviderRequest() {}
    // getters/setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
