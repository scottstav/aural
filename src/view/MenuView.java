package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuView extends MenuBar{
	private Menu fileMenu;
	private MenuItem quitItem;
	private MenuItem importItem;
	private MenuItem createPlaylistItem;

	private Menu editMenu;
	private MenuItem deleteItem;
	private MenuItem keymapItem;
	private MenuItem preferencesItem;

	private Menu playbackMenu;
	private MenuItem playItem;
	private MenuItem pauseItem;
	private MenuItem nextTrackItem;
	private MenuItem previousTrackItem;
	private MenuItem shuffleItem;
	private MenuItem repeatItem;

	private Menu scriptsMenu;
	private MenuItem importScriptsItem;
	private MenuItem executeScriptsItem;
	private MenuItem selectDefaultItem;
	
	public MenuView() {
		fileMenu = new Menu("File");
		quitItem = new MenuItem("Quit");
		importItem = new MenuItem("Import Music");
		createPlaylistItem = new MenuItem("Create PlayList");
		fileMenu.getItems().addAll(quitItem, importItem, createPlaylistItem);

		editMenu = new Menu("Edit");
		deleteItem = new MenuItem("Delete Selected Item(s)");
		keymapItem = new MenuItem("Keymap");
		preferencesItem = new MenuItem("Preferences");
		editMenu.getItems().addAll(deleteItem, keymapItem, preferencesItem);

		playbackMenu = new Menu("Playback");
		playItem = new MenuItem("Play");
		pauseItem = new MenuItem("Pause");
		nextTrackItem = new MenuItem("Next Track");
		previousTrackItem = new MenuItem("Previous Track");
		shuffleItem = new MenuItem("Shuffle");
		repeatItem = new MenuItem("Repeat");
		playbackMenu.getItems().addAll(playItem, pauseItem, nextTrackItem, previousTrackItem, shuffleItem, repeatItem);

		scriptsMenu = new Menu("Scripts");
		importScriptsItem = new MenuItem("Import Scripts");
		executeScriptsItem = new MenuItem("Execute Scripts");
		selectDefaultItem = new MenuItem("Select Default Scripts");
		scriptsMenu.getItems().addAll(importScriptsItem, executeScriptsItem, selectDefaultItem);

		this.getMenus().addAll(fileMenu, editMenu, playbackMenu, scriptsMenu);
	}
}
