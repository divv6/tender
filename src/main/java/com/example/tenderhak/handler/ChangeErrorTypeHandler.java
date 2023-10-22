package com.example.tenderhak.handler;

import com.example.tenderhak.model.ChangeErrorTypeRequest;
import com.example.tenderhak.model.GetTypeResponse;
import com.example.tenderhak.model.TypeModel;
import com.example.tenderhak.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChangeErrorTypeHandler {

    private final TypeRepository typeRepository;
    private final DescriptionRepository descriptionRepository;
    private final ErrorRepository errorRepository;

    public ResponseEntity handle(ChangeErrorTypeRequest request) {
        Type type = typeRepository.findById(request.getId()).orElse(null);
        if(type == null) {
            return new ResponseEntity<>("TypeError не найден", HttpStatus.NOT_FOUND);
        }
        type.setTitle(request.getTitle());
        type.setDescription(request.getDescription());
        type.setSolution(request.getSolution());
        type = typeRepository.save(type);
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
