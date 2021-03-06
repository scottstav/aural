package controller;

import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import launch.MasterController;
import model.SongEntry;

public class PlaybackController
{
	private SongEntry selectedSong;
	private ArrayList<SongEntry> songList;
	private final StringProperty playOrPause;
	private final StringProperty nowPlaying;

	
	private MediaPlayer mediaPlayer;
	
	
	public PlaybackController()
	{
		playOrPause = new SimpleStringProperty("Play");
		nowPlaying = new SimpleStringProperty("");
		songList = new ArrayList<SongEntry>();
		songList.addAll(MasterController.getInstance().getLibraryController().getSongs());

	}
	
	public void update() 
	{
		songList.clear();
		songList.addAll(MasterController.getInstance().getLibraryController().getSongs());

	}
		
	public void playSelection()
	{
		playOrPause.set("Pause");
		playNewSong();
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
		if((songList.indexOf(selectedSong)) == songList.size() - 1)
			return;
		selectedSong = songList.get((songList.indexOf(selectedSong) + 1));
		playSelection();
	}
	
	public void previousSong() 
	{
		if((songList.indexOf(selectedSong) - 1) < 0)
			return;
		selectedSong = songList.get((songList.indexOf(selectedSong) - 1));
		playSelection();

	}
	
	public void shuffleSongs() 
	{
		System.out.println("shuffle requested");

	}
	
	public void repeatSong() 
	{
		System.out.println("repeat requested");

	}
	
	private void pause()
	{
		mediaPlayer.pause();
	}
	
	private void playNewSong() 
	{	
		Media hit = new Media(new File(selectedSong.getLocation()).toURI().toString());
		if(!(mediaPlayer == null)) 
		{
			mediaPlayer.stop();
		}
		this.nowPlaying.set(selectedSong.toString());
		mediaPlayer = new MediaPlayer(hit);
		
		// play the next song when this one ends
		mediaPlayer.setOnEndOfMedia(new Runnable() {

	        @Override
	        public void run() {
	            // play if you want
	            nextSong();
	        }
	    });
		mediaPlayer.play();
	}

	public void setSelected(SongEntry selected) {
		for(SongEntry entry : songList)
		{
			if(entry.equals(selected))
			{
				selectedSong = entry;
				break;
			}
		}
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
	
	public StringProperty getNowPlayingProperty()
	{
		return nowPlaying;
	}

}
