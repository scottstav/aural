package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SongEntry;

/**
 * Possible controller for the SongTable.
 * 
 * @author Daniel Garcia
 *
 */
public class LibraryController {
	
	private ObservableList<SongEntry> library = FXCollections.observableArrayList();
;
	
	public LibraryController() {
		
	}

	public void addSong(File data) throws FileNotFoundException, 
		NoMPEGFramesException, ID3v2FormatException, CorruptHeaderException, IOException 
	{
		// TODO Auto-generated method stub
		MP3File file = new MP3File(data);
		SongEntry song = new SongEntry(file);
		System.out.println("created MP3");
		library.add(song);
		System.out.println("added to library");

		
	}

	public ObservableList<SongEntry> getSongs() {
		// TODO Auto-generated method stub
		return library;
	}

	

}
