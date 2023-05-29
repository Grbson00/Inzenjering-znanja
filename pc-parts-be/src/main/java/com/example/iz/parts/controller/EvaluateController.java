package com.example.iz.parts.controller;

import com.example.iz.parts.services.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluationService service;

    @GetMapping("/test")
    public ResponseEntity<String> Test() {
        service.Evaluate();
        return ResponseEntity.status(HttpStatus.OK).body("Test");
    }
}
