package com.example.iz.parts.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PC {
	private Double cpuSpeed;
	private Double gpuSpeed;
	private Integer ramMemory;
	
	public PC(Double cpuSpeed, Double gpuSpeed, Integer ramMemory) {
		super();
		this.cpuSpeed = cpuSpeed;
		this.gpuSpeed = gpuSpeed;
		this.ramMemory = ramMemory;
	}
	
	public Double getSum() {
		return cpuSpeed + gpuSpeed + ramMemory;
	}

	@Override
	public String toString() {
		return "PC [cpuSpeed=" + cpuSpeed + ", gpuSpeed=" + gpuSpeed + ", ramMemory=" + ramMemory + "]";
	}
}
