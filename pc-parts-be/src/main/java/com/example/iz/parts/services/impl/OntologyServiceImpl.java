package com.example.iz.parts.services.impl;

import lombok.RequiredArgsConstructor;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import com.example.iz.parts.services.OntologyService;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class OntologyServiceImpl implements OntologyService {

    public OWLOntology LoadOntology(File file) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology newOntology;
        try {
            newOntology = manager.loadOntologyFromOntologyDocument(file);
            System.out.print(newOntology);
        } catch (OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
        return newOntology;
    }
}
