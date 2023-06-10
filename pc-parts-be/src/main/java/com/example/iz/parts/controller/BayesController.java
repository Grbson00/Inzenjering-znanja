package com.example.iz.parts.controller;

import com.example.iz.parts.dto.bayes.BayesDTO;
import com.example.iz.parts.dto.bayes.BayesReturnDTO;
import com.example.iz.parts.services.BayesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/bayes")
public class BayesController {
    @Autowired
    private BayesService bayesService;

    @PostMapping("/")
    public ResponseEntity<ArrayList<BayesReturnDTO>> GetErrorCause(@RequestBody BayesDTO dto) {
        System.out.println(dto);
        try {
            ArrayList<BayesReturnDTO> retList = bayesService.GetErrorCause(dto);
            return ResponseEntity.status(HttpStatus.OK).body(retList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<ArrayList<String>> GetProblemCause() throws IOException {

            ArrayList<String> retList = bayesService.GetProblemCause();
            return ResponseEntity.status(HttpStatus.OK).body(retList);

    }
}
