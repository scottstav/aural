package controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	private static ArrayList<SongEntry> fullLibrary;
	private static ArrayList<Album> allAlbums;

	private static Set<Album>  albumset;
	
	public LibraryController() {
		this.initialize();

	}

	public void initialize() 
	{
		// TODO Auto-generated method stub

		fullLibrary = new ArrayList<SongEntry>(MasterController.getInstance().getGateway().getSongEntrys());
		library = FXCollections.observableArrayList(fullLibrary);
		albumset = new HashSet<>();
		allAlbums = new ArrayList<Album>();

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
		
		
	}
	
	private void updateAlbums() {
		albumset = new HashSet<>();
		// put the albums in the album table based on the song table
		for(SongEntry song : library) 
		{
			Album album = new Album(song.getAlbum(), song.getArtist(), 0);
			albumset.add(album);
			allAlbums.add(album);
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
		System.out.println("filtering based on albums with artist: " + filter.getName() );

		library.setAll(fullLibrary);
		albums.setAll(allAlbums);
		Set<Album> hs = new HashSet<>();
		hs.addAll(albums);
		albums.clear();
		albums.addAll(hs);
		library.removeIf(p -> !(p.getArtist().equals(filter.getName())));
		albums.removeIf(p -> !(p.getArtist().equals(filter.getName())));
		

	}
	
	public void filterByAlbum(Album filter) 
	{
		library.setAll(fullLibrary);
		library.removeIf(p -> !(p.getAlbum().equals(filter.getName())));	
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
