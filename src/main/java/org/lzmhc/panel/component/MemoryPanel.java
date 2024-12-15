package org.lzmhc.panel.component;

import org.lzmhc.dto.GlobalMemoryDto;
import org.lzmhc.dto.factory.InfoFactory;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.MemoryHandle;
import oshi.hardware.GlobalMemory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class MemoryPanel extends JPanel implements GetPanel{
    private GlobalMemory globalMemory = InfoDtoSingleton.getInfoDto().getHardware().getMemory();
    private GlobalMemoryDto globalMemoryDto = InfoFactory.createDto(GlobalMemoryDto.class);
    @Override
    public JPanel getPanel(){
        ImageIcon icon = new ImageIcon("img/ram.png");
        PanelItem item = new PanelItem("内存",new GridLayout(6,1), icon);
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
    }
}
