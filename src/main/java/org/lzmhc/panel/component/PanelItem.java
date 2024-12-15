package org.lzmhc.panel.component;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;
import org.lzmhc.utils.FontUtil;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class PanelItem extends JPanel {
    private String title;
    private JLabel labelTitle;
    public PanelItem(String title, LayoutManager layoutManager, ImageIcon icon){
        this.title=title;
        this.setBackground(Color.decode("#20403f"));
        this.setLayout(layoutManager);
        this.addLabelIcon(icon);
    }
    public void addLabelIcon(ImageIcon icon){
        Image image = icon.getImage();
        Image scaleImg = image.getScaledInstance(64,64,Image.SCALE_SMOOTH);
        ImageIcon scaleImage = new ImageIcon(scaleImg);
        labelTitle = new JLabel(scaleImage);
        this.add(labelTitle);
    }
    public void removeAll(){
        JLabel label=this.labelTitle;
        super.removeAll();
        this.add(label);
    }
    public void addLabel(String name, String value){
        JLabel label=new JLabel(name+" : "+value);
        this.add(label);
    }
}
