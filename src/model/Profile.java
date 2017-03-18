package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Playlist> playlists;
	/*
	 * ArrayList<File> scripts = new ArrayList<>; String country;
	 * 
	 * public void setStreet(String street) { this.street = street; }
	 * 
	 * public void setCountry(String country) { this.country = country; }
	 * 
	 * public String getStreet() { return this.street; }
	 * 
	 * public String getCountry() { return this.country; }
	 * 
	 * @Override public String toString() { return new
	 * StringBuffer(" Street : ") .append(this.street).append(" Country : ")
	 * .append(this.country).toString(); }
	 */
	
	public Profile()
	{
	    playlists = new ArrayList<Playlist>();
	}
	
	public void addPlaylist(Playlist pl)
	{
	    playlists.add(pl);
	}
}