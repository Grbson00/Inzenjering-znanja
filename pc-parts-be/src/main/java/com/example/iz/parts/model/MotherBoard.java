package com.example.iz.parts.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MotherBoard {
    private String manufacturer;
    private String cpuSocketType;
    private String dataRate;
    private Integer ramSlotNumber;
    private String formFactor;
    private String chipset;
    private Integer sataSlots;
}
