package com.example.iz.parts.services.impl;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import com.example.iz.parts.services.OntologyService;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OntologyServiceImpl implements OntologyService {
    public static OWLOntologyManager manager;
    public static OWLOntology ontology;
    public static OWLReasoner reasoner;
    public OntologyServiceImpl() {
        manager = OWLManager.createOWLOntologyManager();
        LoadOntology(new File("data/ontology.owl"));
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        reasoner = reasonerFactory.createReasoner(ontology);
    }

    public void LoadOntology(File file) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology newOntology;
        try {
            ontology = manager.loadOntologyFromOntologyDocument(file);
//            System.out.print(ontology);
        } catch (OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
    }
}
