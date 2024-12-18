package org.lzmhc.handle;


import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.dto.StorageDto;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import oshi.hardware.HWDiskStore;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class StorageHandle extends Thread implements InfoBuild{
    InfoItem infoItem;
    Object obj;
    private final CountDownLatch latch;
    public StorageHandle(InfoItem infoItem, Object obj, CountDownLatch latch){
        this.infoItem=infoItem;
        this.obj=obj;
        this.latch=latch;
    }
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
    @Override
    public void build() {
        StorageDto storageDto = (StorageDto)infoItem;
        List<HWDiskStore> hwDiskStoreList = (List<HWDiskStore>) obj;
        HWDiskStore hwDiskStore = hwDiskStoreList.get(hwDiskStoreList.size()-1);
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
            storageDto.setTotal(getConvertedCapacity(total) + "");
            int diskCount = hwDiskStoreList.size();
            storageDto.setDiskCount(diskCount + ((diskCount > 1) ? "Disks" : "Disk"));
            FileSystem fileSystem = InfoDtoSingleton.getInfoDto().getOperatingSystem().getFileSystem();
            long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
            long freeStorages = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();
            String usedRate = String.valueOf((int)Math.round(((double)(totalStorage-freeStorages)/totalStorage)*100));
            storageDto.setUsedRate(usedRate);

            storageDto.setReadCount(hwDiskStore.getReads());
            storageDto.setReadbytes((long) (hwDiskStore.getReadBytes()/1024.0/1024.0/1024.0));
            storageDto.setWriteCount(hwDiskStore.getWrites());
            storageDto.setWritebytes((long) (hwDiskStore.getWriteBytes()/1024.0/1024.0/1024.0));
//            System.out.println(storageDto.getUsedRate());
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
