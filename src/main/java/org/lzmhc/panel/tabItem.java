package org.lzmhc.panel;

import javax.swing.*;
import java.awt.*;

public class tabItem extends JPanel {
    private JLabel title;
    private JTextField text;
    public tabItem(String title, String value){
        this.title=new JLabel(title);
        this.title.setPreferredSize(new Dimension(200,50));
        this.title.setHorizontalAlignment(0);
        this.text=new JTextField(value);
        this.text.setHorizontalAlignment(0);
        this.setLayout(new BorderLayout());
        this.add(this.title,BorderLayout.WEST);
        this.add(this.text,BorderLayout.CENTER);

    }
}
