package com.example.apiorchestrator.controller;

import com.example.apiorchestrator.domain.ApiCase;
import com.example.apiorchestrator.dto.CreateCaseRequest;
import com.example.apiorchestrator.service.ApiCaseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class ApiCaseController {
    private final ApiCaseService apiCaseService;

    public ApiCaseController(ApiCaseService apiCaseService) {
        this.apiCaseService = apiCaseService;
    }

    @PostMapping
    public ApiCase create(@Validated @RequestBody CreateCaseRequest request) {
        return apiCaseService.create(request);
    }

    @GetMapping
    public List<ApiCase> list() {
        return apiCaseService.list();
    }
}
