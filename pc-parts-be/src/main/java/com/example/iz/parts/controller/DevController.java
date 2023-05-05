package com.example.iz.parts.controller;

import com.example.iz.parts.services.impl.OntologyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/dev")
public class DevController {
    @Autowired
    OntologyServiceImpl service;

    @GetMapping("/test")
    public ResponseEntity<String> Test() {
        service.FindCpu();
        return ResponseEntity.status(HttpStatus.OK).body("Test");
    }

    //USED FOR FINDING IRIs OF ONTOLOGY CLASSES WHEN UNSURE, WILL LIST OUT ALL CLASSES WITH THEIR IRIs
    @GetMapping("/iri/all")
    public ResponseEntity<String> Onto() {
        Set<OWLClass> classes = service.ontology.getClassesInSignature();

        for (OWLClass cls : classes) {
            Set<OWLNamedIndividual> instances = service.reasoner.getInstances(cls, false).getFlattened();
            System.out.println("Instances of class " + cls.getIRI().getFragment() + ":");
            System.out.println("IRI: " + cls.getIRI() + ":");
            for (OWLNamedIndividual instance : instances) {
                System.out.println(instance.getIRI().getFragment());
            }
            System.out.println("-----------------------------------------------------------------");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Check System.Out!");
    }


}
