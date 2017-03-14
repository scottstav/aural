package aural;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends BorderPane
{
   private VBox topBox;
   
   private MenuBar   menuBar;
   
   private Menu      fileMenu;
   private MenuItem  quitItem;
   private MenuItem  importItem;
   private MenuItem  createPlaylistItem;
   
   private Menu      editMenu;
   private MenuItem  deleteItem;
   private MenuItem  keymapItem;
   private MenuItem  preferencesItem;
   
   private Menu      playbackMenu;
   private MenuItem  playItem;
   private MenuItem  pauseItem;
   private MenuItem  nextTrackItem;
   private MenuItem  previousTrackItem;
   private MenuItem  shuffleItem;
   private MenuItem  repeatItem;
   
   private Menu      scriptsMenu;
   private MenuItem  importScriptsItem;
   private MenuItem  executeScriptsItem;
   private MenuItem  selectDefaultItem;
   
   private HBox playBackBox;
   private Button play;
   private Button next;
   private Button previous;
   
   private Controller controller;
   
   public MainView(Controller controller)
   {
      
      this.controller = controller;
      
      topBox = new VBox();
      setTop(topBox);
      
      createAndPlaceMenu();
      createAndPlacePlaybackBar();
   }
   
   private void createAndPlaceMenu()
   {
      menuBar = new MenuBar();
      
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
      playbackMenu.getItems().addAll(playItem, pauseItem, nextTrackItem,
                                     previousTrackItem, shuffleItem, repeatItem);
      
      scriptsMenu = new Menu("Scripts");
      importScriptsItem = new MenuItem("Import Scripts");
      executeScriptsItem = new MenuItem("Execute Scripts");
      selectDefaultItem = new MenuItem("Select Default Scripts");
      scriptsMenu.getItems().addAll(importScriptsItem, executeScriptsItem, selectDefaultItem);
      
      menuBar.getMenus().addAll(fileMenu, editMenu, playbackMenu, scriptsMenu);
      topBox.getChildren().add(menuBar);
   }
   
   private void createAndPlacePlaybackBar()
   {
      playBackBox = new HBox();
      play = new Button("Play");
      next = new Button("Next");
      previous = new Button("Previous");
      
      playBackBox.getChildren().addAll(previous, play, next);
      playBackBox.setSpacing(10);
      playBackBox.setPadding(new Insets(0, 0, 0, 10));
      topBox.getChildren().add(playBackBox);
   }
   
   
}
