package view;

import controller.MenuController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import launch.MasterController;
import model.Playlist;
import model.PlaylistNode;

public class MenuView extends MenuBar{
	private Menu fileMenu;
	private MenuItem quitItem;
	private MenuItem importItem;
	private MenuItem createPlaylistItem;

	private Menu editMenu;
	private MenuItem deleteItem;
	private MenuItem keymapItem;
	private MenuItem preferencesItem;
	private CustomMenuItem addToPlaylistMenuItem;

	private Menu playbackMenu;
	private MenuItem playOrPauseItem;
	private MenuItem nextTrackItem;
	private MenuItem previousTrackItem;
	private MenuItem shuffleItem;
	private MenuItem repeatItem;

	private Menu scriptsMenu;
	private MenuItem importScriptsItem;
	private MenuItem executeScriptsItem;
	private MenuItem selectDefaultItem;
	
	private MenuController controller;
	
	FileChooser fileChooser;
	
	public MenuView(MenuController c) {

		
		fileChooser  = new FileChooser();
		
		this.controller = c;
		
		fileMenu = new Menu("File");
		
		quitItem = new MenuItem("Quit");
		importItem = new MenuItem("Import Music");
		createPlaylistItem = new MenuItem("Create Playlist");
		fileMenu.getItems().addAll(quitItem, importItem, createPlaylistItem);

		editMenu = new Menu("Edit");
		
		deleteItem = new MenuItem("Delete Selected Item(s)");
		
		ComboBox<Playlist> playlists = new ComboBox<Playlist>(MasterController.getInstance().getSidebarController().getPlaylists());
        playlists.setPromptText("add to playlist...");
      
		addToPlaylistMenuItem = new CustomMenuItem(playlists);
		addToPlaylistMenuItem.setHideOnClick(false);
		playlists.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			MasterController.getInstance().getSidebarController().getPlaylistById(newValue.getId()).addToPlaylist(new PlaylistNode(MasterController.getInstance().getSelected(), null));
	        
	    });
		
		keymapItem = new MenuItem("Keymap");
		preferencesItem = new MenuItem("Preferences");
		
		editMenu.getItems().addAll(deleteItem, keymapItem, preferencesItem, addToPlaylistMenuItem);

		playbackMenu = new Menu("Playback");
		
		playOrPauseItem = new MenuItem("Play");
		playOrPauseItem.textProperty().bind(MasterController.getInstance().getPlaybackController().getPlayOrPauseProperty());
		nextTrackItem = new MenuItem("Next");
		previousTrackItem = new MenuItem("Previous");
		shuffleItem = new MenuItem("Shuffle");
		repeatItem = new MenuItem("Repeat");
		playbackMenu.getItems().addAll(playOrPauseItem, nextTrackItem, previousTrackItem, shuffleItem, repeatItem);

		scriptsMenu = new Menu("Scripts");
		
		importScriptsItem = new MenuItem("Import Scripts");
		executeScriptsItem = new MenuItem("Execute Scripts");
		selectDefaultItem = new MenuItem("Select Default Scripts");
		scriptsMenu.getItems().addAll(importScriptsItem, executeScriptsItem, selectDefaultItem);

		this.getMenus().addAll(fileMenu, editMenu, playbackMenu, scriptsMenu);
		registerControllers();
	}
	
	private void registerControllers()
	{
	    for(Menu m : getMenus())
	        for(MenuItem mi : m.getItems())
	            mi.setOnAction(controller);
	}
}
