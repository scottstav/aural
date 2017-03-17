package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	private ObservableList<SongEntry> fullLibrary = FXCollections.observableArrayList();

	FilteredList<SongEntry> filteredSongs;
	FilteredList<Album> filteredAlbums;

	
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
	    artists.add(new Artist(song.getArtist(), 0, 0));
        albums.add(new Album(song.getAlbum(), 0, 0));


		/*
		if(!artists.contains(new Artist(song.getArtist(), 0, 0)))
		    artists.add(new Artist(song.getArtist(), 0, 0));
		if(!albums.contains(new Album(song.getAlbum(), 0, 0)))
            albums.add(new Album(song.getAlbum(), 0, 0));
        */
		System.out.println("added to library");
		
		filteredSongs = new FilteredList<>(fullLibrary, p -> true);
		filteredAlbums = new FilteredList<>(albums, p -> true);


		
	}
	
	public void filterByArtist(Artist filter) 
	{
		
		filteredSongs.setPredicate(p -> {
            // If filter text is empty, display all songs.
            if (filter == null) {
            	System.out.println("XXfilteringXX");
                return true;
            }


            if (filter.getName().equals(p.getArtist())) {
            	System.out.println("XXfilteringXX");

                return true; // Filter matches artist.
            }
        	System.out.println("YYfilteringYY");

            return false; // Does not match.
        });
		
		library.setAll(filteredSongs);
	}
		
	/*public ObservableList<Integer> getTracks() {
	    return tracks;
	}
	
	public void clearTracks() {
	    tracks.clear();
	}*/

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
