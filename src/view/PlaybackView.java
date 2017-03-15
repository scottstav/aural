package view;

import controller.PlaybackController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PlaybackView extends HBox{
	
	private Button play;
	private Button next;
	private Button previous;
	
	public PlaybackView(PlaybackController playbackController){
		play = new Button("Play");
		next = new Button("Next");
		previous = new Button("Prev.");

		this.getChildren().addAll(previous, play, next);
		this.setSpacing(10);
		this.setPadding(new Insets(0, 0, 0, 10));
	}
}
