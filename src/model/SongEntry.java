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
	            setArtist(tag.getFrameDataString("TPE1"));
	            setAlbum(tag.getFrameDataString("TALB"));
	            setTitle(tag.getFrameDataString("TIT2"));
	            String trackString = tag.getFrameDataString("TRCK");
	            try {
	                trackString = trackString.substring(0, trackString.indexOf('/'));
	            }
	            catch (Exception e) {
	                
	            }
	            setTrackId(Integer.parseInt(trackString));
                //System.out.println(tag.getFrameDataString("TRCK"));
                //System.out.println(trackString);
	        }
	        else if(song.id3v1Exists())
	        {
	        	// set member variables if V1
	        	ID3v1Tag tag = new ID3v1Tag(new File(getLocation()));   
	            setArtist(tag.getArtist());         
	            setAlbum(tag.getAlbum());     
	            setTitle(tag.getTitle());
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
