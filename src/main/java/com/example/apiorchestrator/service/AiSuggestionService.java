package com.example.apiorchestrator.service;

import com.example.apiorchestrator.dto.FlowDtos;
import com.example.apiorchestrator.entity.AiSuggestionEntity;
import com.example.apiorchestrator.repository.AiSuggestionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiSuggestionService {
    private final AiSuggestionRepository aiSuggestionRepository;

    public FlowDtos.AiSuggestionResponse generateSuggestion(Long flowId, String flowDefinitionJson) {
        // 占位 AI 建议，可替换成真实 LLM 调用
        String suggestion = "{\"flowId\":" + flowId + ",\"actions\":[\"extract common headers\",\"add retry controller\"]}";
        AiSuggestionEntity entity = new AiSuggestionEntity();
        entity.setFlowId(flowId);
        entity.setSuggestionJson(suggestion);
        entity.setAccepted(false);
        AiSuggestionEntity saved = aiSuggestionRepository.save(entity);
        return new FlowDtos.AiSuggestionResponse(saved.getId(), saved.getFlowId(), saved.getSuggestionJson(), saved.isAccepted());
    }

    public FlowDtos.AiSuggestionResponse acceptSuggestion(Long suggestionId) {
        AiSuggestionEntity entity = aiSuggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new IllegalArgumentException("Suggestion not found: " + suggestionId));
        entity.setAccepted(true);
        AiSuggestionEntity saved = aiSuggestionRepository.save(entity);
        return new FlowDtos.AiSuggestionResponse(saved.getId(), saved.getFlowId(), saved.getSuggestionJson(), saved.isAccepted());
    }

    public List<FlowDtos.AiSuggestionResponse> listByFlowId(Long flowId) {
        return aiSuggestionRepository.findByFlowId(flowId).stream()
                .map(it -> new FlowDtos.AiSuggestionResponse(it.getId(), it.getFlowId(), it.getSuggestionJson(), it.isAccepted()))
                .collect(Collectors.toList());
    }
}
