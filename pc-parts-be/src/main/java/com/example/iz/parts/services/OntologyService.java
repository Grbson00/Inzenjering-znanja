package com.example.iz.parts.services;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;

import java.io.File;

public interface OntologyService {
    OWLOntology LoadOntology(File file);
    OWLOntology LoadOntologyWeb(IRI iri);
}
