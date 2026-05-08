package com.example.apiorchestrator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlowDtos {
    public record CreateFlowRequest(
            @NotBlank String name,
            String description,
            @NotNull String flowDefinitionJson
    ) {}

    public record FlowResponse(Long id, String name, String description, String flowDefinitionJson) {}

    public record ExecuteFlowRequest(String runtimeVariablesJson) {}

    public record AiSuggestionResponse(Long suggestionId, Long flowId, String suggestionJson, boolean accepted) {}
}
