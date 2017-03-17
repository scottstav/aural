package controller;

import javafx.collections.ObservableList;
import launch.MasterController;
import model.Album;
import model.Artist;

/**
 * Possible controller for the AlbumTable
 * 
 * @author Daniel Garcia
 *
 */
public class AlbumTableController {

	
	public AlbumTableController() 
	{
		// nothing yet
	}
	
	public ObservableList<Album> getAlbums() 
	{
		return MasterController.getInstance().getAlbums();
	}

}
