package view;

import controller.AlbumTableController;
import controller.AuthorTableController;
import controller.Controller;
import controller.LibrarySelectorController;
import controller.MenuController;
import controller.SongTableController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * MainView that houses all other views in the application. Application is a
 * Single Document Interface.
 * 
 * @author Daniel Garcia
 *
 */
public class MainView extends BorderPane {
	
	// Container for the MenuBar and PlayBack container
	private VBox topBox;


	private MenuController controller;

	public MainView(MenuController controller) {

		this.controller = controller;

		// playback and menu (top)
		topBox = new VBox();
		topBox.getChildren().add(new MenuView());
		topBox.getChildren().add(new PlaybackView());
		setTop(topBox);

		// library (center)
		setCenter(new LibraryView());

		// library selector (left menu)
		setLeft(new LibrarySelector());	
		
	}

}
