package com.example.apiorchestrator.repository;

import com.example.apiorchestrator.entity.ApiCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCaseRepository extends JpaRepository<ApiCaseEntity, Long> {
}
