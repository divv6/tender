package com.example.tenderhak.handler;

import com.example.tenderhak.model.*;
import com.example.tenderhak.repository.ErrorRepository;
import com.example.tenderhak.repository.Type;
import com.example.tenderhak.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetStatistichandler {

    private final ErrorRepository errorRepository;
    private final TypeRepository typeRepository;


    public ResponseEntity handle(StatisticRequest request) {
        LocalDateTime start = request.getStartDate().atStartOfDay();
        LocalDateTime end = request.getEndDate().atStartOfDay();
        Duration duration = Duration.between(start, end);
        int days = (int) duration.toDays();
        StatisticResponse response = new StatisticResponse();

        List<Chart1> chart1 = new ArrayList<>();
        for(int i = 0; i < ClassEnum.values().length; i++) {
            Chart1 chart = new Chart1();
            chart.setClassName(ClassEnum.values()[i]);
            List<Long> typeIds = typeRepository.findAllByClassType(ClassEnum.values()[i]).stream()
                    .map(Type::getId)
                    .collect(Collectors.toList());
            List<Integer> counts = new ArrayList<>();
            for(int j = 0; j <= days - 1; j++) {
                Integer count = errorRepository.findCountFromErrorTypeIdBetweenDate(typeIds, start.plusDays(j), start.plusDays(j).plusDays(1));
                counts.add(count);
            }
            chart.setCounts(counts);
            chart1.add(chart);
        }
        response.setChart1(chart1);

        List<Chart2> chart2 = new ArrayList<>();
        for(int i = 0; i < ClassEnum.values().length; i++) {
            Chart2 chart = new Chart2();
            chart.setClassName(ClassEnum.values()[i]);
            List<ChartData> chartDataList =  new ArrayList<>();
            for(int j = 0; j < TypeEnum.values().length; j++) {
                ChartData chartData = new ChartData();
                chartData.setTypeName(TypeEnum.values()[j]);

                List<Long> typeIds = typeRepository.findAllByClassTypeAndType(ClassEnum.values()[i], TypeEnum.values()[j]).stream()
                        .map(Type::getId)
                        .collect(Collectors.toList());
                List<Integer> counts = new ArrayList<>();
                for(int k = 0; k <= days - 1; k++) {
                    Integer count = errorRepository.findCountFromErrorTypeIdBetweenDate(typeIds, start.plusDays(k), start.plusDays(k).plusDays(1));
                    counts.add(count);
                }
                chartData.setCounts(counts);
                chartDataList.add(chartData);
            }
            chart.setData(chartDataList);
            chart2.add(chart);
        }
        response.setChart2(chart2);

        List<Chart3> chart3 = new ArrayList<>();
        for(int i = 0; i < ClassEnum.values().length; i++) {
            Chart3 chart = new Chart3();
            chart.setClassName(ClassEnum.values()[i]);
            Integer sloved = typeRepository.findCountSloved(ClassEnum.values()[i].toString());
            chart.setSolved(sloved);
            Integer unresolved = typeRepository.findCountUnresolved(ClassEnum.values()[i].toString());
            chart.setUnresolved(unresolved);
            chart3.add(chart);
        }
        response.setChart3(chart3);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
