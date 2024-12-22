package org.lzmhc;

import javax.swing.*;

import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialOceanicIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme;
import org.lzmhc.dto.singleton.InfoDtoSingleton;
import org.lzmhc.panel.component.PartitionPanel;
import org.lzmhc.panel.generalPanel.PanelIndex;
import org.lzmhc.panel.tab.*;
import org.lzmhc.utils.FontUtil;
import org.lzmhc.utils.IconUtil;

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
        String family = InfoDtoSingleton.getInfoDto().getOperatingSystem().getFamily().toLowerCase();
        String OSlogo="";
        if(family.equals("windows")){
            OSlogo="img/windows.png";
        }else if(family.equals("deepin")){
            OSlogo="img/deepin.png";
        }else if(family.equals("ubuntu")){
            OSlogo="img/ubuntu.png";
        }else if(family.equals("debian")){
            OSlogo="img/debian.png";
        }else if(family.equals("arch")){
            OSlogo="img/arch.png";
        }else{
            OSlogo="img/linux.png";
        }
        JPanel panel_4 = new OperatingSystemTab(OSlogo);
        JPanel panel_5 = new GraphicsTab( "img/graphics.png");
        JPanel panel_6 = new PowTab("img/pow.png");
        JPanel panel_7 = new PartitionTab();
        JPanel panel_8 = new NetworkTab();
        JPanel panel_9 = new AboutTab();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("概览",panel_index);
        tabbedPane.addTab("处理器",panel_1);
        tabbedPane.addTab("内存",panel_2);
        tabbedPane.addTab("磁盘",panel_3);
        tabbedPane.addTab("操作系统",panel_4);
        tabbedPane.addTab("显卡",panel_5);
        tabbedPane.addTab("电源",panel_6);
        tabbedPane.addTab("分区",panel_7);
        tabbedPane.addTab("网卡设备",panel_8);
        tabbedPane.addTab("关于",panel_9);
        add(tabbedPane);
        Toolkit tookit=Toolkit.getDefaultToolkit();
        this.setIconImage(IconUtil.loadIcon(new ImageIcon("img/logo.png"), 512).getImage());
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
        UIManager.put("TextComponent.arc", 10);
        FlatSolarizedDarkIJTheme.setup();
        JFrame htopPanel=new HtopPanel();
        htopPanel.setVisible(true);
//        FlatMaterialDesignDarkIJTheme.install();
//        FlatSolarizedDarkIJTheme.install();
//        FlatMaterialOceanicIJTheme.install();
//        FlatMoonlightIJTheme.install();

    }
}
