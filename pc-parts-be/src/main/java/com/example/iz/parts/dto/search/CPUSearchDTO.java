package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CPUSearchDTO {
    private String manufacturer;
    private Double fromSpeed;
    private Double toSpeed;
    private Integer cacheMemoryFrom;
    private Integer cacheMemoryTo;
    private Integer coreNumberFrom;
    private Integer coreNumberTo;
    private Integer threadNumberFrom;
    private Integer threadNumberTo;

    public String getManufacturer() {
        return manufacturer;
    }

    public Double getFromSpeed() {
        return fromSpeed;
    }

    public Double getToSpeed() {
        return toSpeed;
    }

    public Integer getCacheMemoryFrom() {
        return cacheMemoryFrom;
    }

    public Integer getCacheMemoryTo() {
        return cacheMemoryTo;
    }

    public Integer getCoreNumberFrom() {
        return coreNumberFrom;
    }

    public Integer getCoreNumberTo() {
        return coreNumberTo;
    }

    public Integer getThreadNumberFrom() {
        return threadNumberFrom;
    }

    public Integer getThreadNumberTo() {
        return threadNumberTo;
    }

    @Override
    public String toString() {
        return "CPUSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", fromSpeed=" + fromSpeed +
                ", toSpeed=" + toSpeed +
                ", cacheMemoryFrom=" + cacheMemoryFrom +
                ", cacheMemoryTo=" + cacheMemoryTo +
                ", coreNumberFrom=" + coreNumberFrom +
                ", coreNumberTo=" + coreNumberTo +
                ", threadNumberFrom=" + threadNumberFrom +
                ", threadNumberTo=" + threadNumberTo +
                '}';
    }
}
