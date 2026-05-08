package com.example.apiorchestrator.controller;

import com.example.apiorchestrator.entity.ApiCaseEntity;
import com.example.apiorchestrator.service.HarImportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/har")
@RequiredArgsConstructor
public class HarController {
    private final HarImportService harImportService;

    @PostMapping("/import")
    public List<ApiCaseEntity> importHar(@RequestBody String harJson) {
        return harImportService.importFromHar(harJson);
    }
}
