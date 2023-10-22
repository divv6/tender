package com.example.tenderhak.controller;

import com.example.tenderhak.handler.ParseXMLHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParseController {

    private final ParseXMLHandler parseXmlHandler;

    @PostMapping("/api/addToDb")
    public ResponseEntity createAccount() {
        return parseXmlHandler.handle();
    }
}
