package com.example.iz.parts.dto.evaluate;

public class EvaluateDTO {
    private Double cpuSpeed;
    private Integer coreNum;
    private Integer threadNum;
    private Integer storageSize;
    private Integer gpuSize;
    private Integer ramSize;
    private Boolean integrated;
    private Boolean hdd;


    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public Integer getCoreNum() {
        return coreNum;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public Integer getStorageSize() {
        return storageSize;
    }

    public Integer getGpuSize() {
        return gpuSize;
    }

    public Integer getRamSize() {
        return ramSize;
    }

    public Boolean isIntegrated() {
        return integrated;
    }

    public Boolean isHdd() {
        return hdd;
    }

}
