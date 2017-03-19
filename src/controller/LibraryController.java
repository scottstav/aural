package controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import launch.MasterController;
import model.Album;
import model.Artist;
import model.Playlist;
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

		fullLibrary = new ArrayList<SongEntry>(MasterController.getInstance().getGateway().getSongEntrys());
		library = FXCollections.observableArrayList(fullLibrary);
		albumset = new HashSet<>();
		allAlbums = new ArrayList<Album>();
		updateArtists();
		updateAlbums();	
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
	
	public void filterByPlaylist(Playlist filter) 
	{
		library.setAll(filter.getSongs());	
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
		return artists;
	}
	
	public void clearArtists()
    {
        artists.clear();
    }
	
	public ObservableList<Album> getAlbums() {
		return albums;
	}
	
	public void clearAlbums()
    {
        albums.clear();
    }
	

}
