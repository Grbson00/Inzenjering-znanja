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

        fis.evaluate();

        System.out.println("CPU speed vl: " + fis.getVariable("cpuSpeed").getMembership("verySlow"));
        System.out.println("CPU speed l: " + fis.getVariable("cpuSpeed").getMembership("slow"));
        System.out.println("CPU speed m: " + fis.getVariable("cpuSpeed").getMembership("medium"));
        System.out.println("CPU speed h: " + fis.getVariable("cpuSpeed").getMembership("fast"));
        System.out.println("CPU speed vh: " + fis.getVariable("cpuSpeed").getMembership("veryFast"));

        System.out.println("***********************");

        System.out.println("Core num: " + fis.getVariable("coreNum").getMembership("veryLow"));
        System.out.println("Core num: " + fis.getVariable("coreNum").getMembership("low"));
        System.out.println("Core num: " + fis.getVariable("coreNum").getMembership("medium"));
        System.out.println("Core num: " + fis.getVariable("coreNum").getMembership("high"));
        System.out.println("Core num: " + fis.getVariable("coreNum").getMembership("veryHigh"));

        System.out.println("***********************");

        System.out.println("Thread num: " + fis.getVariable("threadNum").getMembership("veryLow"));
        System.out.println("Thread num: " + fis.getVariable("threadNum").getMembership("low"));
        System.out.println("Thread num: " + fis.getVariable("threadNum").getMembership("medium"));
        System.out.println("Thread num: " + fis.getVariable("threadNum").getMembership("high"));
        System.out.println("Thread num: " + fis.getVariable("threadNum").getMembership("veryHigh"));

        System.out.println("***********************");

        System.out.println("Storage size: " + fis.getVariable("storageSize").getMembership("verySmall"));
        System.out.println("Storage size: " + fis.getVariable("storageSize").getMembership("small"));
        System.out.println("Storage size: " + fis.getVariable("storageSize").getMembership("medium"));
        System.out.println("Storage size: " + fis.getVariable("storageSize").getMembership("large"));
        System.out.println("Storage size: " + fis.getVariable("storageSize").getMembership("veryLarge"));

        System.out.println("***********************");

        System.out.println("RAM size: " + fis.getVariable("ramSize").getMembership("verySmall"));
        System.out.println("RAM size: " + fis.getVariable("ramSize").getMembership("small"));
        System.out.println("RAM size: " + fis.getVariable("ramSize").getMembership("medium"));
        System.out.println("RAM size: " + fis.getVariable("ramSize").getMembership("large"));
        System.out.println("RAM size: " + fis.getVariable("ramSize").getMembership("veryLarge"));

        System.out.println("***********************");

        System.out.println("GPU size: " + fis.getVariable("gpuSize").getMembership("verySmall"));
        System.out.println("GPU size: " + fis.getVariable("gpuSize").getMembership("small"));
        System.out.println("GPU size: " + fis.getVariable("gpuSize").getMembership("medium"));
        System.out.println("GPU size: " + fis.getVariable("gpuSize").getMembership("large"));
        System.out.println("GPU size: " + fis.getVariable("gpuSize").getMembership("veryLarge"));

    }

}
