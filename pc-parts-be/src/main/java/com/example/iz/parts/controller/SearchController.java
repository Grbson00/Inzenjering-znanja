package com.example.iz.parts.controller;

import com.example.iz.parts.dto.search.*;
import com.example.iz.parts.model.*;
import com.example.iz.parts.services.impl.OntologyServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    OntologyServiceImpl service;

    @PostMapping("/gpu")
    public ResponseEntity<List<GPU>> FindGPU(@NonNull @RequestBody final GPUSearchDTO dto) {
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/cpu")
    public ResponseEntity<List<CPU>> FindCPU(@NonNull @RequestBody final CPUSearchDTO dto) {
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/motherboard")
    public ResponseEntity<List<MotherBoard>> FindMotherBoard(@NonNull @RequestBody final MotherBoardSearchDTO dto) {
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/ram")
    public ResponseEntity<List<RAM>> FindRAM(@NonNull @RequestBody final RAMSearchDTO dto) {
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping("/power")
    public ResponseEntity<List<PowerSupply>> FindPowerSupply(@NonNull @RequestBody final PSUSearchDTO dto) {
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/motherboard/all")
    public ResponseEntity<String> GetAllMotherboards() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#Motherboard");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instances of Motherboard found");
        }
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
        }
        System.out.println("-----------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/gpu/all")
    public ResponseEntity<String> GetAllGPU() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#GraphicsProcessingUnit");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instances of GPU found");
        }
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
        }
        System.out.println("-----------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/cpu/all")
    public ResponseEntity<String> GetAllCPU() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#CentralProcessingUnit");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instances of CPU found");
        }
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
        }
        System.out.println("-----------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/ram/all")
    public ResponseEntity<String> GetAllRAM() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#RandomAccessMemory");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instances of RAM found");
        }
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
        }
        System.out.println("-----------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/power/all")
    public ResponseEntity<String> GetAllPowersupply() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#PowerSupplyUnit");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        if (instances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instances of Power Supply found");
        }
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
        }
        System.out.println("-----------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
