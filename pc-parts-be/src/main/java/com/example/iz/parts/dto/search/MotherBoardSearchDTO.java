package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MotherBoardSearchDTO {
    private String manufacturer;
    private String chipset;
    private String cpuSocket;
    private String formFactor;
    private Integer ramSlots;
    private Integer sataSlots;
    private Integer ddr;

    @Override
    public String toString() {
        return "MotherBoardSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", chipset='" + chipset + '\'' +
                ", cpuSocket='" + cpuSocket + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", ramSlots=" + ramSlots +
                ", sataSlots=" + sataSlots +
                ", ddr=" + ddr +
                '}';
    }
}
