package org.lzmhc.themes;

import mdlaf.shadows.DropShadowBorder;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.utils.MaterialBorders;

import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * 修复中文字体方框
 */
public class MaterialOrientalTheme extends MaterialLiteTheme {
    @Override
    protected void installFonts() {
        this.fontBold = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.BOLD,20);
        this.fontItalic = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.ITALIC,20);
        this.fontMedium = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.PLAIN,20);
        this.fontRegular = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.PLAIN,20);
    }

    @Override
    protected void installColor() {
        super.installColor();
        this.textColor=new ColorUIResource(Color.green);
    }
    @Override
    protected void installBorders() {
        super.installBorders();
        this.borderPanel=MaterialBorders.roundedLineColorBorder(Color.CYAN, 20);
    }

}
