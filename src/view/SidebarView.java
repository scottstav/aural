package view;

import controller.ScreenReader;
import controller.SidebarController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	private ListView<Playlist> playlists;
	private Button radio;
	private Label playlistsLabel;
	private HBox seperator1;
	private HBox seperator2;



	private SidebarController controller;

	public SidebarView(SidebarController sidebarController) {
		this.controller = sidebarController;
		seperator1 = new HBox();
		seperator1.setPrefHeight(50);
		seperator2 = new HBox();
		seperator2.setPrefHeight(50);
		setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 2;"
                + "-fx-border-color: lightgray;");

		
		createAndPlaceElements();
		//playlists.getItems().addAll(MasterController.getInstance().getGateway().getPlaylists());
	}

	/**
	 * Initializes any objects that are needed and places them in the
	 * appropriate container
	 */
	private void createAndPlaceElements()
	{
		
		// Set value so user knows what this ComboBox is for
		playlists = new ListView<Playlist>();
		playlists.setItems(controller.getPlaylists());
		playlists.setAccessibleRole(AccessibleRole.LIST_VIEW);
		playlists.setAccessibleText("Playlists");
		playlists.setAccessibleHelp("A list of playlists");
		playlists.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			MasterController.getInstance().updateView(ViewType.PLAYLIST_VIEW, newValue);
			if(MasterController.getInstance().isScreenReaderEnabled()) 
			{
				ScreenReader sr = new ScreenReader(newValue, "Playlist");
				sr.readInfo();
			}
		});
	

		personalLibrary = new Button("PersonaLibrary");
		personalLibrary.setAccessibleRole(AccessibleRole.BUTTON);
		personalLibrary.setAccessibleHelp("Display a view of all songs contained within the personal library");
		personalLibrary.setAccessibleText("Personal Library");
		personalLibrary.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MasterController.getInstance().updateView(ViewType.LIBRARY_VIEW, null);
		    	playlists.getSelectionModel().clearSelection();
		    }
		});
		personalLibrary.setAlignment(Pos.TOP_CENTER);
		

		playlistsLabel = new Label("Playlists");
		playlistsLabel.setAccessibleRole(AccessibleRole.TEXT);
		playlistsLabel.setAccessibleHelp("Label displaying playlists");
		playlistsLabel.setAccessibleText("Playlists");
		playlistsLabel.setPadding(new Insets(0, 0, 0, 10));
		playlistsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		
		playlistsLabel.setAlignment(Pos.CENTER);
		
		radio = new Button("Radio");
		radio.setAccessibleRole(AccessibleRole.BUTTON);
		radio.setAccessibleHelp("Displays all radio stations");
		radio.setAccessibleText("Radio");
		radio.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MasterController.getInstance().updateView(ViewType.RADIO_VIEW, null);
		    }
		});
		
		radio.setAlignment(Pos.BOTTOM_CENTER);


		getChildren().addAll(personalLibrary, seperator1, playlistsLabel, playlists, seperator2, radio);
	}
	
	
}
