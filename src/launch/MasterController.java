package launch;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.LibraryController;
import controller.PreferencesViewController;
import controller.RadioController;
import helliker.id3.CorruptHeaderException;
import helliker.id3.ID3v2FormatException;
import helliker.id3.MP3File;
import helliker.id3.NoMPEGFramesException;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Album;
import model.Artist;
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

	// keep current controller instance to compare when changing views
	private LibraryController libraryController = null;

	private BorderPane rootPane;

	private Stage primaryStage;
	
	private Profile profile;

	private MasterController() {
		profile = getProfile();
		libraryController = new LibraryController();

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
			try {
				libraryController.addSong((File) data);
			} catch (NoMPEGFramesException | ID3v2FormatException | CorruptHeaderException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	 *  communicates with the song database
	 */
	public ObservableList<SongEntry> getSongs() {
		// TODO Auto-generated method stub
		return libraryController.getSongs();
	}
	
	/*
	 * 
	 */
	 public ObservableList<Artist> getArtists() {
		 return libraryController.getArtists();
	 }
	 
	 public ObservableList<Album> getAlbums() {
		 return libraryController.getAlbums();
	 }

}
