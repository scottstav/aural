package launch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.KeyMapViewController;
import controller.LibraryController;
import controller.PlaybackController;
import controller.PreferencesViewController;
import controller.RadioController;
import controller.ScreenReader;
import controller.SidebarController;
import gateway.SongGateway;
import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Playlist;
import model.Profile;
import model.SongEntry;
import view.KeyMapView;
import view.HelpView;
import view.LibraryView;
import view.MenuView;
import view.PreferencesView;
import view.RadioView;
import view.ViewType;

/**
 * keeps program state info, gateways, and centralized control methods
 * implements singleton pattern
 * 
 * @author scottstav
 *
 */
public class MasterController {
	private static MasterController instance = null;
	
	private Logger logger = LogManager.getLogger();
	
	private boolean screenReaderEnabled = false;

	// Master needs to be aple to do play back stuff and edit the library
	private LibraryController libraryController = null;
	private PlaybackController playbackController = null;
	private SidebarController sidebarController = null;
	private KeyMapViewController keyMapViewController = null;

	
	private SongGateway gateway = null;

	private BorderPane rootPane;

	private Stage primaryStage;
	
	private Profile profile = null;
	
	private ViewType currentView;

	private MasterController() {
	
		currentView = ViewType.LIBRARY_VIEW;
	}

	/**
	 * 
	 * update the application view
	 * 
	 * @param vType
	 * @param data
	 * @return
	 */
	public boolean updateView(ViewType vType, Object data) {
	    if(currentView == ViewType.KEYMAP_VIEW)
	    {
	        getKeyMapViewController().update();
	    }
		currentView = vType;

		// load view appropriate to the give vType
		if (vType == ViewType.RADIO_VIEW) {
			RadioView view = new RadioView(new RadioController());
			rootPane.setCenter(view);
			
		} else if (vType == ViewType.LIBRARY_VIEW) {
			logger.info("library called");
			libraryController.initialize();
			LibraryView view = new LibraryView(libraryController);
			playbackController.update();
			rootPane.setCenter(view);

		} else if (vType == ViewType.IMPORT_MUSIC) {
			logger.info("adding music to library...");
			MP3File file;
			try {
				file = new MP3File((File) data);
				SongEntry song = new SongEntry(file);
				gateway.insertStuff(song);
				libraryController.initialize();
				playbackController.update();

			} catch (NoMPEGFramesException | ID3v2FormatException | CorruptHeaderException | IOException e1) {
				e1.printStackTrace();
			}
			
			
			LibraryView view = new LibraryView(libraryController);
			rootPane.setCenter(view);

		} else if (vType == ViewType.PREFERENCES_VIEW) {
			Stage stage = new Stage();
	    	Scene pref = new Scene(new PreferencesView(new PreferencesViewController(profile)));
	        stage.setScene(pref);
	        stage.show();
		}	else if (vType == ViewType.CREATE_PLAYLIST) {
			Stage stage = new Stage();
			VBox inputBox = new VBox();
	        TextField playlistNameField = new TextField();
	        // prompt for playlist name
	        playlistNameField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent keyEvent) {
	                if (keyEvent.getCode() == KeyCode.ENTER)  {
	                    String name = playlistNameField.getText();	                    
	                    // close prompt
	                    stage.hide();
	                    
	                    //create playlist
	                    Playlist playlist = new Playlist(name, 0);
	                    gateway.insertPlaylist(playlist);
	                    MasterController.getInstance().getSidebarController().addPlaylist(playlist);
	                    
	                }
	            }
	        });	       
	        Label label = new Label("playlist name: ");
	        inputBox.getChildren().addAll(label, playlistNameField);
	        inputBox.setAlignment(Pos.CENTER);

	    	Scene playlist = new Scene(inputBox);
	        stage.setScene(playlist);
	        stage.show();

		} else if (vType == ViewType.PLAYLIST_VIEW) {
			libraryController.filterByPlaylist((Playlist) data);
			playbackController.update();
			LibraryView view = new LibraryView(libraryController);
			rootPane.setCenter(view);
			
		} else if (vType == ViewType.KEYMAP_VIEW) {
		    rootPane.setCenter(new KeyMapView(getKeyMapViewController()));
		    ((KeyMapView)rootPane.getCenter()).getController().setView((KeyMapView)rootPane.getCenter());
		    
		} else if (vType == ViewType.HELP) {
			HelpView view = new HelpView();
			rootPane.setCenter(view);
			
		}
		return true;
	}

	/**
	 * clean up method to close gateways, etc.
	 */
	public void close() {

	}

	public static MasterController getInstance() {
		if (instance == null)
			instance = new MasterController();
		return instance;
	}

	public BorderPane getRootPane() {
		return rootPane;
	}

	public void setRootPane(BorderPane rootPane) {
		this.rootPane = rootPane;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	/*
	 *  get the saved profile object
	 */
	public Profile getProfile() {
	    if(profile == null)
	    {
	        profile = new Profile();
	        getKeyMapViewController().initialize();
	        getKeyMapViewController().update();
	    }
		return profile;
	}
	
	/*
	 * notifies all necessary controllers of the currently selected song/artist/album
	 * 
	 * menu controller, playback controller, and sidebar controller 
	 * all need to know what is currently selected for different reasons
	 * 
	 */
	public void setSelected(Object selected)
	{
		getPlaybackController();

		playbackController.setSelected((SongEntry) selected);
	}
	
	public SongEntry getSelected()
	{
		getPlaybackController();

		return playbackController.getSelected();
	}

	/*
	 *  communicates with the song database
	 */
	public LibraryController getLibraryController() {
		if (libraryController == null)
		{
			libraryController = new LibraryController();
		}
		
		return libraryController;
	}
	
	public PlaybackController getPlaybackController() {
		if (playbackController == null)
		{
			playbackController = new PlaybackController();
			if(!(getLibraryController().getSongs().isEmpty()))
				playbackController.setSelected(getLibraryController().getSongs().get(0));
		}
		
		return playbackController;
	}
	
	public SidebarController getSidebarController() {
		if (sidebarController == null)
		{
			sidebarController = new SidebarController();
		}
		
		return sidebarController;
	}
	
	/*
	 * do gateway stuff from everywhere using this method
	 */
	public SongGateway getGateway() {
		if (gateway == null)
		{
			gateway = new SongGateway();
		}
		
		return gateway;
	}
	
	public KeyMapViewController getKeyMapViewController() {
	    if (keyMapViewController == null) {
	        keyMapViewController = new KeyMapViewController();
	    }
	    return keyMapViewController;
	}
	
	public List<Menu> getMenus()
	{
	    return ((MenuBar) ((VBox)rootPane.getTop()).getChildren().get(0)).getMenus();
	}
	
	public MenuView getMenuView()
	{
	    return ((MenuView) ((VBox)rootPane.getTop()).getChildren().get(0));
	}
	
	public ViewType getCurrentViewType()
	{
	    return currentView;
	}
	
	public boolean isScreenReaderEnabled()
	{
	    return screenReaderEnabled;
	}
	
	public void toggleScreenReader() 
	{
		screenReaderEnabled = !screenReaderEnabled;
	}

	public void readScreen(Node newNode) {
		// TODO Auto-generated method stub
		if(!isScreenReaderEnabled())
			return;
		ScreenReader sr = null;
		if(newNode instanceof Button)
			sr = new ScreenReader( newNode, "Button");
		else if (newNode instanceof ListView)
			sr = new ScreenReader(newNode, "List");
		else if (newNode instanceof TableView)
			sr = new ScreenReader(newNode, "Table");
			
        sr.readInfo();
		
	}
}
