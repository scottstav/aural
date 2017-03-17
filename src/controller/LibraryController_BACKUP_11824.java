package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Album;
import model.Artist;
import model.SongEntry;

/**
 * Possible controller for the SongTable.
 * 
 * @author Daniel Garcia
 *
 */
public class LibraryController {
	
    private ObservableList<Integer> tracks = FXCollections.observableArrayList();
	private ObservableList<SongEntry> library = FXCollections.observableArrayList();
	private ObservableList<Artist> artists = FXCollections.observableArrayList();
	private ObservableList<Album> albums = FXCollections.observableArrayList();
	private ArrayList<SongEntry> fullLibrary = new ArrayList<SongEntry>();

	
	public LibraryController() {
		
	}

	public void addSong(File data) throws FileNotFoundException, 
		NoMPEGFramesException, ID3v2FormatException, CorruptHeaderException, IOException 
	{
		// TODO Auto-generated method stub
		MP3File file = new MP3File(data);
		SongEntry song = new SongEntry(file);
		System.out.println("created MP3");
		fullLibrary.add(song);
		library.add(song);
		tracks.add(song.getTrackId());
		if(!artists.contains(new Artist(song.getArtist(), 0, 0)))
		    artists.add(new Artist(song.getArtist(), 0, 0));
		if(!albums.contains(new Album(song.getAlbum(), 0, 0)))
            albums.add(new Album(song.getAlbum(), 0, 0));
		System.out.println("added to library");

		
	}
	
<<<<<<< HEAD
	public ObservableList<Integer> getTracks() {
	    return tracks;
	}
	
	public void clearTracks() {
	    tracks.clear();
=======
	public void filterByArtist(Artist filter) 
	{
		for(SongEntry song : library) 
		{
			if(song.getArtist() != filter.getName()) {
				library.remove(song);
			}
		}
>>>>>>> 1b744dd6d73f57255422af302df32ed53ec94033
	}

	public ObservableList<SongEntry> getSongs() {
		return library;
	}
	
	public void clearSongs()
	{
	    library.clear();
	}

	public ObservableList<Artist> getArtists() {
		// TODO Auto-generated method stub
		return artists;
	}
	
	public void clearArtists()
    {
        artists.clear();
    }
	
	public ObservableList<Album> getAlbums() {
		// TODO Auto-generated method stub
		return albums;
	}
	
	public void clearAlbums()
    {
        albums.clear();
    }
	

}