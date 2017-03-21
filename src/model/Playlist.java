package model;

import java.util.ArrayList;

import launch.MasterController;

/**
 * Playlist is implemented as a linked list
 * @author Daniel Garcia
 *
 */
public class Playlist {
    
    private PlaylistNode head = null;
    private String name;
    private int id;
	
	public Playlist(String name, int id) {
	    if(name != null)
	        this.name = name;
	    else
	        this.name = "";
	    
	    setId(id);
	    
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
	public String getSongsForDB()
	{
		StringBuilder songs = new StringBuilder();
		for(SongEntry song : getSongs())
		{

			songs.append(song.getId());
			songs.append("#");
		}
		

		return songs.toString();
		
	}
	
	public void setSongsForDB(String playlist_as_string)
	{
	    playlist_as_string = playlist_as_string.trim();
		System.out.println("playlists as string: " + playlist_as_string + ";");

		String[] song_ids;
		if(playlist_as_string.isEmpty())
		    song_ids = new String[0];
		else
		    song_ids = playlist_as_string.split("#");

		for(String id : song_ids)
		{
			System.out.println("Id: " + id);
			
			if(head == null)
			{
				setHead(new PlaylistNode(MasterController.getInstance().getLibraryController().getSongById(Integer.parseInt(id)), null));
				continue;
			}
			
			addToPlaylist(new PlaylistNode(MasterController.getInstance().getLibraryController().getSongById(Integer.parseInt(id)), null));
			 
		}
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
		
		//update database
		MasterController.getInstance().getGateway().alterPlaylist(this);
	}

}
