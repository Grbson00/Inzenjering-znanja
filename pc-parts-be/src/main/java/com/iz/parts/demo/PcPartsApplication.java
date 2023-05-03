package com.iz.parts.demo;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class PcPartsApplication {
	public static OWLOntology activeOntology;
	public static void main(String[] args) {
		SpringApplication.run(PcPartsApplication.class, args);
		activeOntology =  LoadOntology(new File("data/ontology.owl"));
	}

	public static OWLOntology LoadOntology(File file) {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = null;
		try {
			ontology = manager.loadOntologyFromOntologyDocument(file);
//			System.out.print(ontology);
		} catch (OWLOntologyCreationException e) {
			throw new RuntimeException(e);
		}
		return ontology;
	}

}
