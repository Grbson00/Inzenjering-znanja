package com.example.iz.parts.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PC {
	private String cpuName;
	private Double cpuSpeed;
	private String gpuName;
	private Double gpuSpeed;
	private String ramName;
	private Integer ramMemory;
	
	private Double matchingCoefficient;
	
	public PC(String cpuName, Double cpuSpeed, String gpuName, Double gpuSpeed, String ramName, Integer ramMemory) {
		super();
		this.cpuName = cpuName;
		this.cpuSpeed = cpuSpeed;
		this.gpuName = gpuName;
		this.gpuSpeed = gpuSpeed;
		this.ramName = ramName;
		this.ramMemory = ramMemory;
		this.matchingCoefficient = 999.00;
	}
	
	public Double getMatchingCoefficient() {
		return matchingCoefficient;
	}

	public void setMatchingCoefficient(Double matchingCoefficient) {
		this.matchingCoefficient = matchingCoefficient;
	}

	public void calculateCoefficient(PC wanted, PC max) {
		this.matchingCoefficient = Math.abs(wanted.cpuSpeed/max.cpuSpeed - this.cpuSpeed/max.cpuSpeed) +
									Math.abs(wanted.gpuSpeed/max.gpuSpeed - this.gpuSpeed/max.gpuSpeed) +
									Math.abs(wanted.ramMemory/max.ramMemory - this.ramMemory/max.ramMemory);
	}

	@Override
	public String toString() {
		return "PC [cpuName=" + cpuName + ", gpuName=" + gpuName + ", ramName=" + ramName + "]";
	}

	
}
