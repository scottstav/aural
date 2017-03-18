package controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import launch.MasterController;
import model.Album;

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
		return MasterController.getInstance().getLibraryController().getAlbums();
	}

}
