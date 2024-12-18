package org.lzmhc.panel.component;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class NetworkPanel extends JPanel {
    private JTextField name;
    private JTextField displayname;
    private JTextField ipv4addr;
    private JTextField ipv6addr;
    private JTextField macaddr;
    private JTextField mtu;
    public static JPanel getTitleTable(){
        JPanel titleTable=new JPanel();
        titleTable.setLayout(new GridLayout(1,6));
        JLabel name=new JLabel("名称");
        name.setHorizontalAlignment(0);
        JLabel displayname=new JLabel("描述");
        displayname.setHorizontalAlignment(0);
        JLabel ipv4addr=new JLabel("ipv4");
        ipv4addr.setHorizontalAlignment(0);
        JLabel ipv6addr=new JLabel("ipv6");
        ipv6addr.setHorizontalAlignment(0);
        JLabel macaddr=new JLabel("MAC");
        macaddr.setHorizontalAlignment(0);
        JLabel mtu=new JLabel("MTU");
        mtu.setHorizontalAlignment(0);
        titleTable.add(name);
        titleTable.add(displayname);
        titleTable.add(ipv4addr);
        titleTable.add(ipv6addr);
        titleTable.add(macaddr);
        titleTable.add(mtu);
        return titleTable;
    }
    public NetworkPanel(String name, String displayname, String[] ipv4addr, String[] ipv6addr, String macaddr, long mtu){
        this.setLayout(new GridLayout(1,6));
        this.name=new JTextField(name);
        this.name.setHorizontalAlignment(0);
        this.displayname=new JTextField(displayname);
        this.displayname.setHorizontalAlignment(0);
        this.ipv4addr=new JTextField(Arrays.toString(ipv4addr));
        this.ipv4addr.setHorizontalAlignment(0);
        this.ipv6addr=new JTextField(Arrays.toString(ipv6addr));
        this.ipv6addr.setHorizontalAlignment(0);
        this.macaddr=new JTextField(macaddr);
        this.macaddr.setHorizontalAlignment(0);
        this.mtu=new JTextField(mtu+"");
        this.mtu.setHorizontalAlignment(0);
        this.add(this.name);
        this.add(this.displayname);
        this.add(this.ipv4addr);
        this.add(this.ipv6addr);
        this.add(this.macaddr);
        this.add(this.mtu);
    }
}
