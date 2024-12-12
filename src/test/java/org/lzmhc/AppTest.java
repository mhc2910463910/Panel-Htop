package org.lzmhc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.lzmhc.dto.InfoDto;
import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private SystemInfo systemInfo = new SystemInfo();
    /**
     * Rigorous Test :-)
     */
    @Test
    public void getOperatingSystemInfo() {
        System.out.println("操作系统: "+systemInfo.getOperatingSystem().getFamily());
        System.out.println("位数: "+systemInfo.getOperatingSystem().getBitness());
        System.out.println("版本: "+systemInfo.getOperatingSystem().getVersionInfo().getVersion());
        System.out.println("操作系统版本号: "+systemInfo.getOperatingSystem().getVersionInfo().getBuildNumber());
        System.out.println("开机时间: "+systemInfo.getOperatingSystem().getSystemBootTime());
    }
    @Test
    public void getMemoryInfo(){
        System.out.println("物理内存: "+systemInfo.getHardware().getMemory().getTotal());
        System.out.println("可用物理内存: "+systemInfo.getHardware().getMemory().getAvailable());
        System.out.println("已用物理内存: "+(systemInfo.getHardware().getMemory().getTotal()-systemInfo.getHardware().getMemory().getAvailable()));
//        System.out.println("物理内存利用率: "+systemInfo.getGlobalMemory().getPercentage()+" %");
//        System.out.println("虚拟内存: "+systemInfo.getGlobalMemory().getVirtuallMemory()+" GB");
//        System.out.println("已用虚拟内存: "+systemInfo.getGlobalMemoryDto().getVirtualUsedMemory()+" GB");
//        System.out.println("内存类型/位: "+systemInfo.getGlobalMemoryDto().getRamTypeOrOsBitDepth());
//        System.out.println("进程数: "+systemInfo.getGlobalMemoryDto().getProcCount());
    }

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
//    @Test
//    public void getStorageInfo(){
//        InfoDto systemInfo = (InfoDto) infoController.getInfo().getsystemInfo();
//        List<StorageDto> storageDtoList = systemInfo.getStorageDtoList();
//        for(StorageDto storageDto:storageDtoList) {
//            System.out.println("磁盘模型: " + storageDto.getMainStorage());
//            System.out.println("磁盘数量: " + storageDto.getDiskCount());
//            System.out.println("磁盘空间: " + storageDto.getTotal());
//            System.out.println("磁盘I/O率:" + storageDto.getUsedRate());
//        }
//    }
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
