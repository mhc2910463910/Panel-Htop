package org.lzmhc.panel.tab;

import org.lzmhc.handle.ProcessorHandle;
import org.lzmhc.handle.StorageHandle;
import org.lzmhc.panel.component.StoragePanel;
import org.lzmhc.utils.IconUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class StorageTab extends StoragePanel implements templateTab {
    private ImageIcon icon;
    public StorageTab(String path) {
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
                panel.removeAll();
                panel.add(new tabItem("磁盘I/O",storageDto.getUsedRate()+"%"));
                panel.add(new tabItem("磁盘类型", storageDto.getMainStorage()));
                panel.add(new tabItem("磁盘容量", storageDto.getTotal()));
                panel.add(new tabItem("磁盘数量", storageDto.getDiskCount()));
                updateUI();
            }
        });
        time.start();
        return panel;
    }
}
