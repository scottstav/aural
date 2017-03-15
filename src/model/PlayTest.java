package model;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayTest extends Application
{
    
    public static void main(String[] args)
    {
         Application.launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception
    {
        Media audio = new Media(new File("/home/daniel/Music/The Beatles/Abbey Road/01 Come Together.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(audio);
        player.play();
    }
}
