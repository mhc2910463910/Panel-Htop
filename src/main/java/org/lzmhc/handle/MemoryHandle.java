package org.lzmhc.handle;

import org.lzmhc.dto.GlobalMemoryDto;
import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class MemoryHandle extends Thread implements InfoBuild{
    InfoItem infoItem;
    Object obj;
    private final CountDownLatch latch;
    public MemoryHandle(InfoItem infoItem, Object obj, CountDownLatch latch){
        this.infoItem=infoItem;
        this.obj=obj;
        this.latch=latch;
    }

    @Override
    public void build() {
        GlobalMemoryDto globalMemoryDto = (GlobalMemoryDto) infoItem;
        GlobalMemory memory = (GlobalMemory) obj;
        double availabel = memory.getAvailable()/1024.0/1024.0/1024.0;
        globalMemoryDto.setAvailableMemory(String.format("%.2f",availabel)+" GB");
        double total = memory.getTotal()/1024.0/1024.0/1024.0;
        globalMemoryDto.setTotalMemory(String.format("%.2f", total)+" GB");
        double used = total-availabel;
        globalMemoryDto.setUsedMemory(String.format("%.2f", used)+" GB");
        double percentage = Double.parseDouble(String.format("%.2f",used/total*100));
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
            globalMemoryDto.setRamTypeOrOsBitDepth(InfoDtoSingleton.getInfoDto().getOperatingSystem().getBitness()+" bit");
        }
        int processCount = InfoDtoSingleton.getInfoDto().getOperatingSystem().getProcessCount();
        globalMemoryDto.setProcCount(processCount + ((processCount>1)? "Proces":"Proce"));
    }

    @Override
    public void run() {
        try{
            this.build();
        }finally {
            latch.countDown();
        }

    }
}
