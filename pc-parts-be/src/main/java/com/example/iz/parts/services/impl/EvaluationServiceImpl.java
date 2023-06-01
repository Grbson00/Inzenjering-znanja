package com.example.iz.parts.services.impl;

import com.example.iz.parts.dto.evaluate.EvaluateDTO;
import com.example.iz.parts.services.EvaluationService;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    private String fileName = "data/rules.fcl";

    public ArrayList<Double> Evaluate(EvaluateDTO dto) {
        FIS fis = FIS.load(fileName);
        if( fis == null ) {
            System.err.println("Can't load file: '" + fileName + "'");
            return null;
        }
        fis.setVariable("cpuSpeed", dto.getCpuSpeed());
        fis.setVariable("coreNum", dto.getCoreNum());
        fis.setVariable("threadNum", dto.getThreadNum());
        fis.setVariable("ramSize", dto.getRamSize());
        fis.setVariable("storageSize", dto.getStorageSize());
        fis.setVariable("gpuSize", dto.getGpuSize());

        fis.evaluate();

//        System.out.println("***********************");
//        System.out.println("Home PC: " + fis.getVariable("home").getValue());
//        System.out.println("Work PC: " + fis.getVariable("work").getValue());
//        System.out.println("Gaming PC: " + fis.getVariable("gaming").getValue());
//        System.out.println("Crypto PC: " + fis.getVariable("crypto").getValue());
//        System.out.println("Hosting PC: " + fis.getVariable("hosting").getValue());
//        System.out.println("Development PC: " + fis.getVariable("development").getValue());

        ArrayList<Double> retList = new ArrayList<>();
        retList.add(fis.getVariable("development").getValue());
        retList.add(fis.getVariable("gaming").getValue());
        retList.add(fis.getVariable("crypto").getValue());
        retList.add(fis.getVariable("home").getValue());
        retList.add(fis.getVariable("work").getValue());
        retList.add(fis.getVariable("hosting").getValue());

        return retList;
    }

}
