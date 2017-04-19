package view;

import controller.MenuController;
import controller.ScreenReader;
import javafx.concurrent.Task;
import javafx.scene.AccessibleRole;
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
	private MenuItem helpMenuItem;

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
	
	private Menu screenReader;
	private MenuItem readMenuItemsItem;
	private MenuItem toggleMenuItem;
	
	private MenuController controller;
	
	FileChooser fileChooser;
	
	public MenuView(MenuController c) {

		
	    this.setAccessibleRole(AccessibleRole.MENU_BAR);
	    this.setAccessibleHelp("A Menu Bar containing all basic file operations");
	    
	    this.setAccessibleText("Menu Bar");
		fileChooser  = new FileChooser();
		
		this.controller = c;
		
		
		
		fileMenu = new Menu("File");
		
		
		quitItem = new MenuItem("Quit");
		quitItem.setAccelerator(KeyCombination.keyCombination("CTRL+Q"));
		importItem = new MenuItem("Import Music");
		importItem.setAccelerator(KeyCombination.keyCombination("CTRL+I"));
		createPlaylistItem = new MenuItem("Create Playlist");
		createPlaylistItem.setAccelerator(KeyCombination.keyCombination("CTRL+P"));
		helpMenuItem = new MenuItem("Help");
		fileMenu.getItems().addAll(helpMenuItem, importItem, createPlaylistItem, quitItem);

		editMenu = new Menu("Edit");
		
		deleteItem = new MenuItem("Delete Selected Item(s)");
		deleteItem.setAccelerator(KeyCombination.keyCombination("CTRL+D"));
		
		ComboBox<Playlist> playlists = new ComboBox<Playlist>(MasterController.getInstance().getSidebarController().getPlaylists());
        playlists.setPromptText("add to playlist...");
        
      
		addToPlaylistMenuItem = new CustomMenuItem(playlists);
		addToPlaylistMenuItem.setAccelerator(KeyCombination.keyCombination("CTRL+A"));
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
		
		screenReader = new Menu("Screen Reader");
		readMenuItemsItem = new MenuItem("Read Menu Items");
		toggleMenuItem = new MenuItem("Toggle Screen Reader");
		readMenuItemsItem.setAccelerator(KeyCombination.keyCombination("CTRL+L"));
		screenReader.getItems().addAll(readMenuItemsItem, toggleMenuItem);
		
		this.getMenus().addAll(fileMenu, editMenu, playbackMenu, scriptsMenu, screenReader);
		registerControllers();
	}
	
	private void registerControllers()
	{
	    for(Menu m : getMenus())
	        for(MenuItem mi : m.getItems())
	            mi.setOnAction(controller);
	}
	
	public void readMenuItems()
	{
	    Task<Integer> task = new Task<Integer>() {
	        @Override protected Integer call() throws Exception {
	            for(Menu m : getMenus())
	            {
	                for(MenuItem mi : m.getItems())
	                {
	                    if(mi.getAccelerator() == null)
	                        continue;
	                    ScreenReader sr = new ScreenReader(mi, "MenuItem");
	                    sr.readInfo();
	                }
	            }
	            return 0;
	        }
	    };
	    
	    Thread th = new Thread(task);
	    th.setDaemon(true);
	    th.start();
	}
}
