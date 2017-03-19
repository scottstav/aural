package view;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.MenuController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import launch.MasterController;

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
	
	private MenuController controller;
	
	FileChooser fileChooser;
	
	public MenuView(MenuController c) {
		
		Logger logger = LogManager.getLogger();

		
		fileChooser  = new FileChooser();
		
		this.controller = c;
		
		fileMenu = new Menu("File");
		quitItem = new MenuItem("Quit");
		quitItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		
		importItem = new MenuItem("Import Music");
		importItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	fileChooser.setTitle("Select music files");
		    	File selected_song = fileChooser.showOpenDialog(MasterController.getInstance().getPrimaryStage());
		    	MasterController.getInstance().updateView(ViewType.IMPORT_MUSIC, selected_song);
		    }
		});
		createPlaylistItem = new MenuItem("Create PlayList");
		createPlaylistItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	logger.info("show playlist popup");
		    	MasterController.getInstance().updateView(ViewType.CREATE_PLAYLIST, null);

		    }
		});
		
		fileMenu.getItems().addAll(quitItem, importItem, createPlaylistItem);

		editMenu = new Menu("Edit");
		
		deleteItem = new MenuItem("Delete Selected Item(s)");
		deleteItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		
		keymapItem = new MenuItem("Keymap");
		keymapItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		
		preferencesItem = new MenuItem("Preferences");
		preferencesItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	MasterController.getInstance().updateView(ViewType.PREFERENCES_VIEW, null);
		    	
		    }
		});
		
		editMenu.getItems().addAll(deleteItem, keymapItem, preferencesItem);

		playbackMenu = new Menu("Playback");
		playItem = new MenuItem("Play");
		playItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	 MasterController.getInstance().getPlaybackController().playSelection();
		    }
		});
		
		pauseItem = new MenuItem("Pause");
		pauseItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		nextTrackItem = new MenuItem("Next Track");
		nextTrackItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		previousTrackItem = new MenuItem("Previous Track");
		previousTrackItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		shuffleItem = new MenuItem("Shuffle");
		shuffleItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		repeatItem = new MenuItem("Repeat");
		repeatItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		playbackMenu.getItems().addAll(playItem, pauseItem, nextTrackItem, previousTrackItem, shuffleItem, repeatItem);

		scriptsMenu = new Menu("Scripts");
		
		importScriptsItem = new MenuItem("Import Scripts");
		importScriptsItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		executeScriptsItem = new MenuItem("Execute Scripts");
		executeScriptsItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		selectDefaultItem = new MenuItem("Select Default Scripts");
		selectDefaultItem.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Platform.exit();
		    }
		});
		scriptsMenu.getItems().addAll(importScriptsItem, executeScriptsItem, selectDefaultItem);

		this.getMenus().addAll(fileMenu, editMenu, playbackMenu, scriptsMenu);
	}
}
