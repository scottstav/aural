package controller;

import com.sun.speech.freetts.Voice;

import com.sun.speech.freetts.VoiceManager;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import launch.MasterController;
import model.Album;
import model.Artist;
import model.SongEntry;

public class ScreenReader
{
    private Object obj;
    private String sourceType;
    
    public ScreenReader(Object obj, String sourceType)
    {
        this.obj = obj;
        this.sourceType = sourceType;
    }
    
    public void readInfo()
    {
        if(!MasterController.getInstance().isScreenReaderEnabled() || obj == null)
            return;
        
        String text = "";
        if(sourceType.equals("Button"))
        {
            Button b = (Button) obj;
            text = b.getText();
        }
        else if(sourceType.equals("MenuItem"))
        {
            MenuItem m = (MenuItem) obj;
            text = m.getText() + " mapped to " + m.getAccelerator().getName();
            
        }
        else if(sourceType.equals("SongEntry"))
        {
            SongEntry e = (SongEntry) obj;
            text = e.getTitle() + " by " + e.getArtist();
        }
        else if(sourceType.equals("Artist"))
        {
            Artist a = (Artist) obj;
            text = a.getName();
        }
        else if(sourceType.equals("Album"))
        {
            Album al = (Album) obj;
            text = al.getName();
        }
            
        VoiceManager manager = VoiceManager.getInstance();
        Voice kevin = manager.getVoice("kevin");
        kevin.setDurationStretch((float) 1.2);
        kevin.allocate();
        kevin.speak( text );
        kevin.deallocate();
        
    }
}
