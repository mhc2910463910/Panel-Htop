package org.lzmhc.panel.component;

import org.lzmhc.dto.PowerDto;
import org.lzmhc.dto.factory.InfoFactory;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.PowHandle;
import oshi.hardware.PowerSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class PowPanel extends JPanel implements GetPanel{
    PowerDto powerDto= InfoFactory.createDto(PowerDto.class);
    java.util.List<PowerSource> powerSources = InfoDtoSingleton.getInfoDto().getHardware().getPowerSources();
    @Override
    public JPanel getPanel(){
        ImageIcon icon=new ImageIcon("img/pow.png");
        PanelItem item = new PanelItem(" 电源 ",new GridLayout(6,1), icon);
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
        item.addLabel("当前电量", String.format("%.1f",powerDto.getCurrentCapacity()/powerDto.getMaxCapacity()));
        item.addLabel("电池健康度", String.format("%.1f",powerDto.getMaxCapacity()/powerDto.getDesignCapacity()));
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
                item.addLabel("当前电量", String.format("%.1f",powerDto.getCurrentCapacity()/powerDto.getMaxCapacity()*100)+"%");
                item.addLabel("电池健康度", String.format("%.1f",powerDto.getMaxCapacity()/powerDto.getDesignCapacity()*100)+"%");
                item.addLabel("电池性质", powerDto.getChemistry());
                item.updateUI();
            }
        });
        time.start();
        return item;
    }
}
