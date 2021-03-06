package view;

import controller.LibraryController;
import controller.MenuController;
import controller.PlaybackController;
import controller.SidebarController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import launch.MasterController;

/**
 * MainView that houses all other views in the application. Application is a
 * Single Document Interface.
 * 
 * @author Daniel Garcia
 *
 */
public class MainView extends BorderPane {
	
	// Container for the MenuBar and PlayBack container
	private VBox topBox;


	private MenuController menuController;
	private PlaybackController playbackController;
	private SidebarController sidebarController;
	private LibraryController libraryController;

	public MainView() {

		menuController = new MenuController();
		playbackController = MasterController.getInstance().getPlaybackController();
		sidebarController = MasterController.getInstance().getSidebarController();
		libraryController = MasterController.getInstance().getLibraryController();	
		libraryController.initialize();
		playbackController.update();

		// playback and menu (top)
		topBox = new VBox();
		topBox.getChildren().add(new MenuView(menuController));
		topBox.getChildren().add(new PlaybackView(playbackController));
		setTop(topBox);

		// library (center)
		this.setCenter(new LibraryView(libraryController));

		// sidebar menu (left menu)
		setLeft(new SidebarView(sidebarController));	
		
	}

}
