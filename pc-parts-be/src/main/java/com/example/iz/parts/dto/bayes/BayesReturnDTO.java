package com.example.iz.parts.dto.bayes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BayesReturnDTO {
    private String name;
    private Float percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
