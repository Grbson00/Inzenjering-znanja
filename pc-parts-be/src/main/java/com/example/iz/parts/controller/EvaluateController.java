package com.example.iz.parts.controller;

import com.example.iz.parts.dto.evaluate.EvaluateDTO;
import com.example.iz.parts.services.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluationService service;

    @PostMapping("/")
    public ResponseEntity<String> EvaluatePC(@Validated @RequestBody EvaluateDTO dto) {
        System.out.println(dto);
        service.Evaluate(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Test");
    }
}
