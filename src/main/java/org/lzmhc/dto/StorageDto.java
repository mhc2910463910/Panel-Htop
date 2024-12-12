package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageDto {
    private String mainStorage;
    /**
     * 磁盘模型
     */
    private String total;
    /**
     * 磁盘空间
     */
    private String diskCount;
    /**
     * 磁盘数量
     */
    private String usedRate;
    /**
     * 磁盘使用率
     */
}
