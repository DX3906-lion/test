package com.example.apiorchestrator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "api_case")
public class ApiCaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 16)
    private String method;

    @Column(nullable = false, length = 1024)
    private String url;

    @Lob
    private String headersJson;

    @Lob
    private String bodyTemplate;

    @Lob
    private String assertionJson;
}
