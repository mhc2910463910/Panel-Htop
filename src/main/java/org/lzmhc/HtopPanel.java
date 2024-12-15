package org.lzmhc;

import javax.swing.*;

import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.panel.*;
import org.lzmhc.panel.generalPanel.PanelIndex;
import org.lzmhc.utils.FontUtil;

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
        JPanel panel_1 = new ProcessorTab("img/cpu.png");
        JPanel panel_2 = new MemoryTab("img/ram.png");
        JPanel panel_3 = new StorageTab( "img/ssd.png");
        JPanel panel_4 = new OperatingSystemTab(InfoDtoSingleton.getInfoDto().getOperatingSystem().getFamily().toLowerCase().equals("windows")?"img/windows.png":"img/linux.png");
        JPanel panel_5 = new GraphicsTab( "img/graphics.png");
        JPanel panel_6 = new PowTab("img/pow.png");
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
//        this.setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
//        FlatLaf.registerCustomDefaultsSource( "org.lzmhc.themes");
        Font sunfont= FontUtil.loadFont("font/sun.ttf", 20f);
        Font font= FontUtil.loadFont("font/AaBanRuoKaiShuJiaCu-2.ttf", 20f);
        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
        UIManager.put("TabbedPane.tabWidthMode", "equal");
        UIManager.put("TabbedPane.font", sunfont);
        UIManager.put("Label.font", font);
        UIManager.put("Label.foreground", Color.decode("#1fab89"));
        UIManager.put("TextField.foreground", Color.green);
        FlatSolarizedDarkIJTheme.setup();
        JFrame htopPanel=new HtopPanel();
        htopPanel.setVisible(true);
//        FlatMaterialDesignDarkIJTheme.install();
//        FlatSolarizedDarkIJTheme.install();
//        FlatMaterialOceanicIJTheme.install();
//        FlatMoonlightIJTheme.install();
//        FlatSolarizedDarkIJTheme.install();

    }
}
