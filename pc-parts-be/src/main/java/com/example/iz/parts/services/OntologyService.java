package com.example.iz.parts.services;

import com.example.iz.parts.dto.search.CPUSearchDTO;

import java.io.File;
import java.util.List;

public interface OntologyService {
    void LoadOntology(File file);
    List<String> FindCpu(CPUSearchDTO dto);

    List<String> UpgradeCPU(String motherboard, String cpu);
    List<String> UpgradeGPU(String motherboard, String gpu);
    List<String> UpgradeRAM(String motherboard, String ram);
    List<String> UpgradePSU(String motherboard, String psu);
}
