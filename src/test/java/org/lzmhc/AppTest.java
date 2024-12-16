package org.lzmhc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.lzmhc.dto.InfoDto;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OSSession;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private SystemInfo systemInfo = InfoDtoSingleton.getInfoDto();
    @Test
    public void getOperatingSystem(){
        /**
         * 文件系统
         */
//        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
//        List<OSFileStore> fileStores = fileSystem.getFileStores();
//        for(OSFileStore fileStore: fileStores){
//            System.out.println(fileStore);
//        }
//        List<OSSession> sessions = systemInfo.getOperatingSystem().getSessions();
//        for(OSSession osSession:sessions){
//            System.out.println(osSession.getHost());
//        }
    }
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void getOperatingSystemInfo() {
//        /**
//         * 逻辑处理器
//         */
//        List<CentralProcessor.LogicalProcessor> logicalProcessors = systemInfo.getHardware().getProcessor().getLogicalProcessors();
//        for(CentralProcessor.LogicalProcessor logicalProcessor:logicalProcessors){
//            System.out.println("核心ID"+logicalProcessor.getPhysicalProcessorNumber());
//            System.out.println("逻辑处理器编号"+logicalProcessor.getProcessorNumber());
//        }
//        /**
//         * 物理处理器
//         */
//        List<CentralProcessor.PhysicalProcessor> physicalProcessors = systemInfo.getHardware().getProcessor().getPhysicalProcessors();
//        for(CentralProcessor.PhysicalProcessor physicalProcessor: physicalProcessors){
//            System.out.println("核心ID"+physicalProcessor.getPhysicalProcessorNumber());
//            System.out.println("平台特定标识"+physicalProcessor.getIdString().split(","));
//        }
//    }
//    @Test
//    public void getMemoryInfo(){
//        System.out.println(systemInfo.getHardware().getMemory().getPhysicalMemory());
//        System.out.println("物理内存: "+systemInfo.getHardware().getMemory().getTotal());
//        System.out.println("可用物理内存: "+systemInfo.getHardware().getMemory().getAvailable());
//        System.out.println("已用物理内存: "+(systemInfo.getHardware().getMemory().getTotal()-systemInfo.getHardware().getMemory().getAvailable()));
//    }

//    @Test
//    public void getProcessorInfo(){
//        InfoDto systemInfo=(InfoDto) infoController.getInfo().getsystemInfo();
//        System.out.println("位数: "+systemInfo.getProcessorDto().getBitDepth());
//        System.out.println("CPU核心: "+systemInfo.getProcessorDto().getCoreCount());
//        System.out.println("当前频率："+systemInfo.getProcessorDto().getCurrentFreq());
//        System.out.println("最大频率: "+systemInfo.getProcessorDto().getMaxFreq());
//        System.out.println("处理器: "+systemInfo.getProcessorDto().getName());
//        System.out.println("CPU温度: "+systemInfo.getProcessorDto().getSensoresTemperature());
//        System.out.println("CPU电压: "+systemInfo.getProcessorDto().getSensorsVoltage());
//        System.out.println("风扇速度: "+systemInfo.getProcessorDto().getSensoresSpeedList());
//        System.out.println("CPU使用率: "+systemInfo.getProcessorDto().getUsedRate());
//    }
//    @Test
//    public void getComputerSystemInfo(){
//        InfoDto systemInfo = (InfoDto) infoController.getInfo().getsystemInfo();
//        System.out.println("制造商: "+systemInfo.getComputerSystemDto().getManufacturer());
//        System.out.println("产品名称: "+systemInfo.getComputerSystemDto().getModel());
//        System.out.println("BIOS版本: "+systemInfo.getComputerSystemDto().getVersion());
//        System.out.println("发布时间: "+systemInfo.getComputerSystemDto().getRelease_date());
//
//    }
//    @Test
//    public void getPowerSourcesInfo(){
//        InfoDto systemInfo = (InfoDto) infoController.getInfo().getsystemInfo();
//        System.out.println("电源名称: "+systemInfo.getPowerDto().getName());
//        System.out.println("设备名称: "+systemInfo.getPowerDto().getDeviceName());
//        System.out.println("电压: "+systemInfo.getPowerDto().getVoltage());
//        System.out.println("是否接入电源: "+systemInfo.getPowerDto().isPowerOnLine());
//        System.out.println("是否在充电: "+systemInfo.getPowerDto().isCharging());
//        System.out.println("是否放电: "+systemInfo.getPowerDto().isDischarging());
//        System.out.println("当前容量: "+systemInfo.getPowerDto().getCurrentCapacity());
//        System.out.println("设计容量: "+systemInfo.getPowerDto().getDesignCapacity());
//        System.out.println("最大容量: "+systemInfo.getPowerDto().getMaxCapacity());
//        System.out.println("电池性质: "+systemInfo.getPowerDto().getChemistry());
//        System.out.println("电池制造商: "+systemInfo.getPowerDto().getManufacturer());
//    }
    @Test
    public void getStorageInfo(){
        List<HWDiskStore> diskStores = systemInfo.getHardware().getDiskStores();
        for (HWDiskStore diskStore: diskStores){
            System.out.println(diskStore.getModel());
            System.out.println(diskStore.getName());
            System.out.println(diskStore.getSize());
//            System.out.println(diskStore.getPartitions());
            System.out.println(diskStore.getReads());
            System.out.println(diskStore.getReadBytes());
//            System.out.println(diskStore.getCurrentQueueLength());
            System.out.println(diskStore.getWrites());
            System.out.println(diskStore.getWriteBytes());
        }
//        InfoDto systemInfo = (InfoDto) infoController.getInfo().getsystemInfo();
//        List<StorageDto> storageDtoList = systemInfo.getStorageDtoList();
//        for(StorageDto storageDto:storageDtoList) {
//            System.out.println("磁盘模型: " + storageDto.getMainStorage());
//            System.out.println("磁盘数量: " + storageDto.getDiskCount());
//            System.out.println("磁盘空间: " + storageDto.getTotal());
//            System.out.println("磁盘I/O率:" + storageDto.getUsedRate());
//        }
    }
//    @Test
//    public void getGraphicsCardsInfo(){
//        InfoDto systemInfo = (InfoDto) infoController.getInfo().getsystemInfo();
//        List<GraphicsCard> graphicsCardList = systemInfo.getGraphicsCardDto().getGraphicsCardList();
//        for( GraphicsCard graphicsCard: graphicsCardList){
//            System.out.println("显卡名称: "+graphicsCard.getName());
//            System.out.println("供应商: "+graphicsCard.getVendor());
//            System.out.println("显存: "+graphicsCard.getVRam()/1024.0/1024.0 +" MB");
//        }
//
//    }
}
