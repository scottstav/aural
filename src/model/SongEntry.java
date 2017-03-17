package model;


import java.io.File;

import helliker.id3.ID3v1Tag;
import helliker.id3.ID3v2Tag;
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

	
	public SongEntry()
	{
		setId(0);
		setLocation("");
		setTrackId(0);
		setTitle("");
		setArtist("");
		setAlbum("");
		setLength(0);
	}

	public SongEntry(MP3File song)
	{
		this();
		
		setId(0);						// database?
		
		setLength(song.getPlayingTime());
		setLocation(song.getPath());
		// check which type it is
		try {
	        if(song.id3v2Exists())
	        {
	        	// set member variables
	            ID3v2Tag tag = new ID3v2Tag(new File(getLocation()), 0);
	            System.out.print("Showing artist name: ");
	            System.out.println(tag.getFrameDataString("TPE1"));
	            setArtist(tag.getFrameDataString("TPE1"));
	            System.out.print("Showing Album title: ");
	            System.out.println(tag.getFrameDataString("TALB"));
	            setAlbum(tag.getFrameDataString("TALB"));
	            System.out.print("Showing song title: ");
	            System.out.println(tag.getFrameDataString("TIT2"));
	            setTitle(tag.getFrameDataString("TIT2"));
	            System.out.print("Showing genre: ");
	            System.out.println(tag.getFrameDataString("TCON"));
	            System.out.print("Showing year: ");
	            System.out.println(tag.getFrameDataString("TYER"));
	            String trackString = tag.getFrameDataString("TRCK");
	            trackString = trackString.substring(0, trackString.indexOf('/'));
	            setTrackId(Integer.parseInt(trackString));
                System.out.print("Showing trackId: ");
                //System.out.println(tag.getFrameDataString("TRCK"));
                //System.out.println(trackString);
                System.out.println(Integer.parseInt(trackString));
                System.out.println("Again: " + trackId);
	        }
	        else if(song.id3v1Exists())
	        {
	        	// set member variables if V1
	        	ID3v1Tag tag = new ID3v1Tag(new File(getLocation()));
	            System.out.print("Showing artist name: ");
	            System.out.println(tag.getArtist());
	            setArtist(tag.getArtist());
	            System.out.print("Showing Album title: ");
	            System.out.println(tag.getAlbum());
	            setAlbum(tag.getAlbum());
	            System.out.print("Showing song title: ");
	            System.out.println(tag.getTitle());
	            setTitle(tag.getTitle());
	            System.out.print("Showing genre: ");
	            System.out.println(tag.getGenre());
	            System.out.print("Showing year: ");
	            System.out.println(tag.getYear());
	        }
	    }
	    catch (Exception e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

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

	public String getLength() {
		return String.format("%d:%02d", length/60, length%60);
	}
	
	public long getLengthInt()
	{
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
