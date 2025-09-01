package com.healthcare.security;

import com.healthcare.entity.UserAccount;
import com.healthcare.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository repo;

    public CustomUserDetailsService(UserAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount u = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // roles should be without "ROLE_" prefix here; Spring will add ROLE_ when using hasRole/hasAuthority
        return User.withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRole().name())
                .build();
    }
}
