package model;

import javafx.scene.text.Font;

public class Preferences
{
    private int screenWidth;
    private int screenLength;
    private Font font;
    private int fontSize;
    
    public Preferences()
    {
        font = new Font(12);
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth)
    {
        this.screenWidth = screenWidth;
    }

    public int getScreenLength()
    {
        return screenLength;
    }

    public void setScreenLength(int screenLength)
    {
        this.screenLength = screenLength;
    }

    public Font getFont()
    {
        return font;
    }

    public void setFont(Font font)
    {
        this.font = font;
    }

    
    public int getFontSize()
    {
        return fontSize;
    }

    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
    }
    
    
}
