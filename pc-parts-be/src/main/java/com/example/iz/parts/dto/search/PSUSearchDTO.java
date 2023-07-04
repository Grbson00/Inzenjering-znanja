package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PSUSearchDTO {
    private Integer fromWattage;
    private Integer toWattage;

    public Integer getFromWattage() {
        return fromWattage;
    }

    public Integer getToWattage() {
        return toWattage;
    }

    @Override
    public String toString() {
        return "PSUSearchDTO{" +
                "fromWattage=" + fromWattage +
                ", toWattage=" + toWattage +
                '}';
    }
}
