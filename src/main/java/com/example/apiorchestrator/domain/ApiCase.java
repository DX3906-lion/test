package com.example.apiorchestrator.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_case")
public class ApiCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Lob
    @Column(name = "har_source")
    private String harSource;

    @Lob
    @Column(name = "flow_definition", nullable = false)
    private String flowDefinition;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHarSource() { return harSource; }
    public void setHarSource(String harSource) { this.harSource = harSource; }
    public String getFlowDefinition() { return flowDefinition; }
    public void setFlowDefinition(String flowDefinition) { this.flowDefinition = flowDefinition; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
