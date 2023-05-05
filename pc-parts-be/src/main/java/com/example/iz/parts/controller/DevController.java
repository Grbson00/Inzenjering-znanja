package com.example.iz.parts.controller;

import com.example.iz.parts.services.impl.OntologyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Set;

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
        OWLOntology ontology =  service.LoadOntology(new File("data/pcpartv2.owl"));

        // Get the class "Motherboard"
        OWLClass motherboardClass = service.manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/pcpartv2#Motherboard");
        //OWLClass motherboardClass = ontology.getOWLOntologyManager().getOWLDataFactory().getOWLClass(IRI.create(ontology.getOntologyID().getOntologyIRI() + "#Motherboard"));

        // Get all instances of the class "Motherboard"
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        Set<OWLNamedIndividual> instances = reasoner.getInstances(motherboardClass, false).getFlattened();
        if (instances.isEmpty()) {
            System.out.println("No instances of Motherboard found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // Print the instances
        for (OWLNamedIndividual instance : instances) {
            System.out.println(instance.getIRI().getFragment());
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


//    @GetMapping("/onto")
//    public ResponseEntity Onto() {
//        OWLOntology ontology =  service.LoadOntology(new File("data/pcpartv2.owl"));
//
//        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
//        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
//
//        // Get all classes in the ontology
//        Set<OWLClass> classes = ontology.getClassesInSignature();
//
//        // Loop through each class and get its instances
//        for (OWLClass cls : classes) {
//            Set<OWLNamedIndividual> instances = reasoner.getInstances(cls, false).getFlattened();
//            System.out.println("Instances of class " + cls.getIRI().getFragment() + ":");
//            System.out.println("IRI: " + cls.getIRI() + ":");
//            for (OWLNamedIndividual instance : instances) {
//                System.out.println(instance.getIRI().getFragment());
//            }
//            System.out.println("-----------------------------------------------------------------");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
}
