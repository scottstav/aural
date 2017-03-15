package view;

import controller.AlbumTableController;
import controller.AuthorTableController;
import controller.Controller;
import controller.LibrarySelectorController;
import controller.SongTableController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * MainView that houses all other views in the application. Application is a
 * Single Document Interface.
 * 
 * @author Daniel Garcia
 *
 */
public class MainView extends BorderPane
{
    // Container for the MenuBar and PlayBack container
    private VBox topBox;

    // Items for the MenuBar
    private MenuBar menuBar;

    private Menu     fileMenu;
    private MenuItem quitItem;
    private MenuItem importItem;
    private MenuItem createPlaylistItem;

    private Menu     editMenu;
    private MenuItem deleteItem;
    private MenuItem keymapItem;
    private MenuItem preferencesItem;

    private Menu     playbackMenu;
    private MenuItem playItem;
    private MenuItem pauseItem;
    private MenuItem nextTrackItem;
    private MenuItem previousTrackItem;
    private MenuItem shuffleItem;
    private MenuItem repeatItem;

    private Menu     scriptsMenu;
    private MenuItem importScriptsItem;
    private MenuItem executeScriptsItem;
    private MenuItem selectDefaultItem;

    // Items for the playBackBox, a box containing the playback buttons
    private HBox   playBackBox;
    private Button play;
    private Button next;
    private Button previous;

    // Container for the primary view
    private VBox centerBox;

    // Container for the Artist and Album views
    private HBox artistAlbumBox;

    private AuthorTable           authorTable;
    private AuthorTableController authorTableController;

    private AlbumTable           albumTable;
    private AlbumTableController albumTableController;

    private SongTable           songTable;
    private SongTableController songTableController;

    private LibrarySelector           selector;
    private LibrarySelectorController selectorController;

    private Controller controller;

    public MainView(Controller controller)
    {

        this.setController(controller);

        topBox = new VBox();
        setTop(topBox);

        createAndPlaceMenu();
        createAndPlacePlaybackBar();
        createAndPlaceCenterBox();
        createAndPlaceLeftPanel();
    }

    /**
     * Initializes and places all Menu items in their proper place
     */
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
        scriptsMenu.getItems().addAll(importScriptsItem, executeScriptsItem,
                selectDefaultItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, playbackMenu, scriptsMenu);
        topBox.getChildren().add(menuBar);
    }

    /**
     * Initializes and places the playback bar in its proper place
     */
    private void createAndPlacePlaybackBar()
    {
        playBackBox = new HBox();
        play = new Button("Play");
        next = new Button("Next");
        previous = new Button("Prev.");

        playBackBox.getChildren().addAll(previous, play, next);
        playBackBox.setSpacing(10);
        playBackBox.setPadding(new Insets(0, 0, 0, 10));
        topBox.getChildren().add(playBackBox);
    }

    /**
     * Initializes and places the centerBox and all its elements in their proper
     * place
     */
    private void createAndPlaceCenterBox()
    {
        centerBox = new VBox();

        artistAlbumBox = new HBox();

        authorTableController = new AuthorTableController();
        authorTable = new AuthorTable(authorTableController);
        HBox.setHgrow(authorTable, Priority.ALWAYS);

        albumTableController = new AlbumTableController();
        albumTable = new AlbumTable(albumTableController);
        HBox.setHgrow(albumTable, Priority.ALWAYS);

        artistAlbumBox.getChildren().addAll(authorTable, albumTable);
        HBox.setHgrow(artistAlbumBox.getChildren().get(0), Priority.ALWAYS);
        HBox.setHgrow(artistAlbumBox.getChildren().get(1), Priority.ALWAYS);

        songTableController = new SongTableController();
        songTable = new SongTable(songTableController);

        centerBox.getChildren().addAll(artistAlbumBox, songTable);

        setCenter(centerBox);
    }

    /**
     * Creates and places the left panel.
     */
    private void createAndPlaceLeftPanel()
    {
        selectorController = new LibrarySelectorController();
        selector = new LibrarySelector(selectorController);

        setLeft(selector);
    }

    /**
     *
     * @return A references to the HBox containing the artist and album views
     */
    public HBox getArtistAlbumBox()
    {
        return artistAlbumBox;
    }

    public boolean changeView(ViewType vType, Object view)
    {
        if (vType == ViewType.LIBRARY_VIEW)
        {
            setCenter(centerBox);
            return true;
        }
        else if (vType == ViewType.KEYMAP_VIEW)
        {
            setCenter((KeyMapView) view);
            return true;
        }
        else if (vType == ViewType.PREFERENCES_VIEW)
        {
            setCenter((PreferencesView) view);
            return true;
        }
        else if (vType == ViewType.RADIO_VIEW)
        {
            setCenter((RadioView) view);
            return true;
        }
        else if (vType == ViewType.PLAYBACK_VIEW)
        {
            // setCenter((RadioView) view);
            return true;
        }

        return false;
    }

    /**
     * @return the controller
     */
    public Controller getController()
    {
        return controller;
    }

    /**
     * @param controller
     *            the controller to set
     */
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

}