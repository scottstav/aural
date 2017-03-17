package view;

import controller.SidebarController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import launch.MasterController;
import model.Playlist;

/**
 * VBox containing all the necessary elements for the left panel on the main
 * view
 * 
 * @author Daniel Garcia
 *
 */
public class SidebarView extends VBox {
	private Button personalLibrary;
	private ComboBox<Playlist> playlists;
	private Button radio;

	private SidebarController controller;

	public SidebarView(SidebarController sidebarController) {
		this.controller = sidebarController;
		createAndPlaceElements();
	}

	/**
	 * Initializes any objects that are needed and places them in the
	 * appropriate container
	 */
	private void createAndPlaceElements() {
		personalLibrary = new Button("PersonaLibrary");
		personalLibrary.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MasterController.getInstance().updateView(ViewType.LIBRARY_VIEW, null);
		    }
		});

		playlists = new ComboBox<Playlist>();
		
		// Set value so user knows what this ComboBox is for
		playlists.setValue(new Playlist());

		radio = new Button("Radio");
		radio.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MasterController.getInstance().updateView(ViewType.RADIO_VIEW, null);
		    }
		});

		getChildren().addAll(personalLibrary, playlists, radio);
	}
}
