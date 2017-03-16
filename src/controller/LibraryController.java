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
import model.Artist;
import model.SongEntry;

/**
 * Possible controller for the SongTable.
 * 
 * @author Daniel Garcia
 *
 */
public class LibraryController {
	
	private ObservableList<SongEntry> library = FXCollections.observableArrayList();
	private ObservableList<Artist> artists = FXCollections.observableArrayList();

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
		artists.add(new Artist(song.getArtist(), 0));
		System.out.println("added to library");

		
	}

	public ObservableList<SongEntry> getSongs() {
		// later, this is where we will handle filtering results based on selected artist / album
		return library;
	}

	public ObservableList<Artist> getArtists() {
		// TODO Auto-generated method stub
		return artists;
	}
	

}
