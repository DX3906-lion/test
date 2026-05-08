package com.example.apiorchestrator.domain;

import java.time.LocalDateTime;

public class ApiCase {
    private Long id;
    private String name;
    private String harSource;
    private String flowDefinition;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHarSource() { return harSource; }
    public void setHarSource(String harSource) { this.harSource = harSource; }
    public String getFlowDefinition() { return flowDefinition; }
    public void setFlowDefinition(String flowDefinition) { this.flowDefinition = flowDefinition; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
