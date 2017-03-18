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
}
