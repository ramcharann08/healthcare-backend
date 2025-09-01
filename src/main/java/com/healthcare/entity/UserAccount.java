package com.healthcare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password; // BCrypt hashed

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    private Long linkedPatientId;
    private Long linkedProviderId;

    public UserAccount() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Long getLinkedPatientId() { return linkedPatientId; }
    public void setLinkedPatientId(Long linkedPatientId) { this.linkedPatientId = linkedPatientId; }

    public Long getLinkedProviderId() { return linkedProviderId; }
    public void setLinkedProviderId(Long linkedProviderId) { this.linkedProviderId = linkedProviderId; }

    public enum Role { PATIENT, PROVIDER, ADMIN }
}
