package com.example.iz.parts.dto.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RAMSearchDTO {
    private String manufacturer;
    private String size;
    private String ddr;
    private Integer memory;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDdr() {
        return ddr;
    }

    public void setDdr(String ddr) {
        this.ddr = ddr;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }
    @Override
    public String toString() {
        return "RAMSearchDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", size='" + size + '\'' +
                ", ddr='" + ddr + '\'' +
                ", memory=" + memory +
                '}';
    }
}
