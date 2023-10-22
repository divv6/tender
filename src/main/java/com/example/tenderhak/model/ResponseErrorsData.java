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
public class ResponseErrorsData {

    private ClassEnum classEnum;
    private String classDescription;
    private List<ErrorModel> errorModelList;
}
