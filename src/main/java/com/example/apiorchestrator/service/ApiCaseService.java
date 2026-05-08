package com.example.apiorchestrator.service;

import com.example.apiorchestrator.domain.ApiCase;
import com.example.apiorchestrator.dto.CreateCaseRequest;
import com.example.apiorchestrator.repository.ApiCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCaseService {
    private final ApiCaseRepository apiCaseRepository;

    public ApiCaseService(ApiCaseRepository apiCaseRepository) {
        this.apiCaseRepository = apiCaseRepository;
    }

    public ApiCase create(CreateCaseRequest req) {
        ApiCase apiCase = new ApiCase();
        apiCase.setName(req.getName());
        apiCase.setHarSource(req.getHarSource());
        apiCase.setFlowDefinition(req.getFlowDefinition());
        return apiCaseRepository.save(apiCase);
    }

    public List<ApiCase> list() {
        return apiCaseRepository.findAll();
    }
}
