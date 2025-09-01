package com.healthcare.repository;

import com.healthcare.entity.WellnessService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WellnessServiceRepository extends JpaRepository<WellnessService, Long> {}
