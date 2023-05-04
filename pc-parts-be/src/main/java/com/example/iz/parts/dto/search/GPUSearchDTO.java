package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GPUSearchDTO {
    private String manufacturer;
    private Integer fromSpeed;
    private Integer toSpeed;
    private  Integer gpuMemory;

    @Override
    public String toString() {
        return "GPUSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", fromSpeed=" + fromSpeed +
                ", toSpeed=" + toSpeed +
                ", gpuMemory=" + gpuMemory +
                '}';
    }
}
