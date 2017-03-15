package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import helliker.id3.MP3File;
import javafx.beans.property.SimpleStringProperty;

/**
 * Possible model for the SongEntry gathered from the database
 * 
 * @author Daniel Garcia
 *
 */
public class SongEntry  {
	private int id;
	private int trackId;
	
	private long length;

	private SimpleStringProperty location = new SimpleStringProperty();
	private SimpleStringProperty title = new SimpleStringProperty();
	private SimpleStringProperty artist = new SimpleStringProperty();
	private SimpleStringProperty album = new SimpleStringProperty();

	
	public SongEntry() {
		setId(0);
		setLocation("");
		setTrackId(0);
		setTitle("this is a song");
		setArtist("");
		setAlbum("");
		setLength(0);
	}

	public SongEntry(MP3File song)
	{
		this();
		
		setId(0);						// database?
		setTrackId(0);					// database?
		
		setLength(song.getPlayingTime());
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the trackId
	 */
	public int getTrackId() {
		return trackId;
	}

	/**
	 * @param trackId
	 *            the trackId to set
	 */
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getLocation() {
		return location.get();
	}

	public void setLocation(String val) {
		this.location.set(val);
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String val) {
		this.title.set(val);;
	}

	public String getArtist() {
		return artist.get();
	}

	public void setArtist(String val) {
		this.artist.set(val);;
	}

	public String getAlbum() {
		return album.get();
	}

	public void setAlbum(String val) {
		this.album.set(val);
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String toString() 
	{
		return getTitle();
	}
}
