package com.example.tenderhak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chart3 {

    private ClassEnum className;
    private Integer solved;
    private Integer unresolved;
}
