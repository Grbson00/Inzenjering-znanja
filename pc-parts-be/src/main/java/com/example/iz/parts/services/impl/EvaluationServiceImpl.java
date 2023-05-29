package com.example.iz.parts.services.impl;

import com.example.iz.parts.dto.evaluate.EvaluateDTO;
import com.example.iz.parts.services.EvaluationService;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import org.springframework.stereotype.Service;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    private String fileName = "data/rules.fcl";

    public void Evaluate(EvaluateDTO dto) {
        FIS fis = FIS.load(fileName);
        if( fis == null ) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        JFuzzyChart.get().chart(fis);
        fis.setVariable("cpuSpeed", dto.getCpuSpeed());
        fis.setVariable("coreNum", dto.getCoreNum());
        fis.setVariable("threadNum", dto.getThreadNum());
        fis.setVariable("ramSize", dto.getRamSize());
        fis.setVariable("storageSize", dto.getStorageSize());
        fis.setVariable("gpuSize", dto.getGpuSize());

        System.out.println(dto.isIntegrated());
        System.out.println(dto.isHdd());


        fis.evaluate();
    }

}
