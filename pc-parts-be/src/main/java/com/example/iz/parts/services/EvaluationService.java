package com.example.iz.parts.services;

import com.example.iz.parts.dto.evaluate.EvaluateDTO;

import java.util.ArrayList;

public interface EvaluationService {
    ArrayList<Double> Evaluate(EvaluateDTO dto);
}
