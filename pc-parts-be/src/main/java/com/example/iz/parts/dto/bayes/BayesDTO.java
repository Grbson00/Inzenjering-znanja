package com.example.iz.parts.dto.bayes;

import java.util.ArrayList;

public class BayesDTO {
    private ArrayList<String> symptomList;

    public ArrayList<String> getSymptomList() {
        return symptomList;
    }

    @Override
    public String toString() {
        return "BayesDTO{" +
                "symptomList=" + symptomList +
                '}';
    }
}
