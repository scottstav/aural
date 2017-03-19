package controller;

import javafx.collections.ObservableList;
import launch.MasterController;
import model.SongEntry;

public class SongTableController {
	
	public SongTableController() 
	{
		
	}

	public ObservableList<SongEntry> getSongs() {
		return MasterController.getInstance().getLibraryController().getSongs();
	}

}
