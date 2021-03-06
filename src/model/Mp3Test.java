package model;

import java.io.File;

import helliker.id3.ID3v2Tag;
import helliker.id3.MP3File;

public class Mp3Test
{
    public static void main(String[] args)
    {
        try
        {
            String filePath = "/home/daniel/Music/Black Sabbath - Greatest Hits 2009 [MP3 @ 320] (oan)/01 - Black Sabbath - Paranoid.mp3";
            MP3File mp3 = new MP3File(new File(filePath));
            System.out.println(mp3);
            
            // check which type it is
            if(mp3.id3v2Exists())
            {
                ID3v2Tag tag = new ID3v2Tag(new File(filePath), 0);
                System.out.print("Showing artist name: ");
                System.out.println(tag.getFrameDataString("TPE1"));
                System.out.print("Showing Album title: ");
                System.out.println(tag.getFrameDataString("TALB"));
                System.out.print("Showing song title: ");
                System.out.println(tag.getFrameDataString("TIT2"));
                System.out.print("Showing genre: ");
                System.out.println(tag.getFrameDataString("TCON"));
                System.out.print("Showing year: ");
                System.out.println(tag.getFrameDataString("TYER"));
                System.out.print("Showing track number: ");
                System.out.println(tag.getFrameDataString("TRCK"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
