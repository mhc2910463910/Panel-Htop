package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComputerSystemDto {
    private String manufacturer;
    /**
     * BIOS制造商
     */
    private String model;
    /**
     * 产品名称
     */
    private String version;
    /**
     * 版本
     */
    private String release_date;
    /**
     * 发布日期
     */
}
