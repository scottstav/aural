package view;

import controller.SidebarController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import launch.MasterController;
import model.Playlist;
import model.SongEntry;

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
		//playlists.getItems().addAll(MasterController.getInstance().getGateway().getPlaylists());
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
		
			
		// Set value so user knows what this ComboBox is for
		playlists = new ComboBox<Playlist>();
		playlists.setPromptText("Select Playlist");
		playlists.setItems(controller.getPlaylists());

		radio = new Button("Radio");
		radio.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MasterController.getInstance().updateView(ViewType.RADIO_VIEW, null);
		    }
		});

		getChildren().addAll(personalLibrary, playlists, radio);
	}
}
