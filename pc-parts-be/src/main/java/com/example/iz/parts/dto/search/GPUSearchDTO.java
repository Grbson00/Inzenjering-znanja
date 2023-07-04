package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GPUSearchDTO {
    private String manufacturer;
    private Double fromSpeed;
    private Double toSpeed;
    private  Integer gpuMemory;
    private String integrated;

    public String getManufacturer() {
        return manufacturer;
    }

    public Double getFromSpeed() {
        return fromSpeed;
    }

    public Double getToSpeed() {
        return toSpeed;
    }

    public Integer getGpuMemory() {
        return gpuMemory;
    }

    public String getIntegrated() {
        return integrated;
    }

    @Override
    public String toString() {
        return "GPUSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", fromSpeed=" + fromSpeed +
                ", toSpeed=" + toSpeed +
                ", gpuMemory=" + gpuMemory +
                ", integrated='" + integrated + '\'' +
                '}';
    }
}
