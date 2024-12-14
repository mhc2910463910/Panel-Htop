package org.lzmhc.themes;

import mdlaf.shadows.DropShadowBorder;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.utils.MaterialBorders;
import org.lzmhc.utils.FontUtil;

import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

/**
 * 修复中文字体方框
 */
public class MaterialOrientalTheme extends MaterialLiteTheme {
    @Override
    protected void installFonts() {
        Font font = FontUtil.loadFont("font/AaBanRuoKaiShuJiaCu-2.ttf", 18f);
        this.fontBold = new javax.swing.plaf.FontUIResource(font);
        this.fontItalic = new javax.swing.plaf.FontUIResource(font);
        this.fontMedium = new javax.swing.plaf.FontUIResource(font);
        this.fontRegular = new javax.swing.plaf.FontUIResource(font);
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
