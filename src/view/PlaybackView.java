package view;

import controller.PlaybackController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import launch.MasterController;

public class PlaybackView extends HBox{
	
	private Button play;
	private Button next;
	private Button previous;
	
	private PlaybackController controller;
	
	public PlaybackView(PlaybackController playbackController){
		controller = playbackController;

		play = new Button("Play");
		
		// bind the text of the button to the status of media in the play controller
		play.textProperty().bind(controller.getPlayOrPauseProperty());

		next = new Button("Next");
		previous = new Button("Prev.");

		setbuttons();
		
		this.getChildren().addAll(previous, play, next);
		this.setSpacing(10);
		this.setPadding(new Insets(0, 0, 0, 10));
	}

	private void setbuttons() {
		play.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	// TO DO: alternate between play / pause actions
	            if(play.getText().equals("Play"))
	            {
	            	MasterController.getInstance().getPlaybackController().playSong();
	            	//play.setText("Pause");
	            }
	            else
	            {
	            	MasterController.getInstance().getPlaybackController().pauseSong();
	            	//play.setText("Play");
	            }

		    }
		});
		
		next.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {

	            MasterController.getInstance().getPlaybackController().nextSong();

		    }
		});
		previous.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {

	            MasterController.getInstance().getPlaybackController().previousSong();

		    }
		});
	}
}
