package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RAMSearchDTO {
    private String manufacturer;
    private String mode;
    private String size;
    private Integer ddr;
    private Integer memory;

    @Override
    public String toString() {
        return "RAMSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", mode='" + mode + '\'' +
                ", size='" + size + '\'' +
                ", ddr=" + ddr +
                ", memory=" + memory +
                '}';
    }
}
