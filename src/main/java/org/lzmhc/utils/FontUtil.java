package org.lzmhc.utils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FontUtil {
    public static Font loadFont(String path, float fontSize){
        try{
            File file = new File(path);
            FileInputStream fileInputStream=new FileInputStream(file);
            Font sunFont = Font.createFont(Font.TRUETYPE_FONT, fileInputStream);
            Font sunFontPt = sunFont.deriveFont(fontSize);
            fileInputStream.close();
            return sunFontPt;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
