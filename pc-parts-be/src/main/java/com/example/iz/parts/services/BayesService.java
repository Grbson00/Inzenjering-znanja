package com.example.iz.parts.services;

import com.example.iz.parts.dto.bayes.BayesDTO;
import com.example.iz.parts.dto.bayes.BayesReturnDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface BayesService {
    ArrayList<BayesReturnDTO> GetErrorCause(BayesDTO dto) throws IOException;
    ArrayList<String> GetProblemCause() throws IOException;
}
