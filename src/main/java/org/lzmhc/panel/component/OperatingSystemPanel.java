package org.lzmhc.panel.component;

import org.lzmhc.dto.OperatingSystemDto;
import org.lzmhc.dto.factory.InfoFactory;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.OperatingSystemHandle;
import oshi.software.os.OperatingSystem;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class OperatingSystemPanel extends JPanel implements GetPanel{
    OperatingSystem operatingSystem = InfoDtoSingleton.getInfoDto().getOperatingSystem();
    OperatingSystemDto operatingSystemDto= InfoFactory.createDto(OperatingSystemDto.class);
    @Override
    public JPanel getPanel(){
        ImageIcon icon = null;
        if(InfoDtoSingleton.getInfoDto().getOperatingSystem().getFamily().toLowerCase().equals("windows")){
            icon = new ImageIcon("img/windows.png");
        }else{
            icon = new ImageIcon("img/linux.png");
        }
        PanelItem item=new PanelItem("操作系统",new GridLayout(6,1), icon);
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
}
