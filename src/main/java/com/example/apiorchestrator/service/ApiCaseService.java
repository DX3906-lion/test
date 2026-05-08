package com.example.apiorchestrator.service;

import com.example.apiorchestrator.domain.ApiCase;
import com.example.apiorchestrator.dto.CreateCaseRequest;
import com.example.apiorchestrator.repository.ApiCaseCsvRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCaseService {
    private final ApiCaseCsvRepository repository;

    public ApiCaseService(ApiCaseCsvRepository repository) {
        this.repository = repository;
    }

    public ApiCase create(CreateCaseRequest req) {
        ApiCase apiCase = new ApiCase();
        apiCase.setName(req.getName());
        apiCase.setHarSource(req.getHarSource());
        apiCase.setFlowDefinition(req.getFlowDefinition());
        return repository.save(apiCase);
    }

    public List<ApiCase> list() {
        return repository.findAll();
    }
}
