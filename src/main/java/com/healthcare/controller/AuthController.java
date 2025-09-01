package com.healthcare.controller;

import com.healthcare.dto.*;
import com.healthcare.entity.*;
import com.healthcare.repository.*;
import com.healthcare.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserAccountRepository userRepo;
    private final PatientRepository patientRepo;
    private final ProviderRepository providerRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthController(UserAccountRepository userRepo, PatientRepository patientRepo,
                          ProviderRepository providerRepo, PasswordEncoder encoder, JwtService jwt) {
        this.userRepo = userRepo;
        this.patientRepo = patientRepo;
        this.providerRepo = providerRepo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    // ✅ Register Patient
    @PostMapping("/register/patient")
    public ResponseEntity<?> registerPatient(@Valid @RequestBody RegisterPatientRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        Patient p = new Patient();
        p.setName(req.getName());
        p.setEmail(req.getEmail());
        p.setPhone(req.getPhone());
        p.setAddress(req.getAddress());
        if (req.getDob() != null && !req.getDob().isEmpty()) {
            p.setDob(LocalDate.parse(req.getDob()));
        }
        if (req.getGender() != null && !req.getGender().isEmpty()) {
            p.setGender(Patient.Gender.valueOf(req.getGender().toUpperCase()));
        }
        p = patientRepo.save(p);

        UserAccount ua = new UserAccount();
        ua.setEmail(req.getEmail());
        ua.setPassword(encoder.encode(req.getPassword()));
        ua.setRole(UserAccount.Role.PATIENT);
        ua.setLinkedPatientId(p.getId());
        userRepo.save(ua);

        return ResponseEntity.ok(p);
    }

    // ✅ Register Provider
    @PostMapping("/register/provider")
    public ResponseEntity<?> registerProvider(@Valid @RequestBody RegisterProviderRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }

        Provider pr = new Provider();
        pr.setName(req.getName());
        pr.setEmail(req.getEmail());
        pr.setPhone(req.getPhone());
        pr.setSpecialization(req.getSpecialization());
        pr.setRole(Provider.Role.valueOf(req.getRole().toUpperCase())); // DOCTOR / WELLNESS_PROVIDER
        pr = providerRepo.save(pr);

        UserAccount ua = new UserAccount();
        ua.setEmail(req.getEmail());
        ua.setPassword(encoder.encode(req.getPassword()));
        ua.setRole(UserAccount.Role.PROVIDER); // ← This stays PROVIDER
        ua.setLinkedProviderId(pr.getId());
        userRepo.save(ua);

        return ResponseEntity.ok(pr);
    }

    // ✅ Login (returns ID also!)
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        UserAccount u = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        String token = jwt.generate(u.getEmail());

        // 👇 Include linked ID so frontend doesn’t hardcode it
        return ResponseEntity.ok(new LoginResponse(
                token,
                u.getRole().name(),
                u.getLinkedPatientId(),
                u.getLinkedProviderId()
        ));
    }
}
