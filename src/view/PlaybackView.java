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
	
	public PlaybackView(PlaybackController playbackController){
		play = new Button("Play");
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
	            MasterController.getInstance().getPlaybackController().playSelection();
	            if(play.getText().equals("Play"))
	            	play.setText("Pause");
	            else
	            	play.setText("Play");

		    }
		});
		
		next.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {

	            MasterController.getInstance().getPlaybackController().playSelection();

		    }
		});
		previous.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {

	            MasterController.getInstance().getPlaybackController().playSelection();

		    }
		});
	}
}
