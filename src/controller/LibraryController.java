package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import launch.MasterController;
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

	FilteredList<SongEntry> filteredData;
	
	public LibraryController() {
		this.update();

	}

	public void update() 
	{
		// TODO Auto-generated method stub

		fullLibrary = FXCollections.observableArrayList( MasterController.getInstance().getGateway().getSongEntrys());
		library = fullLibrary;
		
		/*
		tracks.add(song.getTrackId());
	    artists.add(new Artist(song.getArtist(), 0, 0));
        albums.add(new Album(song.getAlbum(), 0, 0));
		*/
		updateArtists();
		updateAlbums();

		/*
		if(!artists.contains(new Artist(song.getArtist(), 0, 0)))
		    artists.add(new Artist(song.getArtist(), 0, 0));
		if(!albums.contains(new Album(song.getAlbum(), 0, 0)))
            albums.add(new Album(song.getAlbum(), 0, 0));
        */
		System.out.println("added to library");
		
		filteredData = new FilteredList<>(fullLibrary, p -> true);

		
	}
	
	private void updateAlbums() {
		// put the albums in the album table based on the song table
		Set<Album> albumset = new HashSet<>();
		for(SongEntry song : library) 
		{
			Album album = new Album(song.getAlbum(), 0, 0);
			albumset.add(album);
		}
		albums = FXCollections.observableArrayList(albumset);
		
	}

	private void updateArtists() {
		// put the artists in the artist table based on the song table
		Set<Artist> artistset = new HashSet<>();
		for(SongEntry song : library) 
		{
			Artist artist = new Artist(song.getArtist(), 0, 0);
			artistset.add(artist);
		}
		artists = FXCollections.observableArrayList(artistset);
		
	}

	public void filterByArtist(Artist filter) 
	{
		
		filteredData.setPredicate(p -> {
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
		library.setAll(filteredData);
	}
		
	public ObservableList<Integer> getTracks() {
	    return tracks;
	}
	
	public void clearTracks() {
	    tracks.clear();
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
