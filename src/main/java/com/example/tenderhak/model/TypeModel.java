package com.example.tenderhak.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeModel {

    private Long id;
    @Enumerated(EnumType.STRING)
    private ClassEnum classType;
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    private String key;
    private String title;
    private String description;
    private String solution;
    @Enumerated(EnumType.STRING)
    private ErrorStatus status;
    private LocalDateTime lastDate;
}
