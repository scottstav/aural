package model;

import java.io.File;
import java.io.IOException;

import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;

public class PlaylistTest
{
    public static void main(String[] args)
    {
        String path = "/home/daniel/Music/Radiohead - Kid A/01 Everything In Its Right Place.mp3";
        Playlist pl = new Playlist("New playlist", 0);
        try
        {
            pl.setHead(new PlaylistNode(new SongEntry(new MP3File(new File(path))), null));
        }
        catch (NoMPEGFramesException | ID3v2FormatException | CorruptHeaderException
                | IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        try
        {
            path = "/home/daniel/Music/Radiohead - Kid A/02 Kid A.mp3";
            pl.addToPlaylist(new PlaylistNode(new SongEntry(new MP3File(new File(path))), null));
            
            path = "/home/daniel/Music/Radiohead - Kid A/03 The National Anthem.mp3";
            pl.addToPlaylist(new PlaylistNode(new SongEntry(new MP3File(new File(path))), null));
            
            path = "/home/daniel/Music/Radiohead - Kid A/04 How To Disappear Completely.mp3";
            pl.addToPlaylist(new PlaylistNode(new SongEntry(new MP3File(new File(path))), null));
            
            path = "/home/daniel/Music/Radiohead - Kid A/05 Treefingers.mp3";
            pl.addToPlaylist(new PlaylistNode(new SongEntry(new MP3File(new File(path))), null));
        }
        catch (NoMPEGFramesException | ID3v2FormatException | CorruptHeaderException
                | IOException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        
        System.out.println(pl);
        
        try
        {
            path = "/home/daniel/Music/Radiohead - Kid A/06 Optimistic.mp3";
            pl.getHead().insertNode(new PlaylistNode(new SongEntry(new MP3File(new File(path))), null),
                                    pl.getHead().getEntry().getId());
        }
        catch (NoMPEGFramesException | ID3v2FormatException | CorruptHeaderException
                | IOException e)
        {
            e.printStackTrace();
            System.exit(3);
        }
        
        System.out.println(pl);
    }
}
