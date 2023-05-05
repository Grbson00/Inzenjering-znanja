package com.example.iz.parts.services.impl;

import com.example.iz.parts.dto.search.CPUSearchDTO;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import com.example.iz.parts.services.OntologyService;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.vocab.OWLFacet;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OntologyServiceImpl implements OntologyService {
    public static OWLOntologyManager manager;
    public static OWLOntology ontology;
    public static OWLReasoner reasoner;
    public static OWLDataFactory dataFactory;
    public OntologyServiceImpl() {
        manager = OWLManager.createOWLOntologyManager();
        LoadOntology(new File("data/ontology.owl"));
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        reasoner = reasonerFactory.createReasoner(ontology);
        dataFactory = manager.getOWLDataFactory();
    }

    public void LoadOntology(File file) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        try {
            ontology = manager.loadOntologyFromOntologyDocument(file);
//            System.out.print(ontology);
        } catch (OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> FindCpu(CPUSearchDTO dto) {
        List<String> retList = new ArrayList<String>();

        // Get the data properties to query
        OWLDataProperty cpuCoreNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCoreNumber"));
        OWLDataProperty cpuThreadNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuThreadNumber"));
        OWLDataProperty cpuCache = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCache"));
        OWLDataProperty cpuSpeed = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuClockSpeed"));

        OWLClass cpuClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#CentralProcessingUnit");

        OWLDataRange coresRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(dto.getCoreNumberFrom())),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(dto.getCoreNumberTo())));

        OWLDataRange threadsRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(dto.getThreadNumberFrom())),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(dto.getThreadNumberTo())));

        OWLDataRange cacheRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(dto.getCacheMemoryFrom())),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(dto.getCacheMemoryTo())));

        OWLDataRange speedRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(dto.getFromSpeed())),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(dto.getToSpeed())));

        //TODO: match manufacturer string and return speed once speed is added to ontology
        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                cpuClass,
                dataFactory.getOWLDataSomeValuesFrom(cpuCoreNumber, coresRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuThreadNumber, threadsRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuCache, cacheRange)
//                dataFactory.getOWLDataSomeValuesFrom(cpuSpeed, speedRange)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();

        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }
}


//    public void FindCpu() {
//        // Get the data properties to query
//        OWLDataProperty cpuCoreNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCoreNumber"));
//        OWLDataProperty cpuThreadNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuThreadNumber"));
//        OWLDataProperty cpuCache = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCache"));
//
//        // Set the values for the data properties
//        OWLLiteral coreNumberValue = manager.getOWLDataFactory().getOWLLiteral(4);
//        OWLLiteral threadNumberValue = manager.getOWLDataFactory().getOWLLiteral(4);
//        OWLLiteral cacheValue = manager.getOWLDataFactory().getOWLLiteral(4);
//
//        // Create a SPARQL query to find individuals with the specified data property values
////        String sparqlQuery = "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
////                "SELECT ?individual\n" +
////                "WHERE {\n" +
////                "  ?individual rdf:type <http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#CentralProcessingUnit> .\n" +
////                "  ?individual <http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCoreNumber> ?coreNumber .\n" +
////                "  ?individual <http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuThreadNumber> ?threadNumber .\n" +
////                "  ?individual <http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCache> ?cache .\n" +
////                "  FILTER (?coreNumber = " + coreNumberValue.getLiteral() + "^^xsd:int && " +
////                "?threadNumber = " + threadNumberValue.getLiteral() + "^^xsd:int && " +
////                "?cache < " + cacheValue.getLiteral() + "^^xsd:int)\n" +
////                "}";
//
//        Set<OWLNamedIndividual> individuals = reasoner.getInstances(
//                manager.getOWLDataFactory().getOWLClass(IRI.create(
//                        "http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#CentralProcessingUnit")), false).getFlattened();
//
//        // Filter the individuals based on the data properties
//        Set<OWLNamedIndividual> filteredIndividuals = individuals.stream()
//                .filter(i -> reasoner.getDataPropertyValues(i, cpuCoreNumber).contains(coreNumberValue)
//                        && reasoner.getDataPropertyValues(i, cpuThreadNumber).contains(threadNumberValue)
//                        && reasoner.getDataPropertyValues(i, cpuCache).stream()
//                        .anyMatch(value -> value.parseInteger() <= cacheValue.parseInteger()))
//                .collect(Collectors.toSet());
//
//        if(filteredIndividuals.isEmpty()) System.out.println("None found");
//
//        // Print the individuals
//        for (OWLNamedIndividual individual : filteredIndividuals) {
//            System.out.println(individual.getIRI());
//        }
//
//    }