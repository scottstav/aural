package model;

/**
 * Possible model for the SongEntry gathered from the database
 * 
 * @author Daniel Garcia
 *
 */
public class SongEntry {
	private int id;
	private int trackId;
	private int length;

	private String location;
	private String title;
	private String artist;
	private String album;

	public SongEntry() {
		setId(0);
		setLocation("");
		setTrackId(0);
		setTitle("");
		setartist("");
		setAlbum("");
		setLength(0);
	}

	public SongEntry(int id, String location, int trackId, String title, String artist, String album, int length) {
		setId(id);
		setLocation(location);
		setTrackId(trackId);
		setTitle(title);
		setartist(artist);
		setAlbum(album);
		setLength(length);
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @return the artist
	 */
	public String getartist() {
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setartist(String artist) {
		this.artist = artist;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the length
	 */
	public String getLengthFormatted() {
		return "" + length / 60 + ":" + length % 60;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
}
