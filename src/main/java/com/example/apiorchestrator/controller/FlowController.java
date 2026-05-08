package com.example.apiorchestrator.controller;

import com.example.apiorchestrator.dto.FlowDtos;
import com.example.apiorchestrator.service.AiSuggestionService;
import com.example.apiorchestrator.service.FlowService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flows")
@RequiredArgsConstructor
public class FlowController {
    private final FlowService flowService;
    private final AiSuggestionService aiSuggestionService;

    @PostMapping
    public FlowDtos.FlowResponse createFlow(@Valid @RequestBody FlowDtos.CreateFlowRequest request) {
        return flowService.createFlow(request);
    }

    @PostMapping("/{flowId}/execute")
    public Map<String, Object> executeFlow(@PathVariable Long flowId, @RequestBody(required = false) FlowDtos.ExecuteFlowRequest request) {
        String runtime = request == null ? "{}" : request.runtimeVariablesJson();
        return flowService.executeFlow(flowId, runtime);
    }

    @PostMapping("/{flowId}/ai-suggestions")
    public FlowDtos.AiSuggestionResponse generate(@PathVariable Long flowId, @RequestBody String flowDefinitionJson) {
        return aiSuggestionService.generateSuggestion(flowId, flowDefinitionJson);
    }

    @GetMapping("/{flowId}/ai-suggestions")
    public List<FlowDtos.AiSuggestionResponse> list(@PathVariable Long flowId) {
        return aiSuggestionService.listByFlowId(flowId);
    }

    @PostMapping("/ai-suggestions/{suggestionId}/accept")
    public FlowDtos.AiSuggestionResponse accept(@PathVariable Long suggestionId) {
        return aiSuggestionService.acceptSuggestion(suggestionId);
    }
}
