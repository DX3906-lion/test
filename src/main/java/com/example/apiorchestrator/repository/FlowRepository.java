package com.example.apiorchestrator.repository;

import com.example.apiorchestrator.entity.FlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowRepository extends JpaRepository<FlowEntity, Long> {
}
