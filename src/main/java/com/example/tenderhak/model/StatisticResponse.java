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
public class StatisticResponse {

    private List<Chart1> chart1;
    private List<Chart2> chart2;
    private List<Chart3> chart3;

}
