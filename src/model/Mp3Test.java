package model;

import java.io.File;

import helliker.id3.MP3File;

public class Mp3Test
{
    public static void main(String[] args)
    {
        try
        {
            MP3File mp3 = new MP3File(new File("/home/daniel/Music/The Beatles/Abbey Road/01 Come Together.mp3"));
            System.out.println(mp3.getFileName().substring(0,  mp3.getFileName().length()-4));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
