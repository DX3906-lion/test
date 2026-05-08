package com.example.apiorchestrator.repository;

import com.example.apiorchestrator.domain.ApiCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCaseRepository extends JpaRepository<ApiCase, Long> {
}
