package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PSUSearchDTO {
    private String manufacturer;
    private Integer wattage;

    @Override
    public String toString() {
        return "PSUSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", wattage=" + wattage +
                '}';
    }
}
