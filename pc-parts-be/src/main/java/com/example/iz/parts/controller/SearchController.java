package com.example.iz.parts.controller;

import com.example.iz.parts.dto.search.*;
import com.example.iz.parts.model.*;
import com.example.iz.parts.services.impl.OntologyServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    OntologyServiceImpl service;

    @PostMapping("/gpu")
    public ResponseEntity<List<GPU>> FindGPU(@NonNull @RequestBody final GPUSearchDTO dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/cpu")
    public ResponseEntity<List<CPU>> FindCPU(@NonNull @RequestBody final CPUSearchDTO dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/motherboard")
    public ResponseEntity<List<MotherBoard>> FindMotherBoard(@NonNull @RequestBody final MotherBoardSearchDTO dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/ram")
    public ResponseEntity<List<RAM>> FindRAM(@NonNull @RequestBody final RAMSearchDTO dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/power")
    public ResponseEntity<List<PowerSupply>> FindPowerSupply(@NonNull @RequestBody final PSUSearchDTO dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
