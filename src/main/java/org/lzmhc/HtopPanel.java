package org.lzmhc;

import javax.swing.*;

import mdlaf.MaterialLookAndFeel;
import org.lzmhc.component.Panel1;
import org.lzmhc.themes.MaterialOrientalTheme;

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
        JPanel panel_1 = new Panel1();
        JPanel panel_2 = new JPanel();
        JPanel panel_3 = new JPanel();
        JPanel panel_4 = new JPanel();
        JPanel panel_5 = new JPanel();
        JPanel panel_6 = new JPanel();
        JPanel panel_7 = new JPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("概览",panel_1);
        tabbedPane.addTab("处理器",panel_2);
        tabbedPane.addTab("内存",panel_3);
        tabbedPane.addTab("磁盘",panel_4);
        tabbedPane.addTab("操作系统",panel_5);
        tabbedPane.addTab("显卡",panel_6);
        tabbedPane.addTab("电源",panel_7);
        add(tabbedPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try{
            /**
             * beautyeyeUI
             */
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
//            UIManager.put("RootPane.setupButtonVisible", false);
//            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            /**
             * Material-UI-Swing
             */
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOrientalTheme()));
        }catch (Exception e){
            e.printStackTrace();
        }
        JFrame htopPanel=new HtopPanel();
        htopPanel.setVisible(true);
    }
}
