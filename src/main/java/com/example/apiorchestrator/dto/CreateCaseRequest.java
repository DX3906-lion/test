package com.example.apiorchestrator.dto;

import javax.validation.constraints.NotBlank;

public class CreateCaseRequest {
    @NotBlank
    private String name;
    private String harSource;
    @NotBlank
    private String flowDefinition;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHarSource() { return harSource; }
    public void setHarSource(String harSource) { this.harSource = harSource; }
    public String getFlowDefinition() { return flowDefinition; }
    public void setFlowDefinition(String flowDefinition) { this.flowDefinition = flowDefinition; }
}
