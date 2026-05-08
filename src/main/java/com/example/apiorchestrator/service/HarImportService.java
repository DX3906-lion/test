package com.example.apiorchestrator.service;

import com.example.apiorchestrator.entity.ApiCaseEntity;
import com.example.apiorchestrator.repository.ApiCaseRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HarImportService {
    private final ApiCaseRepository apiCaseRepository;

    public List<ApiCaseEntity> importFromHar(String harJson) {
        // 占位实现：真实场景应解析 HAR 并抽取 entries/request/response.
        ApiCaseEntity sample = new ApiCaseEntity();
        sample.setName("imported-from-har");
        sample.setMethod("GET");
        sample.setUrl("https://example.org/placeholder");
        sample.setHeadersJson("{}");
        sample.setBodyTemplate("");
        sample.setAssertionJson("{\"status\":200}");
        ApiCaseEntity saved = apiCaseRepository.save(sample);
        List<ApiCaseEntity> result = new ArrayList<>();
        result.add(saved);
        return result;
    }
}
