package org.lzmhc.dto;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;

import java.util.List;

@Getter
@Setter
public class InfoDto implements InfoItem {
    private ComputerSystemDto computerSystemDto;
    /**
     * 物理硬件
     */
    private ProcessorDto processorDto;
    /**
     * 处理器
     */
    private OperatingSystemDto operatingSystemDto;
    /**
     * 操作系统
     */
    private GlobalMemoryDto globalMemoryDto;
    /**
     * 内存
     */
    private PowerDto powerDto;
    /**
     * 电池
     */
    private List<StorageDto> storageDtoList;
    /**
     * 磁盘存储
     */
    private GraphicsCardDto graphicsCardDto;
    /**
     * 显卡
     */
}
