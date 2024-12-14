package org.lzmhc;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MaterialOrientalFontsTheme extends MaterialLiteTheme {
    @Override
    protected void installFonts() {
        this.fontBold = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.BOLD,20);
        this.fontItalic = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.ITALIC,20);
        this.fontMedium = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.PLAIN,20);
        this.fontRegular = new javax.swing.plaf.FontUIResource(Font.MONOSPACED, Font.PLAIN,20);
        this.setTextColor(new ColorUIResource(Color.green));
    }
}
