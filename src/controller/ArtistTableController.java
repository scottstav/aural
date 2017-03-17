package controller;

import javafx.collections.ObservableList;
import launch.MasterController;
import model.Artist;
import model.SongEntry;

/**
 * Possible controller for the AuthorTable
 * 
 * @author Daniel Garcia
 *
 */
public class ArtistTableController {
	
	public ArtistTableController() 
	{
		// nothing yet
	}
	
	public ObservableList<Artist> getArtists() 
	{
		return MasterController.getInstance().getLibraryController().getArtists();
	}

}
