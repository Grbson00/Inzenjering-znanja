package com.example.iz.parts.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CPU {
    private String manufacturer;
    private Double clockSpeed;
    private Integer coreNumber;
    private Integer threadNumber;
    private Double cacheMemory;
}
