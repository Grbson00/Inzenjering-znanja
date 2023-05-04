package com.example.iz.parts.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RAM {
    private String manufacturer;
    private String dataRate;
    private Integer memory;
    private String size; //probably enum SO-DIMM / DIMM
    private String operatingMode; //enum Single Dual Quad
}
