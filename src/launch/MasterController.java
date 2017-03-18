package launch;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.LibraryController;
import controller.PlaybackController;
import controller.PreferencesViewController;
import controller.RadioController;
import gateway.SongGateway;
import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Profile;
import model.SongEntry;
import view.LibraryView;
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

	// Master needs to be aple to do play back stuff and edit the library
	private LibraryController libraryController = null;
	private PlaybackController playbackController = null;

	
	private SongGateway gateway = null;

	private BorderPane rootPane;

	private Stage primaryStage;
	
	private Profile profile;

	private MasterController() {
	
		
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
		

		// load view appropriate to the give vType
		if (vType == ViewType.RADIO_VIEW) {
			RadioView view = new RadioView(new RadioController());
			rootPane.setCenter(view);
			
		} else if (vType == ViewType.LIBRARY_VIEW) {
			logger.info("library called");
			LibraryView view = new LibraryView(libraryController);
			rootPane.setCenter(view);

		} else if (vType == ViewType.IMPORT_MUSIC) {
			logger.info("adding music to library...");
			MP3File file;
			try {
				file = new MP3File((File) data);
				SongEntry song = new SongEntry(file);
				gateway.insertStuff(song);
				libraryController.initialize();

			} catch (NoMPEGFramesException | ID3v2FormatException | CorruptHeaderException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			LibraryView view = new LibraryView(libraryController);
			rootPane.setCenter(view);

		} else if (vType == ViewType.PREFERENCES_VIEW) {
			Stage stage = new Stage();
	    	Scene pref = new Scene(new PreferencesView(new PreferencesViewController(profile)));
	        stage.setScene(pref);
	        stage.show();
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
		return new Profile();
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
			playbackController.setSelected(libraryController.getSongs().get(0));
		}
		
		return playbackController;
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
	
	
	
}
