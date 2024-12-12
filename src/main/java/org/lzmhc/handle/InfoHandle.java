package org.lzmhc.handle;


import org.lzmhc.dto.*;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class InfoHandle {
    private SystemInfo systemInfo;
    /**
     * processor info
     * @return
     */
    public void InfoHandle(SystemInfo systemInfo){
        this.systemInfo=systemInfo;
    }
    private String getConvertedFrequency(long[] hertzArray){
        long totalFrequency = Arrays.stream(hertzArray).sum();
        long hertz = totalFrequency / hertzArray.length;
        if((hertz/1E+6)>999){
            return (Math.round((hertz / 1E+9)*10.0)/10.0)+" GHz";
        }else{
            return Math.round(hertz/1E+6)+" MHz";
        }
    }
    private String getConvertedFrequency(long hertz){
        if((hertz/1E+6)>999){
            return (Math.round((hertz / 1E+9)*10.0)/10.0)+" GHz";
        }else{
            return Math.round(hertz/1E+6)+" MHz";
        }
    }
    /**
     * Processor info
     * @return
     */
    private ProcessorDto getProcessor(){
        ProcessorDto processorDto = new ProcessorDto();
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        CentralProcessor.ProcessorIdentifier processorIdentifier = centralProcessor.getProcessorIdentifier();
        String name = processorIdentifier.getName();
        processorDto.setName(name.trim());
        int coreCount = centralProcessor.getLogicalProcessorCount();
        processorDto.setCoreCount(coreCount);
        processorDto.setMaxFreq(getConvertedFrequency(centralProcessor.getMaxFreq()));
        processorDto.setCurrentFreq(getConvertedFrequency(centralProcessor.getCurrentFreq()));
        String BitDepthPrefix = processorIdentifier.isCpu64bit()?"64":"32";
        processorDto.setBitDepth(BitDepthPrefix+"-bit");
        Sensors sensors = systemInfo.getHardware().getSensors();
        String voltage = String.format("%.2f",sensors.getCpuVoltage());
        String temperature = String.format("%.2f",sensors.getCpuTemperature());
        processorDto.setSensoresTemperature(temperature+" ℃");
        processorDto.setSensorsVoltage(voltage+" V");
        int[] fanSpeeds = sensors.getFanSpeeds();
        List<String> speedList = new ArrayList<>();
        for( int speed : fanSpeeds){
            speedList.add(String.valueOf(speed)+"rpm");
        }
        processorDto.setSensoresSpeedList(speedList);
        /**
         * 利用率计算
         */
        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];
        Util.sleep(1000);
        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];
        processorDto.setUsedRate(String.valueOf((int)Math.round((1-((double)(currIdleTicks-prevIdleTicks))/((double)(currTotalTicks-prevTotalTicks)))*100)));

        return processorDto;
    }
    /**
     * OperationSystem info
     * @return
     */
    private OperatingSystemDto getOperatingSystem(){
//        操作系统
        OperatingSystemDto operatingSystemDto=new OperatingSystemDto();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        operatingSystemDto.setBitness(operatingSystem.getBitness());
        operatingSystemDto.setFamily(operatingSystem.getFamily());
        operatingSystemDto.setVersionInfo(operatingSystem.getVersionInfo());
        Long timestamp = operatingSystem.getSystemBootTime();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp*1000));
        operatingSystemDto.setSystemboottime(date);
        return operatingSystemDto;
    }
    /**
     * Memory info
     * @return
     */
    private GlobalMemoryDto getGlobalMemory(){
//        全局内存
        GlobalMemoryDto globalMemoryDto=new GlobalMemoryDto();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        double availabel = memory.getAvailable()/1024.0/1024.0/1024.0;
        globalMemoryDto.setAvailableMemory(String.format("%.2f",availabel)+" GB");
        double total = memory.getTotal()/1024.0/1024.0/1024.0;
        globalMemoryDto.setTotalMemory(String.format("%.2f", total)+" GB");
        double used = total-availabel;
        globalMemoryDto.setUsedMemory(String.format("%.2f", used)+" GB");
        double percentage = used/total;
        globalMemoryDto.setPercentage(percentage);
        double virtualUsedMemory=memory.getVirtualMemory().getSwapUsed()/1024.0/1024.0;
        if(virtualUsedMemory > 999){
            virtualUsedMemory = virtualUsedMemory/1024.0;
            globalMemoryDto.setVirtualUsedMemory(String.format("%.2f", virtualUsedMemory)+" GB");
        }else{
            globalMemoryDto.setVirtualUsedMemory(String.format("%.2f", virtualUsedMemory)+" MB");
        }
        double virtualMemory=memory.getVirtualMemory().getSwapTotal()/1024.0/1024.0/1024.0;
        globalMemoryDto.setVirtuallMemory(String.format("%.2f", virtualMemory)+" GB");

        Optional<PhysicalMemory> physicalMemoryOptional = memory.getPhysicalMemory().stream().findFirst();
        if(physicalMemoryOptional.isPresent()){
            globalMemoryDto.setRamTypeOrOsBitDepth(physicalMemoryOptional.get().getMemoryType());
        }else{
            globalMemoryDto.setRamTypeOrOsBitDepth(systemInfo.getOperatingSystem().getBitness()+" bit");
        }
        int processCount = systemInfo.getOperatingSystem().getProcessCount();
        globalMemoryDto.setProcCount(processCount + ((processCount>1)? "Proces":"Proce"));
        return globalMemoryDto;
    }
    /**
     * BIOS info
     * @return
     */
    private ComputerSystemDto getComputerSystem(){
        ComputerSystemDto computerSystemDto = new ComputerSystemDto();
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        Baseboard baseboard = computerSystem.getBaseboard();
        String manufacturer = baseboard.getManufacturer();
        String model = baseboard.getModel();
        Firmware firmware = computerSystem.getFirmware();
        String version = firmware.getVersion();
        String releaseDate = firmware.getReleaseDate();
        computerSystemDto.setManufacturer(manufacturer);
        computerSystemDto.setModel(model);
        computerSystemDto.setVersion(version);
        computerSystemDto.setRelease_date(releaseDate);
        return computerSystemDto;
    }
    /**
     * Storage info
     * @param bits
     * @return
     */
    private String getConvertedCapacity(long bits) {
        if ((bits / 1.049E+6) > 999)
        {
            if ((bits / 1.074E+9) > 999)
            {
                return (Math.round((bits / 1.1E+12) * 10.0) / 10.0) + " TiB";
            }
            else
            {
                return Math.round(bits / 1.074E+9) + " GiB";
            }
        }
        else
        {
            return Math.round(bits / 1.049E+6) + " MiB";
        }
    }
    /**
     * Storage info
     * @return
     */
    private List<StorageDto> getStorage(){
        List<StorageDto> storageDtosList = new ArrayList<>();
        List<HWDiskStore> hwDiskStoreList = systemInfo.getHardware().getDiskStores();
        for( HWDiskStore hwDiskStore : hwDiskStoreList) {
            StorageDto storageDto = new StorageDto();
            Optional<HWDiskStore> hwDiskStoreOptional = Optional.ofNullable(hwDiskStore);
            if (hwDiskStoreOptional.isPresent()) {
                String mainStorage = hwDiskStoreOptional.get().getModel();
                if (mainStorage.contains("(Standard disk drives)")) {
                    mainStorage = mainStorage.substring(0, mainStorage.indexOf("(Standard disk drives)") - 1);
                }
                storageDto.setMainStorage(mainStorage.trim());
            } else {
                storageDto.setMainStorage("Undefined");
            }
            long total = hwDiskStoreList.stream().mapToLong(HWDiskStore::getSize).sum();
            storageDto.setTotal(getConvertedCapacity(total) + " Total");
            int diskCount = hwDiskStoreList.size();
            storageDto.setDiskCount(diskCount + ((diskCount > 1) ? "Disks" : "Disk"));
            FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
            long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
            long freeStorages = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();
            String usedRate = String.valueOf((int)Math.round(((double)(totalStorage-freeStorages)/totalStorage)*100));
            storageDto.setUsedRate(usedRate);
            storageDtosList.add(storageDto);
        }
        return storageDtosList;
    }
    /**
     * 显卡
     * @return
     */
    private GraphicsCardDto getGraphicsCards(){
        GraphicsCardDto graphicsCardDto = new GraphicsCardDto();
        List<GraphicsCard> graphicsCards = systemInfo.getHardware().getGraphicsCards();
        graphicsCardDto.setGraphicsCardList(graphicsCards);
        return graphicsCardDto;
    }
    /**
     * Power info
     * @return
     */
    private PowerDto getPowerSource(){
        PowerDto powerDto = new PowerDto();
        List<PowerSource> powerSources = systemInfo.getHardware().getPowerSources();
        for(PowerSource powerSource:powerSources){
            if(!powerSource.getDeviceName().equals("unknown")){
                powerDto.setName(powerSource.getName());
                powerDto.setDeviceName(powerSource.getDeviceName());
                DecimalFormat format=new DecimalFormat("#.00");
                String voltage = format.format((powerSource.getVoltage()));
                powerDto.setVoltage(voltage+" V");
                powerDto.setPowerOnLine(powerSource.isPowerOnLine());
                powerDto.setCharging(powerSource.isCharging());
                powerDto.setDischarging(powerSource.isDischarging());
                powerDto.setCurrentCapacity(powerSource.getCurrentCapacity());
                powerDto.setMaxCapacity(powerSource.getMaxCapacity());
                powerDto.setDesignCapacity(powerSource.getDesignCapacity());
                powerDto.setChemistry(powerSource.getChemistry());
                powerDto.setManufacturer(powerSource.getManufacturer());
                return powerDto;
            }
        }
        return null;
    }
    public InfoDto getInfo() {
        InfoDto infoDto = new InfoDto();
        infoDto.setProcessorDto(this.getProcessor());
        infoDto.setOperatingSystemDto(this.getOperatingSystem());
        infoDto.setGlobalMemoryDto(this.getGlobalMemory());
        infoDto.setComputerSystemDto(this.getComputerSystem());
        infoDto.setPowerDto(this.getPowerSource());
        infoDto.setStorageDtoList(this.getStorage());
        infoDto.setGraphicsCardDto(this.getGraphicsCards());
        return infoDto;
    }
}
