package controller;

import java.io.File;
import java.util.LinkedList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.SongEntry;

public class PlaybackController
{
	private SongEntry selectedSong;
	private LinkedList<SongEntry> songList;
	
	private MediaPlayer mediaPlayer;
	
	
	public PlaybackController()
	{
		
	}
		
	public void playSelection()
	{
	
		play();
	}
	

	public void nextSong()
	{
		
	}
	
	public void previousSong() 
	{
		
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

}
