package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.input.KeyCombination;

public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Playlist> playlists;
	private ArrayList<KeyMap> keyMaps;
	private Preferences preferences;
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
	    keyMaps = new ArrayList<KeyMap>();
	    initializeDefaultMappings();
	    preferences = new Preferences();
	}
	
	private void initializeDefaultMappings()
	{
	    keyMaps.add(new KeyMap("Quit", KeyCombination.keyCombination("CTRL+Q")));
	    keyMaps.add(new KeyMap("Import Music", KeyCombination.keyCombination("CTRL+I")));
	    keyMaps.add(new KeyMap("Create Playlist", KeyCombination.keyCombination("CTRL+P")));
	    keyMaps.add(new KeyMap("Delete Selected Item(s)", KeyCombination.keyCombination("CTRL+D")));
	    keyMaps.add(new KeyMap("Keymap", KeyCombination.keyCombination("CTRL+K")));
	    keyMaps.add(new KeyMap("Preferences", KeyCombination.keyCombination("CTRL+U")));
	    keyMaps.add(new KeyMap("Play", KeyCombination.keyCombination("SPACE")));
	    keyMaps.add(new KeyMap("Next", KeyCombination.keyCombination("CTRL+RIGHT")));
	    keyMaps.add(new KeyMap("Previous", KeyCombination.keyCombination("CTRL+LEFT")));
	    keyMaps.add(new KeyMap("Shuffle", KeyCombination.keyCombination("S")));
	    keyMaps.add(new KeyMap("Repeat", KeyCombination.keyCombination("R")));
	    keyMaps.add(new KeyMap("Import Scripts", KeyCombination.keyCombination("CTRL+S")));
	    keyMaps.add(new KeyMap("Execute Scripts", KeyCombination.keyCombination("CTRL+E")));
	    keyMaps.add(new KeyMap("Select Default Scripts", KeyCombination.keyCombination("CTRL+J")));
	}
	
	public Preferences getPreferences()
    {
        return preferences;
    }

    public void setPreferences(Preferences preferences)
    {
        this.preferences = preferences;
    }

    public void addKeyMap(KeyMap k)
	{
	    if(keyMaps.contains(k))
	        keyMaps.remove(k);
	    keyMaps.add(k);
	}
    
    public ArrayList<KeyMap> getKeyMaps()
    {
        return keyMaps;
    }
	
	public void addPlaylist(Playlist pl)
	{
	    playlists.add(pl);
	}
}