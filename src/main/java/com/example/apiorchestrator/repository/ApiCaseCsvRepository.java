package com.example.apiorchestrator.repository;

import com.example.apiorchestrator.domain.ApiCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ApiCaseCsvRepository {
    private final Path csvPath;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ApiCaseCsvRepository(@Value("${storage.csv.path:data/api_cases.csv}") String csvFile) {
        this.csvPath = Paths.get(csvFile);
    }

    @PostConstruct
    public void init() throws IOException {
        if (csvPath.getParent() != null) {
            Files.createDirectories(csvPath.getParent());
        }
        if (!Files.exists(csvPath)) {
            Files.write(csvPath, new ArrayList<String>() {{ add("id,name,harSourceBase64,flowDefinitionBase64,createdAt,updatedAt"); }}, StandardCharsets.UTF_8);
        }
        for (ApiCase item : findAll()) {
            idGenerator.set(Math.max(idGenerator.get(), item.getId()));
        }
    }

    public synchronized ApiCase save(ApiCase apiCase) {
        LocalDateTime now = LocalDateTime.now();
        apiCase.setId(idGenerator.incrementAndGet());
        apiCase.setCreatedAt(now);
        apiCase.setUpdatedAt(now);

        String line = toLine(apiCase);
        try {
            Files.write(csvPath, (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("写入CSV失败", e);
        }
        return apiCase;
    }

    public synchronized List<ApiCase> findAll() {
        try {
            List<String> lines = Files.readAllLines(csvPath, StandardCharsets.UTF_8);
            List<ApiCase> result = new ArrayList<ApiCase>();
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue;
                result.add(fromLine(line));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("读取CSV失败", e);
        }
    }

    private String toLine(ApiCase c) {
        return c.getId() + "," + esc(c.getName()) + "," + b64(c.getHarSource()) + "," + b64(c.getFlowDefinition()) + "," + c.getCreatedAt() + "," + c.getUpdatedAt();
    }

    private ApiCase fromLine(String line) {
        String[] parts = line.split(",", 6);
        ApiCase c = new ApiCase();
        c.setId(Long.parseLong(parts[0]));
        c.setName(unesc(parts[1]));
        c.setHarSource(unb64(parts[2]));
        c.setFlowDefinition(unb64(parts[3]));
        c.setCreatedAt(LocalDateTime.parse(parts[4]));
        c.setUpdatedAt(LocalDateTime.parse(parts[5]));
        return c;
    }

    private String b64(String v) { return Base64.getEncoder().encodeToString((v == null ? "" : v).getBytes(StandardCharsets.UTF_8)); }
    private String unb64(String v) { return new String(Base64.getDecoder().decode(v), StandardCharsets.UTF_8); }
    private String esc(String v) { return (v == null ? "" : v).replace("\n", " ").replace("\r", " ").replace(",", " "); }
    private String unesc(String v) { return v; }
}
