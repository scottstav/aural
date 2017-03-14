package view;

import controller.LibrarySelectorController;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import model.Playlist;

/**
 * VBox containing all the necessary elements for the left panel on the main
 * view
 * 
 * @author Daniel Garcia
 *
 */
public class LibrarySelector extends VBox {
	private Button personalLibray;
	private ComboBox<Playlist> playlists;
	private Button radio;

	private LibrarySelectorController controller;

	public LibrarySelector() {
		createAndPlaceElements();
	}

	/**
	 * Initializes any objects that are needed and places them in the
	 * appropriate container
	 */
	private void createAndPlaceElements() {
		personalLibray = new Button("PersonaLibrary");

		playlists = new ComboBox<Playlist>();
		
		// Set value so user knows what this ComboBox is for
		playlists.setValue(new Playlist());

		radio = new Button("Radio");

		getChildren().addAll(personalLibray, playlists, radio);
	}
}
