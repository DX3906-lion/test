package com.example.apiorchestrator.repository;

import com.example.apiorchestrator.entity.AiSuggestionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiSuggestionRepository extends JpaRepository<AiSuggestionEntity, Long> {
    List<AiSuggestionEntity> findByFlowId(Long flowId);
}
