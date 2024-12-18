package org.lzmhc.panel.component;

import org.lzmhc.dto.StorageDto;
import org.lzmhc.dto.factory.InfoFactory;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.StorageHandle;
import oshi.hardware.HWDiskStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class StoragePanel extends JPanel implements GetPanel{
    protected java.util.List<HWDiskStore> diskStores = InfoDtoSingleton.getInfoDto().getHardware().getDiskStores();
    protected StorageDto storageDto = InfoFactory.createDto(StorageDto.class);
    @Override
    public JPanel getPanel(){
        ImageIcon icon=new ImageIcon("img/ssd.png");
        PanelItem item = new PanelItem("磁盘",new GridLayout(6,1), icon);
        CountDownLatch latch=new CountDownLatch(numThreads);
        Thread thread=new StorageHandle(storageDto, diskStores,latch);
        thread.start();
        try{
            latch.await();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        item.addLabel("使用率", storageDto.getUsedRate()+"%");
        item.addLabel("磁盘类型", storageDto.getMainStorage());
        item.addLabel("磁盘容量", storageDto.getTotal());
        item.addLabel("写入次数", storageDto.getWriteCount()+"");
        item.addLabel("读取次数", storageDto.getReadCount()+"");
        Timer time = new Timer(5000, new ActionListener() {
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
                item.addLabel("使用率", storageDto.getUsedRate()+"%");
                item.addLabel("磁盘类型", storageDto.getMainStorage());
                item.addLabel("磁盘容量", storageDto.getTotal());
                item.addLabel("写入次数", storageDto.getWriteCount()+"");
                item.addLabel("读取次数", storageDto.getReadCount()+"");
                item.updateUI();
//                System.out.println(objTojson(storageDto));
            }
        });
        time.start();
        return item;
    }
}
