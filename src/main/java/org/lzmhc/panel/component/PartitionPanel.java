package org.lzmhc.panel.component;

import org.lzmhc.panel.tab.PartitionTab;

import javax.swing.*;
import java.awt.*;

public class PartitionPanel extends JPanel {
    private JTextField name;
    private JTextField mount;
    private JTextField fsType;
    private JTextField freeSpace;
    private JTextField usedSpace;
    private JTextField totalSpace;
    public static JPanel getTitleTable(){
        JPanel titleTable=new JPanel();
        titleTable.setLayout(new GridLayout(1,6));
        JLabel name=new JLabel("分区名称");
        name.setHorizontalAlignment(0);
        JLabel mount=new JLabel("挂载点");
        mount.setHorizontalAlignment(0);
        JLabel fsType=new JLabel("文件系统");
        fsType.setHorizontalAlignment(0);
        JLabel freeSpace=new JLabel("空闲空间");
        freeSpace.setHorizontalAlignment(0);
        JLabel usedSpace=new JLabel("已用");
        usedSpace.setHorizontalAlignment(0);
        JLabel totalSpace=new JLabel("总空间");
        totalSpace.setHorizontalAlignment(0);
        titleTable.add(name);
        titleTable.add(mount);
        titleTable.add(fsType);
        titleTable.add(freeSpace);
        titleTable.add(usedSpace);
        titleTable.add(totalSpace);
        return titleTable;
    }
    public PartitionPanel(String name, String mount, String fsType, long freeSpace, long usedSpace, long totalSpace){
        this.setLayout(new GridLayout(1,6));
        this.name=new JTextField(name);
        this.name.setHorizontalAlignment(0);
        this.mount=new JTextField(mount);
        this.mount.setHorizontalAlignment(0);
        this.fsType=new JTextField(fsType);
        this.fsType.setHorizontalAlignment(0);
        if(freeSpace/1024.0/1024.0>999){
            this.freeSpace=new JTextField(String.format("%.2f", freeSpace/1024.0/1024.0/1024.0)+" GB");
        }else{
            this.freeSpace=new JTextField(String.format("%.2f", freeSpace/1024.0/1024.0)+" MB");
        }
        this.freeSpace.setHorizontalAlignment(0);
        if(usedSpace/1024.0/1024.0>999){
            this.usedSpace=new JTextField(String.format("%.2f", usedSpace/1024.0/1024.0/1024.0)+" GB");
        }else{
            this.usedSpace=new JTextField(String.format("%.2f", usedSpace/1024.0/1024.0)+" MB");
        }
        this.usedSpace.setHorizontalAlignment(0);
        if(totalSpace/1024.0/1024.0>999){
            this.totalSpace=new JTextField(String.format("%.2f", totalSpace/1024.0/1024.0/1024.0)+" GB");
        }else{
            this.totalSpace=new JTextField(String.format("%.2f", totalSpace/1024.0/1024.0)+" MB");
        }
        this.totalSpace.setHorizontalAlignment(0);
        this.add(this.name);
        this.add(this.mount);
        this.add(this.fsType);
        this.add(this.freeSpace);
        this.add(this.usedSpace);
        this.add(this.totalSpace);
    }
}
