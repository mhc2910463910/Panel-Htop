package org.lzmhc;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme;
import org.lzmhc.panel.*;
import org.lzmhc.panel.component.GetPanel;
import org.lzmhc.panel.generalPanel.PanelIndex;

import java.awt.*;

/**
 * Hello world!
 */
public class HtopPanel extends JFrame {
    public HtopPanel(){
        initPanel();
    }
    public void initPanel(){
        setTitle("简易的系统监视面板");
        setSize(1080,720);
        PanelIndex panel_index = new PanelIndex();
        JPanel panel_1 = new ProcessorTab(panel_index.getProcessorPanel());
        JPanel panel_2 = new MemoryTab(panel_index.getMemoryPanel());
        JPanel panel_3 = new StorageTab(panel_index.getStoragePanel());
        JPanel panel_4 = new OperatingSystemTab(panel_index.getOperatingSystemPanel());
        JPanel panel_5 = new GraphicsTab(panel_index.getGraphicsPanel());
        JPanel panel_6 = new PowTab(panel_index.getPowPanel());
        JPanel panel_7 = new AboutTab();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("概览",panel_index);
        tabbedPane.addTab("处理器",panel_1);
        tabbedPane.addTab("内存",panel_2);
        tabbedPane.addTab("磁盘",panel_3);
        tabbedPane.addTab("操作系统",panel_4);
        tabbedPane.addTab("显卡",panel_5);
        tabbedPane.addTab("电源",panel_6);
        tabbedPane.addTab("关于",panel_7);
        add(tabbedPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        UIManager.put("TabbedPane.selectedBackground", Color.decode("#355c7d"));
//        UIManager.put("Panel.background", Color.decode("#355c7d"));
        FlatDarkLaf.registerCustomDefaultsSource( "org.lzmhc.theme");
        FlatDarkLaf.install();
        FlatGradiantoNatureGreenIJTheme.install();
//        FlatMaterialDesignDarkIJTheme.install();
//        FlatSolarizedDarkIJTheme
//        FlatMaterialOceanicIJTheme
//        FlatMoonlightIJTheme
//        FlatSolarizedDarkIJTheme
        JFrame htopPanel=new HtopPanel();
        htopPanel.setVisible(true);
    }
}
