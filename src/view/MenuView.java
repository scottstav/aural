package view;

import controller.MenuController;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
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
		quitItem.setAccelerator(KeyCombination.keyCombination("CTRL+Q"));
		importItem = new MenuItem("Import Music");
		importItem.setAccelerator(KeyCombination.keyCombination("CTRL+I"));
		createPlaylistItem = new MenuItem("Create Playlist");
		createPlaylistItem.setAccelerator(KeyCombination.keyCombination("CTRL+P"));
		fileMenu.getItems().addAll(quitItem, importItem, createPlaylistItem);

		editMenu = new Menu("Edit");
		
		deleteItem = new MenuItem("Delete Selected Item(s)");
		deleteItem.setAccelerator(KeyCombination.keyCombination("CTRL+D"));
		
		ComboBox<Playlist> playlists = new ComboBox<Playlist>(MasterController.getInstance().getSidebarController().getPlaylists());
        playlists.setPromptText("add to playlist...");
      
		addToPlaylistMenuItem = new CustomMenuItem(playlists);
		addToPlaylistMenuItem.setHideOnClick(false);
		playlists.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
			MasterController.getInstance().getSidebarController().getPlaylistById(newValue.getId()).addToPlaylist(new PlaylistNode(MasterController.getInstance().getSelected(), null));
	        
	    });
		
		keymapItem = new MenuItem("Keymap");
		keymapItem.setAccelerator(KeyCombination.keyCombination("CTRL+K"));
		preferencesItem = new MenuItem("Preferences");
		preferencesItem.setAccelerator(KeyCombination.keyCombination("CTRL+U"));
		
		editMenu.getItems().addAll(deleteItem, keymapItem, preferencesItem, addToPlaylistMenuItem);

		playbackMenu = new Menu("Playback");
		
		playOrPauseItem = new MenuItem("Play");
		playOrPauseItem.textProperty().bind(MasterController.getInstance().getPlaybackController().getPlayOrPauseProperty());
		playOrPauseItem.setAccelerator(KeyCombination.keyCombination("SPACE"));
		nextTrackItem = new MenuItem("Next");
		nextTrackItem.setAccelerator(KeyCombination.keyCombination("CTRL+RIGHT"));
		previousTrackItem = new MenuItem("Previous");
		previousTrackItem.setAccelerator(KeyCombination.keyCombination("CTRL+LEFT"));
		shuffleItem = new MenuItem("Shuffle");
		shuffleItem.setAccelerator(KeyCombination.keyCombination("S"));
		repeatItem = new MenuItem("Repeat");
		repeatItem.setAccelerator(KeyCombination.keyCombination("R"));
		playbackMenu.getItems().addAll(playOrPauseItem, nextTrackItem, previousTrackItem, shuffleItem, repeatItem);

		scriptsMenu = new Menu("Scripts");
		
		importScriptsItem = new MenuItem("Import Scripts");
		importScriptsItem.setAccelerator(KeyCombination.keyCombination("CTRL+S"));
		executeScriptsItem = new MenuItem("Execute Scripts");
		executeScriptsItem.setAccelerator(KeyCombination.keyCombination("CTRL+E"));
		selectDefaultItem = new MenuItem("Select Default Scripts");
		selectDefaultItem.setAccelerator(KeyCombination.keyCombination("CTRL+J"));
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
