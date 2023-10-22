package com.example.tenderhak.handler;

import com.example.tenderhak.model.GetTypeResponse;
import com.example.tenderhak.repository.DescriptionRepository;
import com.example.tenderhak.repository.ErrorRepository;
import com.example.tenderhak.repository.Type;
import com.example.tenderhak.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetErrorTypehandler {

    private final TypeRepository typeRepository;
    private final ErrorRepository errorRepository;
    private final DescriptionRepository descriptionRepository;

    public ResponseEntity handle(Long id) {
        Type type = typeRepository.findById(id).orElse(null);
        if(type == null) {
            return new ResponseEntity<>("TypeError не найден", HttpStatus.NOT_FOUND);
        }
        GetTypeResponse response = GetTypeResponse.builder()
                .id(type.getId())
                .classType(type.getClassType())
                .classDescription(descriptionRepository.findByTitle(type.getClassType().toString()).getDescription())
                .type(type.getType())
                .typeDescription(descriptionRepository.findByTitle(type.getType().toString()).getDescription())
                .key(type.getKey())
                .title(type.getTitle())
                .description(type.getDescription())
                .solution(type.getSolution())
                .status(type.getStatus())
                .build();
        LocalDateTime[] dates = errorRepository.findLast100Dates(type.getId());
        response.setDates(dates);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
