package com.example.iz.parts.services;

import com.example.iz.parts.dto.search.CPUSearchDTO;
import com.example.iz.parts.dto.search.GPUSearchDTO;
import com.example.iz.parts.dto.search.PSUSearchDTO;
import com.example.iz.parts.dto.search.RAMSearchDTO;
import com.example.iz.parts.model.PC;

import java.io.File;
import java.util.List;

public interface OntologyService {
    void LoadOntology(File file);
    List<String> FindCpu(CPUSearchDTO dto);
    List<String> FindGpu(GPUSearchDTO dto);
    List<String> FindRam(RAMSearchDTO dto);
    List<String> FindPsu(PSUSearchDTO dto);

    List<String> UpgradeCPU(String motherboard, String cpu);
    List<String> UpgradeGPU(String motherboard, String gpu);
    List<String> UpgradeRAM(String motherboard, String ram);
    List<String> UpgradePSU(String motherboard, String psu);
	List<String> FindSimilarPCs();
}
