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
import java.util.ArrayList;
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
    public ResponseEntity<List<String>> FindCPU(@NonNull @RequestBody final CPUSearchDTO dto) {
        System.out.println(dto);
        return ResponseEntity.status(HttpStatus.OK).body(service.FindCpu(dto));
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
    public ResponseEntity<List<String>> GetAllMotherboards() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#Motherboard");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        List<String> returnInstances = new ArrayList<>();

        if (instances.isEmpty()) {
            List<String> errorList = new ArrayList<>();
            errorList.add("No instances of MOTHERBOARD found");
            System.out.println("-----------------------------");
            System.out.println("No instances of MOTHERBOARD found");
            System.out.println("-----------------------------");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorList);
        }
        System.out.println("--------------MTB---------------");
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
            returnInstances.add(instance.getIRI().getFragment());
        }
        System.out.println("--------------MTB---------------");
        return ResponseEntity.status(HttpStatus.OK).body(returnInstances);
    }
    @GetMapping("/gpu/all")
    public ResponseEntity<List<String>> GetAllGPU() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#GraphicsProcessingUnit");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        List<String> returnInstances = new ArrayList<>();

        if (instances.isEmpty()) {
            List<String> errorList = new ArrayList<>();
            errorList.add("No instances of GPU found");
            System.out.println("-----------------------------");
            System.out.println("No instances of GPU found");
            System.out.println("-----------------------------");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorList);
        }
        System.out.println("--------------GPU---------------");
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
            returnInstances.add(instance.getIRI().getFragment());
        }
        System.out.println("--------------GPU---------------");
        return ResponseEntity.status(HttpStatus.OK).body(returnInstances);
    }
    @GetMapping("/cpu/all")
    public ResponseEntity<List<String>> GetAllCPU() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#CentralProcessingUnit");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        List<String> returnInstances = new ArrayList<>();

        if (instances.isEmpty()) {
            List<String> errorList = new ArrayList<>();
            errorList.add("No instances of CPU found");
            System.out.println("-----------------------------");
            System.out.println("No instances of CPU found");
            System.out.println("-----------------------------");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorList);
        }
        System.out.println("--------------CPU---------------");
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
            returnInstances.add(instance.getIRI().getFragment());
        }
        System.out.println("--------------CPU---------------");
        return ResponseEntity.status(HttpStatus.OK).body(returnInstances);
    }
    @GetMapping("/ram/all")
    public ResponseEntity<List<String>> GetAllRAM() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#RandomAccessMemory");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        List<String> returnInstances = new ArrayList<>();

        if (instances.isEmpty()) {
            List<String> errorList = new ArrayList<>();
            errorList.add("No instances of RAM found");
            System.out.println("-----------------------------");
            System.out.println("No instances of RAM found");
            System.out.println("-----------------------------");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorList);
        }
        System.out.println("--------------RAM---------------");
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
            returnInstances.add(instance.getIRI().getFragment());
        }
        System.out.println("--------------RAM---------------");
        return ResponseEntity.status(HttpStatus.OK).body(returnInstances);
    }
    @GetMapping("/power/all")
    public ResponseEntity<List<String>> GetAllPowersupply() {
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#PowerSupplyUnit");
        Set<OWLNamedIndividual> instances = service.reasoner.getInstances(motherboardClass, false).getFlattened();
        List<String> returnInstances = new ArrayList<>();

        if (instances.isEmpty()) {
            List<String> errorList = new ArrayList<>();
            errorList.add("No instances of PSU found");
            System.out.println("-----------------------------");
            System.out.println("No instances of PSU found");
            System.out.println("-----------------------------");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorList);
        }
        System.out.println("--------------PSU---------------");
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
            returnInstances.add(instance.getIRI().getFragment());
        }
        System.out.println("--------------PSU---------------");
        return ResponseEntity.status(HttpStatus.OK).body(returnInstances);
    }

    @GetMapping("/upgrade/{toUpgrade}/{motherboard}/{cpu}/{gpu}/{ram}/{psu}")
    public ResponseEntity<?> getUpgrade(
            @PathVariable(value = "toUpgrade", required = true) String toUpgrade,
            @PathVariable(value = "motherboard", required = true) String motherboard,
            @PathVariable(value = "cpu") String cpu,
            @PathVariable(value = "gpu") String gpu,
            @PathVariable(value = "ram") String ram,
            @PathVariable(value = "psu") String psu) {
        if (toUpgrade.equals("CPU"))
            return  ResponseEntity.status(HttpStatus.OK).body(service.UpgradeCPU(motherboard, cpu));
        else if (toUpgrade.equals("GPU"))
            return  ResponseEntity.status(HttpStatus.OK).body(service.UpgradeGPU(motherboard, gpu));
        else if (toUpgrade.equals("RAM"))
            return  ResponseEntity.status(HttpStatus.OK).body(service.UpgradeRAM(motherboard, ram));
        else if (toUpgrade.equals("PSU"))
            return  ResponseEntity.status(HttpStatus.OK).body(service.UpgradePSU(motherboard, psu));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ne valja");
    }


}
