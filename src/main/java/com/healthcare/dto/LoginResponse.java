package com.healthcare.dto;

public class LoginResponse {
    private String token;
    private String role;
    private Long patientId;
    private Long providerId;

    public LoginResponse(String token, String role, Long patientId, Long providerId) {
        this.token = token;
        this.role = role;
        this.patientId = patientId;
        this.providerId = providerId;
    }

    public String getToken() { return token; }
    public String getRole() { return role; }
    public Long getPatientId() { return patientId; }
    public Long getProviderId() { return providerId; }
}
