package model;

public class PlaylistNode
{
    private SongEntry entry;
    private PlaylistNode next;
    public PlaylistNode(SongEntry entry, PlaylistNode next)
    {
        this.entry = entry;
        this.next = next;
    }
    
    public SongEntry getEntry()
    {
        return entry;
    }
    
    public PlaylistNode getNext()
    {
        return next;
    }
    
    private void setNext(PlaylistNode next)
    {
        this.next = next;
    }
    
    public void addNode(PlaylistNode next)
    {
        PlaylistNode iterator = this;
        while(iterator.getNext() != null)
            iterator = iterator.getNext();
        iterator.setNext(next);
    }
    
    public PlaylistNode searchList(int id, String title, String artist, String album) throws PlayListNodeNotFoundException
    {
        if(!compareFields(id, title, artist, album) && getNext() == null)
            throw new PlayListNodeNotFoundException(artist+" "+title+" "+album+" does not exist");
        if(compareFields(id, title, artist, album))
            return this;
        try {
            getNext().searchList(id, title, artist, album);
        }
        catch(PlayListNodeNotFoundException e)
        {
            throw new PlayListNodeNotFoundException(artist+" "+title+" "+album+" does not exist");
        }
        return null;
    }
    
    public PlaylistNode searchList(PlaylistNode node) throws PlayListNodeNotFoundException
    {
        if(!this.equals(node) && getNext() == null)
            throw new PlayListNodeNotFoundException("Node does not exist");
        if(this.equals(node))
            return this;
        try {
            getNext().searchList(node);
        }
        catch(PlayListNodeNotFoundException e)
        {
            throw new PlayListNodeNotFoundException("Node does not exist");
        }
        return null;
    }
    
    private boolean compareFields(int id, String title, String artist, String album)
    {
        if(getEntry().getId() == id)
            return true;
        if(title != null && artist != null && album != null)
            if(getEntry().getTitle().equals(title) &&
               getEntry().getArtist().equals(artist) &&
               getEntry().getAlbum().equals(album))
                return true;
        return false;
    }
    
    public boolean equals(Object obj)
    {
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        PlaylistNode node = (PlaylistNode) obj;
        return node.getEntry().getAlbum().equals(getEntry().getAlbum()) &&
               node.getEntry().getTitle().equals(getEntry().getTitle()) &&
               node.getEntry().getArtist().equals(getEntry().getArtist()) &&
               node.getEntry().getId() == getEntry().getId();
    }
    
    public boolean insertNode(PlaylistNode insert, int insertionId)
    {
        try {
            PlaylistNode node = searchList(insertionId, null, null, null);
            PlaylistNode tmp = node.getNext();
            node.setNext(insert);
            insert.setNext(tmp);
        } catch (PlayListNodeNotFoundException e) {
            
        }
        return true;
    }
}
