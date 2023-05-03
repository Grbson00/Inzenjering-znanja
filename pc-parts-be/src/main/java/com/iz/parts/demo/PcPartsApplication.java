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

	public static void main(String[] args) {
		System.out.print("Test");
		SpringApplication.run(PcPartsApplication.class, args);
		LoadOntology(new File("data/ontology.owl"));
		System.out.print("Done");
	}

	public static OWLOntology LoadOntology(File file) {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = null;
		try {
			ontology = manager.loadOntologyFromOntologyDocument(file);
			System.out.print(ontology);
		} catch (OWLOntologyCreationException e) {
			throw new RuntimeException(e);
		}
		return ontology;
	}

}
