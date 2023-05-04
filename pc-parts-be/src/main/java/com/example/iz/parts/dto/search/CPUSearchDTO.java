package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CPUSearchDTO {
    private String manufacturer;
    private Integer fromSpeed;
    private Integer toSpeed;
    private Integer cacheMemory;
    private Integer coreNumber;
    private Integer threadNumber;

    @Override
    public String toString() {
        return "CPUSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", fromSpeed=" + fromSpeed +
                ", toSpeed=" + toSpeed +
                ", cacheMemory=" + cacheMemory +
                ", coreNumber=" + coreNumber +
                ", threadNumber=" + threadNumber +
                '}';
    }
}
