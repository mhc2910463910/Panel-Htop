package org.lzmhc.component.generalPanel;

import lombok.Getter;
import lombok.Setter;
import org.lzmhc.dto.ItemInterface.InfoItem;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class PanelItem extends JPanel {
    private String title;
    private InfoItem infoItem;
    private JLabel labelTitle;
    public PanelItem(String title, LayoutManager layoutManager, ImageIcon icon, String color){
        this.title=title;
        this.setLayout(layoutManager);
        this.addLabelIcon(icon);
        this.setBackground(Color.decode(color));
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
