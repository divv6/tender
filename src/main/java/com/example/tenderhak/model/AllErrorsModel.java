package com.example.tenderhak.model;

import com.example.tenderhak.repository.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllErrorsModel {

    private ClassEnum classType;
    private TypeEnum type;
    private String key;
    private Integer count;
}
