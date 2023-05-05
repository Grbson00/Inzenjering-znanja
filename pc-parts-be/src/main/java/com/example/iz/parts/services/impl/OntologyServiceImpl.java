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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
