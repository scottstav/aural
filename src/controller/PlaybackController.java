package controller;

import java.io.File;
import java.util.LinkedList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import model.SongEntry;

public class PlaybackController
{
	private SongEntry selectedSong;
	private LinkedList<SongEntry> songList;
	private final StringProperty playOrPause;
	private MediaPlayer mediaPlayer;
	
	
	public PlaybackController()
	{
		playOrPause = new SimpleStringProperty("Play");
	}
		
	public void playSelection()
	{
		playOrPause.set("Pause");
		play();
	}
	
	public void pauseSong() 
	{
		playOrPause.set("Play");
		pause();
	}
	
	public void playSong() 
	{
		playOrPause.set("Pause");
		if(mediaPlayer == null)
			playSelection();
		else 
			mediaPlayer.play();
	}
	

	public void nextSong()
	{
		
	}
	
	public void previousSong() 
	{
		
	}
	
	private void pause()
	{
		mediaPlayer.pause();
	}
	
	private void play() 
	{
		
	
		Media hit = new Media(new File(selectedSong.getLocation()).toURI().toString());
		if(!(mediaPlayer == null)) 
		{
			mediaPlayer.stop();
		}
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}

	public void setSelected(SongEntry selected) {
		selectedSong = selected;
	}
	
	public SongEntry getSelected()
	{
		return selectedSong;
	}
	
	// updates the view
	public StringProperty getPlayOrPauseProperty()
	{
		return playOrPause;
	}

}
