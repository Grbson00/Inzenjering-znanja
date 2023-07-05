package com.example.iz.parts.dto;

public class SimilarPCDTO {
    private Double cpuSpeed;
    private Double gpuSpeed;
    private Integer ramSize;

    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public Double getGpuSpeed() {
        return gpuSpeed;
    }

    public Integer getRamSize() {
        return ramSize;
    }

    @Override
    public String toString() {
        return "SimilarPCDTO{" +
                "cpuSpeed=" + cpuSpeed +
                ", gpuSpeed=" + gpuSpeed +
                ", ramSize=" + ramSize +
                '}';
    }
}
