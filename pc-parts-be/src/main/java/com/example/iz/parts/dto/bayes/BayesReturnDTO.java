package com.example.iz.parts.dto.bayes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BayesReturnDTO {
    private String name;
    private Float percentage;

    public BayesReturnDTO(String replace, float f) {
		name = replace;
		percentage = f;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float name) {
        this.percentage = percentage;
    }

}
