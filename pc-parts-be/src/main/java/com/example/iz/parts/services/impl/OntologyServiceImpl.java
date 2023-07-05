package com.example.iz.parts.services.impl;

import com.example.iz.parts.dto.SimilarPCDTO;
import com.example.iz.parts.dto.search.CPUSearchDTO;
import com.example.iz.parts.dto.search.GPUSearchDTO;
import com.example.iz.parts.dto.search.PSUSearchDTO;
import com.example.iz.parts.dto.search.RAMSearchDTO;
import com.example.iz.parts.model.PC;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import com.example.iz.parts.services.OntologyService;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;
import org.semanticweb.owlapi.vocab.XSDVocabulary;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.sql.ResultSet;
import java.util.Optional;
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
            System.out.print(ontology);
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

        OWLDataRange speedRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getOWLDatatype(XSDVocabulary.DECIMAL.getIRI()),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(String.valueOf(dto.getFromSpeed()), dataFactory.getOWLDatatype(XSDVocabulary.DECIMAL.getIRI()))),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(String.valueOf(dto.getToSpeed()), dataFactory.getOWLDatatype(XSDVocabulary.DECIMAL.getIRI()))));

        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                cpuClass,
                dataFactory.getOWLDataSomeValuesFrom(cpuCoreNumber, coresRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuThreadNumber, threadsRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuCache, cacheRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuSpeed, speedRange)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();

        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }

    @Override
    public List<String> FindGpu(GPUSearchDTO dto) {
        List<String> retList = new ArrayList<String>();

        OWLDataProperty gpuManufacturer = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuManufacturer"));
        OWLDataProperty gpuMemory = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuMemory"));
        OWLDataProperty gpuClockSpeed = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuClockSpeed"));
        OWLDataProperty gpuType = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuType"));

            OWLClass gpuClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#GraphicsProcessingUnit");

        OWLDataRange speedRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getOWLDatatype(XSDVocabulary.DECIMAL.getIRI()),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(String.valueOf(dto.getFromSpeed()), dataFactory.getOWLDatatype(XSDVocabulary.DECIMAL.getIRI()))),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(String.valueOf(dto.getToSpeed()), dataFactory.getOWLDatatype(XSDVocabulary.DECIMAL.getIRI()))));
        OWLDataRange memoryRange = dataFactory.getOWLDatatypeRestriction(
                dataFactory.getOWLDatatype(XSDVocabulary.INTEGER.getIRI()),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(dto.getGpuMemory())),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(dto.getGpuMemory()))
        );

        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                gpuClass,
                dataFactory.getOWLDataSomeValuesFrom(gpuClockSpeed, speedRange),
                dataFactory.getOWLDataSomeValuesFrom(gpuMemory, memoryRange)
        );
        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();

        for(OWLNamedIndividual individual : individuals) {
            Optional<OWLLiteral> manufacturerValue = reasoner.getDataPropertyValues(individual, gpuManufacturer).stream().findFirst();
            Optional<OWLLiteral> typeValue = reasoner.getDataPropertyValues(individual, gpuType).stream().findFirst();
            if(manufacturerValue.get().getLiteral().equals(dto.getManufacturer()) && typeValue.get().getLiteral().equals(dto.getIntegrated())) {
                retList.add(individual.getIRI().getFragment());
            }
        }

        return retList;
    }

    @Override
    public List<String> FindRam(RAMSearchDTO dto) {
        List<String> retList = new ArrayList<String>();

        OWLDataProperty ramMemory = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#ramMemory"));
        OWLDataProperty ramDDR = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#ramDDR"));
        OWLDataProperty ramMemoryTechnology = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#ramMemoryTechnology"));
        OWLDataProperty ramManufacturer = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#ramManufacturer"));

        OWLClass ramClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#RandomAccessMemory");

        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(ramClass);
        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();

        for(OWLNamedIndividual individual : individuals) {
            Optional<OWLLiteral> memoryValue = reasoner.getDataPropertyValues(individual, ramMemory).stream().findFirst();
            Optional<OWLLiteral> ddrValue = reasoner.getDataPropertyValues(individual, ramDDR).stream().findFirst();
            Optional<OWLLiteral> technologyValue = reasoner.getDataPropertyValues(individual, ramMemoryTechnology).stream().findFirst();
            Optional<OWLLiteral> manufacturerValue = reasoner.getDataPropertyValues(individual, ramManufacturer).stream().findFirst();
            if(manufacturerValue.get().getLiteral().equals(dto.getManufacturer())
                    && memoryValue.get().getLiteral().equals(dto.getMemory().toString())
                    && ddrValue.get().getLiteral().equals(dto.getDdr())
                    && technologyValue.get().getLiteral().equals(dto.getSize())
                ) {
                retList.add(individual.getIRI().getFragment());
            }
        }

        return retList;
    }

    @Override
    public List<String> FindPsu(PSUSearchDTO dto) {
        List<String> retList = new ArrayList<String>();

        OWLDataProperty wattage = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#wattage"));

        OWLClass psuClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#PowerSupplyUnit");

        OWLDataRange wattageRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(dto.getFromWattage())),
                dataFactory.getOWLFacetRestriction(OWLFacet.MAX_INCLUSIVE, dataFactory.getOWLLiteral(dto.getToWattage())));

        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                psuClass,
                dataFactory.getOWLDataSomeValuesFrom(wattage, wattageRange)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();

        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }

    public List<String> UpgradeCPU(String motherboard, String cpu) {
        List<String> retList = new ArrayList<String>();

        System.out.println("------------------------------------");
        // Get motherboard
        OWLNamedIndividual motherboardIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + motherboard));
        System.out.println(motherboardIndividual.getIRI().getFragment());
        // Get cpu
        OWLNamedIndividual cpuIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + cpu));
        System.out.println(cpuIndividual.getIRI().getFragment());
        System.out.println("------------------------------------");

        // Get the data properties to query
        OWLDataProperty cpuCoreNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCoreNumber"));
        OWLDataProperty cpuThreadNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuThreadNumber"));
        OWLDataProperty cpuCache = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuCache"));
        OWLObjectProperty cpuSocket = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasCPUSocket"));
        OWLObjectProperty cpuSocketCPU = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasCPUSocketCPU"));

        OWLClass cpuClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#CentralProcessingUnit");

        OWLLiteral cpuCoreNumberValue = reasoner.getDataPropertyValues(cpuIndividual, cpuCoreNumber).stream().findFirst().orElse(null);
        OWLLiteral cpuThreadNumberValue = reasoner.getDataPropertyValues(cpuIndividual, cpuThreadNumber).stream().findFirst().orElse(null);
        OWLLiteral cpuCacheValue = reasoner.getDataPropertyValues(cpuIndividual, cpuCache).stream().findFirst().orElse(null);

        // Get the object property values for the individual
        Set<OWLNamedIndividual> propertyValues = reasoner.getObjectPropertyValues(motherboardIndividual, cpuSocket).getFlattened();
        OWLNamedIndividual firstValue = null;
        if (!propertyValues.isEmpty()) {
            OWLNamedIndividual[] array = propertyValues.toArray(new OWLNamedIndividual[0]);
            if (array.length > 0) {
                firstValue = array[0];
                System.out.println("First value: ");
            } else {
                System.out.println("Set is empty.");
            }
        }

        OWLDataRange coresRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral((Integer.parseInt(cpuCoreNumberValue.getLiteral())))));
        OWLDataRange threadsRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(Integer.parseInt(cpuThreadNumberValue.getLiteral()))));
        OWLDataRange cpuCacheRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(Integer.parseInt(cpuCacheValue.getLiteral()))));

        //TODO: match manufacturer string and return speed
        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                cpuClass,
                dataFactory.getOWLDataSomeValuesFrom(cpuCoreNumber, coresRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuThreadNumber, threadsRange),
                dataFactory.getOWLDataSomeValuesFrom(cpuCache, cpuCacheRange),
                dataFactory.getOWLObjectHasValue(cpuSocketCPU, firstValue)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();
        System.out.println("Individuals: " + individuals.size());
        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }

    public List<String> UpgradeGPU(String motherboard, String gpu) {
        List<String> retList = new ArrayList<String>();

        System.out.println("------------------------------------");
        // Get motherboard
        OWLNamedIndividual motherboardIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + motherboard));
        System.out.println(motherboardIndividual.getIRI().getFragment());
        // Get cpu
        OWLNamedIndividual gpuIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + gpu));
        System.out.println(gpuIndividual.getIRI().getFragment());
        System.out.println("------------------------------------");

        // Get the data properties to query
        OWLDataProperty gpuMemoryNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuMemory"));
        OWLDataProperty gpuClockSpeedNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuClockSpeed"));
        OWLObjectProperty PCIeConnector = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasPCIeConnector"));
        OWLObjectProperty PCIeConnectorGPU = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasPCIeConnectorGPU"));

        OWLClass gpuClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#GraphicsProcessingUnit");

        OWLLiteral gpuMemoryNumberValue = reasoner.getDataPropertyValues(gpuIndividual, gpuMemoryNumber).stream().findFirst().orElse(null);
        OWLLiteral gpuClockSpeedNumberValue = reasoner.getDataPropertyValues(gpuIndividual, gpuClockSpeedNumber).stream().findFirst().orElse(null);

        // Get the object property values for the individual
        Set<OWLNamedIndividual> propertyValues = reasoner.getObjectPropertyValues(motherboardIndividual, PCIeConnector).getFlattened();
        OWLNamedIndividual firstValue = null;
        if (!propertyValues.isEmpty()) {
            OWLNamedIndividual[] array = propertyValues.toArray(new OWLNamedIndividual[0]);
            if (array.length > 0) {
                firstValue = array[0];
                System.out.println("First value: ");
            } else {
                System.out.println("Set is empty.");
            }
        }

        OWLDataRange gpuMemoryRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral((Integer.parseInt(gpuMemoryNumberValue.getLiteral())))));
        OWLDataRange gpuClockSpeedRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL) ,
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral(Double.toString(Double.parseDouble(gpuClockSpeedNumberValue.getLiteral())), OWL2Datatype.XSD_DECIMAL)));


        //TODO: match manufacturer string and return speed
        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                gpuClass,
                dataFactory.getOWLDataSomeValuesFrom(gpuMemoryNumber, gpuMemoryRange),
                dataFactory.getOWLDataSomeValuesFrom(gpuClockSpeedNumber, gpuClockSpeedRange),
                dataFactory.getOWLObjectHasValue(PCIeConnectorGPU, firstValue)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();
        System.out.println("Individuals: " + individuals.size());
        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }

    public List<String> UpgradeRAM(String motherboard, String ram) {
        List<String> retList = new ArrayList<String>();

        System.out.println("------------------------------------");
        // Get motherboard
        OWLNamedIndividual motherboardIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + motherboard));
        System.out.println(motherboardIndividual.getIRI().getFragment());
        // Get cpu
        OWLNamedIndividual ramIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + ram));
        System.out.println(ramIndividual.getIRI().getFragment());
        System.out.println("------------------------------------");

        // Get the data properties to query
        OWLDataProperty ramMemoryNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#ramMemory"));
        OWLObjectProperty ramSlot = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasRamSlot"));
        OWLObjectProperty ramSlotRAM = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasRamSlotRAM"));

        OWLClass ramClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#RandomAccessMemory");

        OWLLiteral ramMemoryNumberValue = reasoner.getDataPropertyValues(ramIndividual, ramMemoryNumber).stream().findFirst().orElse(null);

        // Get the object property values for the individual
        Set<OWLNamedIndividual> propertyValues = reasoner.getObjectPropertyValues(motherboardIndividual, ramSlot).getFlattened();
        OWLNamedIndividual firstValue = null;
        if (!propertyValues.isEmpty()) {
            OWLNamedIndividual[] array = propertyValues.toArray(new OWLNamedIndividual[0]);
            if (array.length > 0) {
                firstValue = array[0];
                System.out.println("First value: ");
            } else {
                System.out.println("Set is empty.");
            }
        }

        OWLDataRange ramMemoryRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral((Integer.parseInt(ramMemoryNumberValue.getLiteral())))));

        //TODO: match manufacturer string and return speed
        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                ramClass,
                dataFactory.getOWLDataSomeValuesFrom(ramMemoryNumber, ramMemoryRange),
                dataFactory.getOWLObjectHasValue(ramSlotRAM, firstValue)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();
        System.out.println("Individuals: " + individuals.size());
        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }

    public List<String> UpgradePSU(String motherboard, String psu) {
        List<String> retList = new ArrayList<String>();

        System.out.println("------------------------------------");
        // Get motherboard
        OWLNamedIndividual motherboardIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + motherboard));
        System.out.println(motherboardIndividual.getIRI().getFragment());
        // Get cpu
        OWLNamedIndividual psuIndividual = dataFactory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#" + psu));
        System.out.println(psuIndividual.getIRI().getFragment());
        System.out.println("------------------------------------");

        // Get the data properties to query
        OWLDataProperty wattageNumber = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#wattage"));
        OWLObjectProperty has24PinConnector = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#has24PinConnector"));
        OWLObjectProperty has24PinConnectorPSU = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#has24PinConnectorPSU"));

        OWLClass psuClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#PowerSupplyUnit");

        OWLLiteral wattageNumberValue = reasoner.getDataPropertyValues(psuIndividual, wattageNumber).stream().findFirst().orElse(null);

        // Get the object property values for the individual
        Set<OWLNamedIndividual> propertyValues = reasoner.getObjectPropertyValues(motherboardIndividual, has24PinConnector).getFlattened();
        OWLNamedIndividual firstValue = null;
        if (!propertyValues.isEmpty()) {
            OWLNamedIndividual[] array = propertyValues.toArray(new OWLNamedIndividual[0]);
            if (array.length > 0) {
                firstValue = array[0];
                System.out.println("First value: ");
            } else {
                System.out.println("Set is empty.");
            }
        }

        OWLDataRange wattageRange = dataFactory.getOWLDatatypeRestriction(dataFactory.getIntegerOWLDatatype(),
                dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, dataFactory.getOWLLiteral((Integer.parseInt(wattageNumberValue.getLiteral())))));

        //TODO: match manufacturer string and return speed
        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                psuClass,
                dataFactory.getOWLDataSomeValuesFrom(wattageNumber, wattageRange),
                dataFactory.getOWLObjectHasValue(has24PinConnectorPSU, firstValue)
        );

        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();
        System.out.println("Individuals: " + individuals.size());
        // Print the individuals
        for (OWLNamedIndividual individual : individuals) {
            System.out.println(individual.getIRI().getFragment());
            retList.add(individual.getIRI().getFragment());
        }

        return retList;
    }
    
    @Override
    public List<String> FindSimilarPCs(SimilarPCDTO dto) {
    	PC wanted = new PC("", dto.getCpuSpeed(), "", dto.getGpuSpeed(), "", dto.getRamSize());//hardcoded for test
    	
        List<String> retList = new ArrayList<String>();

        OWLObjectProperty cpu = dataFactory.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasCPU"));
        OWLDataProperty cpuClockSpeed = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#cpuClockSpeed"));
        
        OWLObjectProperty gpu = dataFactory.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasGPU"));
        OWLDataProperty gpuClockSpeed = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#gpuClockSpeed"));
        
        OWLObjectProperty ram = dataFactory.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#hasRAM"));
        OWLDataProperty ramMemory = manager.getOWLDataFactory().getOWLDataProperty(IRI.create("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#ramMemory"));

        OWLClass pcClass = manager.getOWLDataFactory().getOWLClass("http://www.semanticweb.org/grbson/ontologies/2023/4/untitled-ontology-5#PC");

        OWLClassExpression queryExpression = dataFactory.getOWLObjectIntersectionOf(
                pcClass
        );
        Set<OWLNamedIndividual> individuals = reasoner.getInstances(queryExpression, false).getFlattened();

    	Double cpuSpeedMax = 0.0, gpuSpeedMax = 0.0, ramMemoryMax = 0.0;
        List<PC> pcs = new ArrayList<PC>();

        for(OWLNamedIndividual individual : individuals) {
        	
        	Set<OWLNamedIndividual> cpuProp = reasoner.getObjectPropertyValues(individual, cpu).getFlattened();
        	OWLNamedIndividual[] array = cpuProp.toArray(new OWLNamedIndividual[0]);
        	OWLNamedIndividual cpuTemp = array[0];
        	
        	Set<OWLNamedIndividual> gpuProp = reasoner.getObjectPropertyValues(individual, gpu).getFlattened();
        	array = gpuProp.toArray(new OWLNamedIndividual[0]);
        	OWLNamedIndividual gpuTemp = array[0];
        	
        	Set<OWLNamedIndividual> ramProp = reasoner.getObjectPropertyValues(individual, ram).getFlattened();
        	array = ramProp.toArray(new OWLNamedIndividual[0]);
        	OWLNamedIndividual ramTemp = array[0];
        	
        	OWLLiteral cpuSpeed = reasoner.getDataPropertyValues(cpuTemp, cpuClockSpeed).stream().findFirst().orElse(null);
        	OWLLiteral gpuSpeed = reasoner.getDataPropertyValues(gpuTemp, gpuClockSpeed).stream().findFirst().orElse(null);
        	OWLLiteral ramMemoryy = reasoner.getDataPropertyValues(ramTemp, ramMemory).stream().findFirst().orElse(null);
        	
            if(Double.parseDouble(cpuSpeed.getLiteral()) > cpuSpeedMax) { cpuSpeedMax = Double.parseDouble(cpuSpeed.getLiteral()); }
            if(Double.parseDouble(gpuSpeed.getLiteral()) > gpuSpeedMax) { gpuSpeedMax = Double.parseDouble(gpuSpeed.getLiteral()); }
            if(Double.parseDouble(ramMemoryy.getLiteral()) > ramMemoryMax) { ramMemoryMax = Double.parseDouble(ramMemoryy.getLiteral()); }
        	
        	pcs.add(new PC(cpuTemp.getIRI().getFragment(), cpuSpeed.parseDouble(), gpuTemp.getIRI().getFragment(), gpuSpeed.parseDouble(), ramTemp.getIRI().getFragment(), ramMemoryy.parseInteger()));
            //retList.add(individual.getIRI().getFragment() + ": " + cpuTemp.getIRI().getFragment() + ", " + gpuTemp.getIRI().getFragment() + ", " + ramTemp.getIRI().getFragment());
            
        }
        
        System.out.println("cpuSpeedMax: " + cpuSpeedMax + ", gpuSpeedMax: " + gpuSpeedMax + ", ramMemoryMax: " + ramMemoryMax);
        
        for (PC p : pcs) {
        	p.calculateCoefficient(wanted, new PC("", cpuSpeedMax, "", gpuSpeedMax, "", ramMemoryMax.intValue()));
        }
        
        Collections.sort(pcs, Comparator.comparing(p -> p.getMatchingCoefficient()));
        
        for (int i = 0; i < 5; i++) {
        	retList.add(pcs.get(i).toString());
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