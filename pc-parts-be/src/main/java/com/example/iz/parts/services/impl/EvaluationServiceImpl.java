package com.example.iz.parts.services.impl;

import com.example.iz.parts.services.EvaluationService;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import org.springframework.stereotype.Service;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    private String fileName = "data/rules.fcl";

    public void Evaluate() {
        FIS fis = FIS.load(fileName);
        if( fis == null ) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        JFuzzyChart.get().chart(fis);
        fis.setVariable("cpuSpeed", 3.1);
        fis.setVariable("coreNum", 8);
        fis.setVariable("threadNum", 8);
        fis.setVariable("ramSize", 16);
        fis.setVariable("storageSize", 512);
        fis.setVariable("gpuSize", 8);

        fis.evaluate();
    }

}
