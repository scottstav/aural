package controller;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Song;

/**
 * Possible controller for the SongTable.
 * 
 * @author Daniel Garcia
 *
 */
public class LibraryController {
	
	private ObservableList<Song> library;
	
	public LibraryController() {
		
		library.add(new Song());
		
	}

}
