package view;

import controller.PlaybackController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import launch.MasterController;

public class PlaybackView extends VBox{
	
	private Button play;
	private Button next;
	private Button previous;
	private Label nowPlaying;

	
	private PlaybackController controller;
	
	public PlaybackView(PlaybackController playbackController){
		controller = playbackController;

		HBox buttons = new HBox();
		play = new Button("Play");
		play.setAccessibleRole(AccessibleRole.BUTTON);
		play.setAccessibleText("Play");
		play.setAccessibleHelp("Plays currently selected song");
		nowPlaying = new Label("");
		
		// bind the text of the button to the status of media in the play controller
		play.textProperty().bind(controller.getPlayOrPauseProperty());
		nowPlaying.textProperty().bind(controller.getNowPlayingProperty());
		nowPlaying.setAlignment(Pos.CENTER);
		nowPlaying.setPadding(new Insets(0, 0, 0, 10));
		nowPlaying.setPrefWidth(Double.MAX_VALUE);
		nowPlaying.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		next = new Button("Next");
		next.setAccessibleRole(AccessibleRole.BUTTON);
		next.setAccessibleText("Next");
		next.setAccessibleHelp("Plays the next song");
		previous = new Button("Prev.");
		previous.setAccessibleRole(AccessibleRole.BUTTON);
		previous.setAccessibleText("Previous");
		previous.setAccessibleHelp("Plays the previous song");

		setbuttons();
		buttons.getChildren().addAll(previous, play, next);
		buttons.setSpacing(10);
		buttons.setPadding(new Insets(0, 0, 0, 10));
		buttons.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(nowPlaying, buttons);

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
