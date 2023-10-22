package com.example.tenderhak.controller;

import com.example.tenderhak.handler.*;
import com.example.tenderhak.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ErrorController {

    private final GetAllErrorsHandler getAllErrorsHandler;
    private final LoginHandler loginhandler;
    private final ChangeErrorTypeHandler changeErrorTypeHandler;
    private final GetErrorTypehandler getErrorTypehandler;
    private final GetStatistichandler getStatistichandler;

    @GetMapping("/api/get-errors")
    public ResponseEntity<List<ResponseErrorsData>> getErrors() {
        return getAllErrorsHandler.handle();
    }

    @PostMapping("/api/login")
    public ResponseEntity login(@RequestBody LoginModel request) {
        return loginhandler.handle(request);
    }

    @PostMapping("/api/error")
    public ResponseEntity changeErrorType(@RequestBody ChangeErrorTypeRequest request) {
        return changeErrorTypeHandler.handle(request);
    }

    @GetMapping("/api/error/{id}")
    public ResponseEntity getErrorType(@PathVariable Long id) {
        return getErrorTypehandler.handle(id);
    }

    @PostMapping("/api/error-statistic")
    public ResponseEntity statisticError(@RequestBody StatisticRequest request) {
        return getStatistichandler.handle(request);
    }
}
