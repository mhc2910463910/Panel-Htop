package org.lzmhc.component;

import org.lzmhc.dto.*;
import org.lzmhc.dto.factory.DtoCreator;
import org.lzmhc.dto.factory.InfoDto;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.*;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class Panel1 extends JPanel{
    private SystemInfo systemInfo = InfoDtoSingleton.getInfoDto();
    private DtoCreator dtoCreator = new InfoDto();
    private int numThreads = 1;
    private String color = "#355c7d";
    /**
     * 概览
     * @return
     */
    public Panel1(){
        GridLayout gridLayout = new GridLayout(2,3);
        setLayout(gridLayout);
        this.add(this.processPane1());
        this.add(this.processPane2());
        this.add(this.processPane3());
        this.add(this.processPane4());
        this.add(this.processPane5());
        this.add(this.processPane6());
    }
    /**
     * 处理器
     */
    private JPanel processPane1()  {
        ImageIcon icon = new ImageIcon("img/cpu.png");
        PanelItem item=new PanelItem("处理器",new GridLayout(7,1), icon, color);
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        ProcessorDto processorDto = dtoCreator.createDto(ProcessorDto.class);
        CountDownLatch latch = new CountDownLatch(numThreads);
        Thread thread = new ProcessorHandle(processorDto, processor,latch);
        thread.start();
        try {
            latch.await();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        item.addLabel("利用率", processorDto.getUsedRate() + "%");
        item.addLabel("CPU电压", processorDto.getSensorsVoltage());
        item.addLabel("CPU温度", processorDto.getSensoresTemperature());
        item.addLabel("处理器", processorDto.getName());
        item.addLabel("频率", processorDto.getCurrentFreq() + "/" + processorDto.getMaxFreq());
        item.addLabel("风扇速度", (processorDto.getSensoresSpeedList().size()>0)?processorDto.getSensoresSpeedList().get(0):"0");
        Timer time = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Thread thread = new ProcessorHandle(processorDto, processor,latch);
                    thread.start();
                    try {
                        latch.await();
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                    item.removeAll();
                    item.addLabel("利用率", processorDto.getUsedRate() + "%");
                    item.addLabel("CPU电压", processorDto.getSensorsVoltage());
                    item.addLabel("CPU温度", processorDto.getSensoresTemperature());
                    item.addLabel("处理器", processorDto.getName());
                    item.addLabel("频率", processorDto.getCurrentFreq() + "/" + processorDto.getMaxFreq());
                    item.addLabel("风扇速度", (processorDto.getSensoresSpeedList().size()>0)?processorDto.getSensoresSpeedList().get(0):"0");
                    item.updateUI();
                }
            });
            time.start();
            return item;
    }
    /**
     * 内存
     * @return
     */
    private JPanel processPane2(){
        ImageIcon icon = new ImageIcon("img/java.png");
        PanelItem item = new PanelItem("内存",new GridLayout(6,1), icon,color);
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        GlobalMemoryDto globalMemoryDto = dtoCreator.createDto(GlobalMemoryDto.class);
        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread = new MemoryHandle(globalMemoryDto, globalMemory, latch);
        thread.start();
        try{
            latch.await();
        }catch (InterruptedException err){
            err.printStackTrace();
        }
        item.addLabel("使用率", globalMemoryDto.getPercentage()+"%");
        item.addLabel("已使用",globalMemoryDto.getUsedMemory()+"/"+globalMemoryDto.getTotalMemory());
        item.addLabel("可用", globalMemoryDto.getAvailableMemory());
        item.addLabel("虚拟内存", globalMemoryDto.getVirtualUsedMemory()+"/"+globalMemoryDto.getVirtuallMemory());
        item.addLabel("内存类型/bit",globalMemoryDto.getRamTypeOrOsBitDepth());
        Timer time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new MemoryHandle(globalMemoryDto, globalMemory,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                item.removeAll();
                item.addLabel("使用率", globalMemoryDto.getPercentage()+"%");
                item.addLabel("已使用",globalMemoryDto.getUsedMemory()+"/"+globalMemoryDto.getTotalMemory());
                item.addLabel("可用", globalMemoryDto.getAvailableMemory());
                item.addLabel("虚拟内存", globalMemoryDto.getVirtualUsedMemory()+"/"+globalMemoryDto.getVirtuallMemory());
                item.addLabel("内存类型/bit",globalMemoryDto.getRamTypeOrOsBitDepth());
                item.updateUI();
            }
        });
        time.start();
        return item;
//
    }
    private JPanel processPane3(){
        ImageIcon icon=new ImageIcon("img/computer.png");
        PanelItem item = new PanelItem("磁盘",new GridLayout(4,1), icon, color);
        java.util.List<HWDiskStore> diskStores = systemInfo.getHardware().getDiskStores();
        StorageDto storageDto = dtoCreator.createDto(StorageDto.class);
        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread=new StorageHandle(storageDto, diskStores,latch);
        thread.start();
        try{
            latch.await();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        item.addLabel("I/O", storageDto.getUsedRate()+"%");
        item.addLabel("磁盘类型", storageDto.getMainStorage());
        item.addLabel("磁盘容量", storageDto.getTotal());
        Timer time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new StorageHandle(storageDto, diskStores,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                item.removeAll();
                item.addLabel("I/O", storageDto.getUsedRate()+"%");
                item.addLabel("磁盘类型", storageDto.getMainStorage());
                item.addLabel("磁盘容量", storageDto.getTotal());
                item.updateUI();
            }
        });
        time.start();
        return item;
    }
    private JPanel processPane4(){
        ImageIcon icon = new ImageIcon("img/linux.png");
        PanelItem item=new PanelItem("操作系统",new GridLayout(6,1), icon, color);
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystemDto operatingSystemDto=dtoCreator.createDto(OperatingSystemDto.class);
        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread= new OperatingSystemHandle(operatingSystemDto, operatingSystem, latch);
        thread.start();
        try{
            latch.await();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        item.addLabel("操作系统", operatingSystemDto.getFamily());
        item.addLabel("bit", operatingSystemDto.getBitness()+" bit");
        item.addLabel("版本", operatingSystemDto.getVersionInfo().getVersion());
        item.addLabel("操作系统版本", operatingSystemDto.getVersionInfo().getBuildNumber());
        item.addLabel("开机时间", operatingSystemDto.getSystemboottime());
        /**
         * 操作系统信息不需要动态更新,预留代码
         */
//        Timer time = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Thread thread = new OperatingSystemHandle(operatingSystemDto, operatingSystem,latch);
//                thread.start();
//                try {
//                    latch.await();
//                } catch (InterruptedException err) {
//                    err.printStackTrace();
//                }
//                item.removeAll();
//                item.addLabel("操作系统", operatingSystemDto.getFamily());
//                item.addLabel("bit", operatingSystemDto.getBitness()+" bit");
//                item.addLabel("版本", operatingSystemDto.getVersionInfo().getVersion());
//                item.addLabel("操作系统版本", operatingSystemDto.getVersionInfo().getBuildNumber());
//                item.addLabel("开机时间", operatingSystemDto.getSystemboottime());
//                item.updateUI();
//            }
//        });
//        time.start();
        return item;
    }
    private JPanel processPane5(){
        ImageIcon icon = new ImageIcon("img/graphics.png");
        PanelItem item = new PanelItem(" 显卡 ",new GridLayout(4,1), icon, color);
        java.util.List<GraphicsCard> graphicsCards = systemInfo.getHardware().getGraphicsCards();
        GraphicsCardDto graphicsCardDto = dtoCreator.createDto(GraphicsCardDto.class);
        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread=new GraphicsCardHandle(graphicsCardDto, graphicsCards, latch);
        thread.start();
        try{
            latch.await();
        }catch (InterruptedException err){
            err.printStackTrace();
        }
        item.addLabel("型号", graphicsCardDto.getName());
        item.addLabel("供应商", graphicsCardDto.getVendor());
        item.addLabel("显存", graphicsCardDto.getVram());
        /**
         * 同上
         */
        return item;
    }
    private JPanel processPane6(){
        ImageIcon icon=new ImageIcon("img/pow.png");
        PanelItem item = new PanelItem(" 电源 ",new GridLayout(6,1), icon, color);
        PowerDto powerDto=dtoCreator.createDto(PowerDto.class);
        java.util.List<PowerSource> powerSources = systemInfo.getHardware().getPowerSources();
        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread = new PowHandle(powerDto,powerSources,latch);
        thread.start();
        try{
            latch.await();
        }catch (InterruptedException err){
            err.printStackTrace();
        }
        item.addLabel("设备名称", powerDto.getName());
        item.addLabel("电压", powerDto.getVoltage());
        item.addLabel("当前电量", String.valueOf(powerDto.getCurrentCapacity()/powerDto.getMaxCapacity()));
        item.addLabel("电池健康度", String.valueOf(powerDto.getMaxCapacity()/powerDto.getDesignCapacity()));
        item.addLabel("电池性质", powerDto.getChemistry());
        Timer time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new PowHandle(powerDto, powerSources,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                item.removeAll();
                item.addLabel("设备名称", powerDto.getDeviceName());
                item.addLabel("电压", powerDto.getVoltage());
                item.addLabel("当前电量", String.valueOf(powerDto.getCurrentCapacity()/powerDto.getMaxCapacity()*100)+"%");
                item.addLabel("电池健康度", String.valueOf(powerDto.getMaxCapacity()/powerDto.getDesignCapacity()*100)+"%");
                item.addLabel("电池性质", powerDto.getChemistry());
                item.updateUI();
            }
        });
        time.start();
        return item;
    }

    /**
     * 测试用，对象转json格式
     * @param processorDto
     * @return
     */
//    private String objTojson(ProcessorDto processorDto){
//        Object obj = JSONArray.toJSON(processorDto);
//        String json = obj.toString();
//        return json;
//    }
}
