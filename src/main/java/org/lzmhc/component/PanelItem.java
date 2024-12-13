package org.lzmhc.component;

import lombok.Getter;
import lombok.Setter;
import org.jb2011.lnf.beautyeye.widget.border.BEDashedRoundRecBorder;
import org.jb2011.lnf.beautyeye.widget.border.BERoundBorder;
import org.jb2011.lnf.beautyeye.widget.border.BEShadowBorder;
import org.jb2011.lnf.beautyeye.widget.border.BEShadowBorder3;
import org.lzmhc.dto.ItemInterface.InfoItem;
import sun.swing.ImageIconUIResource;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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
