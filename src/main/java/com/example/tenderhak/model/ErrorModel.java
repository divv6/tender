package com.example.tenderhak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {

    private Long id;
    private TypeEnum type;
    private String typeDescription;
    private String key;
    private String title;
    private String description;
    private String solution;
    private ErrorStatus status;
    private Integer count;
    private LocalDateTime lastDate;
}
