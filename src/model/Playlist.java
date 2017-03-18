package model;

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
		String returnString = getName() + ":\n\n";
		PlaylistNode node = head;
		
		while(node != null)
		{
		    returnString += node.getEntry().getArtist() + " " +
		                    node.getEntry().getTitle() + " " +
		                    node.getEntry().getAlbum() +  "\n";
		    node = node.getNext();
		}
		
		return returnString;
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
	
	public void setHead(PlaylistNode head) {
	    this.head = head;
	}
	
	public void addToPlaylist(PlaylistNode node) {
	    head.addNode(node);
	}

}
