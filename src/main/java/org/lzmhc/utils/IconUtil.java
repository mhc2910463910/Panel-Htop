package org.lzmhc.utils;

import javax.swing.*;
import java.awt.*;

public class IconUtil {
    public static ImageIcon loadIcon(ImageIcon icon, int px){
        Image image = icon.getImage();
        Image scaleImg = image.getScaledInstance(px,px,Image.SCALE_SMOOTH);
        ImageIcon scaleImage = new ImageIcon(scaleImg);
        return scaleImage;
    }

}
