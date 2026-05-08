package com.example.apiorchestrator.service;

import com.example.apiorchestrator.dto.FlowDtos;
import com.example.apiorchestrator.engine.FlowExecutionEngine;
import com.example.apiorchestrator.entity.FlowEntity;
import com.example.apiorchestrator.repository.FlowRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlowService {
    private final FlowRepository flowRepository;
    private final FlowExecutionEngine flowExecutionEngine;

    public FlowDtos.FlowResponse createFlow(FlowDtos.CreateFlowRequest request) {
        FlowEntity entity = new FlowEntity();
        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setFlowDefinitionJson(request.flowDefinitionJson());
        FlowEntity saved = flowRepository.save(entity);
        return new FlowDtos.FlowResponse(saved.getId(), saved.getName(), saved.getDescription(), saved.getFlowDefinitionJson());
    }

    public Map<String, Object> executeFlow(Long flowId, String runtimeVariablesJson) {
        FlowEntity flow = flowRepository.findById(flowId)
                .orElseThrow(() -> new IllegalArgumentException("Flow not found: " + flowId));
        return flowExecutionEngine.execute(flow, runtimeVariablesJson);
    }
}
