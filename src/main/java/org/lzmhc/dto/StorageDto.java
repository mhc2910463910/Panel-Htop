package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;

@Getter
@Setter
public class StorageDto implements InfoItem {
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
     * 磁盘容量占比
     */
    private long readCount;
    /**
     * 读取次数
     */
    private long readbytes;
    /**
     * 读取字节数
     */
    private long writeCount;
    /**
     * 写入次数
     */
    private long writebytes;
    /**
     * 写入字节数
     */
}
