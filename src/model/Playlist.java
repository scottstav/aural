package model;

import java.util.ArrayList;

/**
 * Playlist is implemented as a linked list
 * @author Daniel Garcia
 *
 */
public class Playlist {
    
    private PlaylistNode head = null;
    private String name;
    private int id;
	
	public Playlist(String name) {
	    if(name != null)
	        this.name = name;
	    else
	        this.name = "";
	    
	}
	
	public Playlist(String name, PlaylistNode head) {
	    this.name = name;
        this.head = head;
    }
	
	public String getName()
	{
	    return name;
	}
	
	public String toString() {
		return name;
	}
	
	public PlaylistNode getHead()
	{
	    return head;
	}
	
	public PlaylistNode getNext()
	{
	    PlaylistNode currentNode = head;
	    head = head.getNext();
	    return currentNode;
	}
	
	/**
	 * this method is for the database
	 * 
	 * it returns a string representing all the songs in the playlist
	 * 
	 * '#' is the delimiter
	 * 
	 * string structure: <song_id1>#<song_id2#and so on....
	 * @return
	 */
	public String getSongsForDB(){
		return name;
		
	}
	
	public ArrayList<SongEntry> getSongs() {
		ArrayList<SongEntry> songs = new ArrayList<SongEntry>();
		PlaylistNode iter = head;
		while(iter != null) 
		{
			songs.add(iter.getEntry());
			iter = iter.getNext();
		}
		return songs;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setHead(PlaylistNode head) {
	    this.head = head;
	}
	
	public void addToPlaylist(PlaylistNode node) {
		if(this.head == null)
		{
			this.setHead(node);;
		} else 
		{
		    head.addNode(node);
		}
	}

}
