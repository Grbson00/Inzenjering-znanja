package com.example.iz.parts;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.iz.parts.controller")
public class PcPartsBeApplication {
	public static void main(String[] args) {

		SpringApplication.run(PcPartsBeApplication.class, args);
		System.setProperty("java.awt.headless", "false");
	}

}
