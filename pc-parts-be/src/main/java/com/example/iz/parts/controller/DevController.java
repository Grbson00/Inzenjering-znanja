package com.example.iz.parts.controller;

import com.example.iz.parts.services.impl.OntologyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/dev")
public class DevController {
    @Autowired
    OntologyServiceImpl service;

    @GetMapping("/test")
    public ResponseEntity Test() {
        //returns null
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/onto")
    public ResponseEntity Onto() {
        //returns null and console logs ontology
        service.LoadOntology(new File("data/ontology.owl"));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
