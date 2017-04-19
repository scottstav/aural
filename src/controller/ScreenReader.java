package controller;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import launch.MasterController;
import model.Album;
import model.Artist;
import model.Playlist;
import model.SongEntry;

public class ScreenReader
{
    private Object obj;
    private String sourceType;
    private Voice kevin;
    private VoiceManager manager;
    
    private boolean isReading = false;
    
    public ScreenReader(Object obj, String sourceType)
    {
        this.obj = obj;
        this.sourceType = sourceType;
        manager = VoiceManager.getInstance();
        kevin = manager.getVoice("kevin");
        kevin.setDurationStretch((float) 1.2);
        kevin.allocate();
    }
    
    public void readInfo()
    {
        if(!MasterController.getInstance().isScreenReaderEnabled() || obj == null)
            return;
        if(isReading)
        
        isReading = true;
        
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
        else if (sourceType.equals("Table"))			// telll us what table we are looking at
        {
        	TableView table = (TableView) obj;
        	text = table.getAccessibleText();
        }
        else if (sourceType.equals("List"))				// tell us what list we are looking at
        {
        	ListView list = (ListView) obj;
        	text = list.getAccessibleText();
        }
        else if (sourceType.equals("Playlist"))
        {
        	Playlist pl = (Playlist) obj;
        	text = pl.getName();
        }
        else if (sourceType.equals("TextField"))
        {
            text = "Type in the name of a new playlist";
        }
            
        kevin.speak( text );
        kevin.deallocate();
        isReading = false;
    }
    
    public void setObject(Object obj)
    {
        this.obj = obj;
    }
    
    public Object getObject()
    {
        return this.obj;
    }
    
    public void setSourceType(String sourceType)
    {
        this.sourceType = sourceType;
    }
    
    public String getSourceType()
    {
        return this.sourceType;
    }
}
