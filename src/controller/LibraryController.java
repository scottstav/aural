package controller;
import java.util.HashSet;
import java.util.Set;
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

	FilteredList<SongEntry> filteredSongs;
	FilteredList<Album> filteredAlbums;

	
	public LibraryController() {
		this.initialize();

	}

	public void initialize() 
	{
		// TODO Auto-generated method stub

		fullLibrary = FXCollections.observableArrayList( MasterController.getInstance().getGateway().getSongEntrys());
		library = fullLibrary;
		
		/*
=======
		MP3File file = new MP3File(data);
		SongEntry song = new SongEntry(file);
		System.out.println("created MP3");
		fullLibrary.add(song);
		library.add(song);
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
		
		filteredSongs = new FilteredList<>(fullLibrary, p -> true);
		
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

		filteredSongs.setPredicate(p -> {
            // If filter text is empty, display all songs.
            if (filter == null) {
                return true;
            }

            if (filter.getName().equals(p.getArtist())) {

                return true; // Filter matches artist.
            }

            return false; // Does not match.
        });
		
		library.setAll(filteredSongs);
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
