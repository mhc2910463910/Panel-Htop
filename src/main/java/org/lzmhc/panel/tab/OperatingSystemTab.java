package org.lzmhc.panel.tab;

import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.handle.OperatingSystemHandle;
import org.lzmhc.handle.ProcessorHandle;
import org.lzmhc.panel.component.OperatingSystemPanel;
import org.lzmhc.panel.component.PanelItem;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class OperatingSystemTab extends OperatingSystemPanel implements templateTab {
    private ImageIcon icon;
    public OperatingSystemTab(String path) {
        JPanel iconPanel=new JPanel();
        this.icon = IconUtil.loadIcon(new ImageIcon(path), 128);
        JLabel label = new JLabel( this.icon);
        iconPanel.add(label);
        iconPanel.setBackground(Color.decode("#155263"));
        this.setLayout(new BorderLayout());
        this.add(iconPanel, BorderLayout.NORTH);
        this.add(getPanel(), BorderLayout.CENTER);
    }
    @Override
    public JPanel getPanel() {
        JPanel panel=new JPanel();
        LayoutManager layoutManager=new GridLayout(12,1);
        panel.setLayout(layoutManager);
        CountDownLatch latch = new CountDownLatch(numThreads);
        Timer time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new OperatingSystemHandle(operatingSystemDto, operatingSystem,latch);
                thread.start();
                try {
                    latch.await();
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
                panel.removeAll();
                panel.add(new tabItem("操作系统",operatingSystemDto.getFamily()));
                panel.add(new tabItem("bit", operatingSystemDto.getBitness()+" bit"));
                panel.add(new tabItem("版本", operatingSystemDto.getVersionInfo().getVersion()));
                panel.add(new tabItem("操作系统版本", operatingSystemDto.getVersionInfo().getBuildNumber()));
                panel.add(new tabItem("开机时间", operatingSystemDto.getSystemboottime()));
                updateUI();
            }
        });
        time.start();
        return panel;
    }
}
