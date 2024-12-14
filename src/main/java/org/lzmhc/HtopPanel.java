package org.lzmhc;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;

import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialBorders;
import org.jb2011.lnf.beautyeye.*;
import org.lzmhc.component.Panel1;

/**
 * Hello world!
 */
public class HtopPanel extends JFrame {
    public HtopPanel(){
        initPanel();
    }
    public void initPanel(){
        setTitle("简易的监控面板");
        setSize(1080,720);
        JPanel panel_1 = new Panel1();
        JPanel panel_2 = this.createPanel2();
        JPanel panel_3 = this.createPanel3();
        JPanel panel_4 = this.createPanel4();
        JPanel panel_5 = this.createPanel5();
        JPanel panel_6 = this.createPanel6();
        JPanel panel_7 = this.createPanel7();

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


    /**
     * 处理器
     * @return
     */
    public JPanel createPanel2(){
        JPanel panel = new JPanel();

        return panel;
    }
    /**
     * 内存
     * @return
     */
    public JPanel createPanel3(){
        JPanel panel = new JPanel();

        return panel;
    }
    /**
     * 磁盘
     * @return
     */
    public JPanel createPanel4(){
        JPanel panel = new JPanel();

        return panel;
    }
    /**
     * 操作系统
     * @return
     */
    public JPanel createPanel5(){
        JPanel panel = new JPanel();

        return panel;
    }
    /**
     * 显卡
     * @return
     */
    public JPanel createPanel6(){
        JPanel panel = new JPanel();

        return panel;
    }
    /**
     * 电源
     * @return
     */
    public JPanel createPanel7(){
        JPanel panel = new JPanel();

        return panel;
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
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOrientalFontsTheme()));
        }catch (Exception e){
            e.printStackTrace();
        }
        JFrame htopPanel=new HtopPanel();
        htopPanel.setVisible(true);
    }
}
