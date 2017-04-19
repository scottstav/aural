package controller;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import launch.MasterController;
import view.ViewType;

public class MenuController implements EventHandler<ActionEvent> {

	private static final Logger logger = LogManager.getLogger();
	private FileChooser fileChooser;
	
	public MenuController()
	{
	    fileChooser = new FileChooser();
	}

    @Override
    public void handle(ActionEvent arg0)
    {
        MenuItem menuItem = (MenuItem) arg0.getSource();
        String text = menuItem.getText();
        
        if(text == null)
        {
            
        }
        else if(text.equals("Import Music"))
        {
            fileChooser.setTitle("Select music files");
            List<File> selected_songs = fileChooser.showOpenMultipleDialog(MasterController.getInstance().getPrimaryStage());
            for(File file : selected_songs)
                MasterController.getInstance().updateView(ViewType.IMPORT_MUSIC, file);
        }
        else if(text.equals("Play"))
        {
            MasterController.getInstance().getPlaybackController().playSong();
        }
        else if(text.equals("Pause"))
        {
            MasterController.getInstance().getPlaybackController().pauseSong();
        }
        else if(text.equals("Next"))
        {
            MasterController.getInstance().getPlaybackController().nextSong();
        }
        else if(text.equals("Help"))
        {
            MasterController.getInstance().updateView(ViewType.HELP , null);
        }
        else if(text.equals("Previous"))
        {
            MasterController.getInstance().getPlaybackController().previousSong();
        }
        else if(text.equals("Shuffle"))
        {
            MasterController.getInstance().getPlaybackController().shuffleSongs();
        }
        else if(text.equals("Repeat"))
        {
            MasterController.getInstance().getPlaybackController().repeatSong();
        }
        else if(text.equals("Preferences"))
        {
            MasterController.getInstance().updateView(ViewType.PREFERENCES_VIEW, null);
        }
        else if(text.equals("Create Playlist"))
        {
            logger.info("show playlist popup");
            MasterController.getInstance().updateView(ViewType.CREATE_PLAYLIST, null);
        }
        else if(text.equals("Keymap"))
        {
            MasterController.getInstance().updateView(ViewType.KEYMAP_VIEW, null);
        }
        else if(text.equals("Read Menu Items"))
        {
            MasterController.getInstance().getMenuView().readMenuItems();
        } 
        else if (text.equals("Toggle Screen Reader"))
        {
            MasterController.getInstance().toggleScreenReader();
        }
        else if (text.equals("Quit"))
        {
            Platform.exit();
        }
        else 
        {
        	logger.info("unimplemented button");
        }
    }
}
