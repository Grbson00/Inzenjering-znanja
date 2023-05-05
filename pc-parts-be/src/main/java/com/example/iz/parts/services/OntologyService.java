package com.example.iz.parts.services;

import com.example.iz.parts.dto.search.CPUSearchDTO;

import java.io.File;
import java.util.List;

public interface OntologyService {
    void LoadOntology(File file);
    List<String> FindCpu(CPUSearchDTO dto);
}
