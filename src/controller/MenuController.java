package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import launch.MasterController;
import view.ViewType;

public class MenuController {
	
	@FXML private MenuItem menuQuit;
	@FXML private MenuItem menuImportMusic;
	@FXML private MenuItem menuCreatePlaylist;
	@FXML private MenuItem menuPreferences;
	@FXML private MenuItem menuImportScripts;
	@FXML private MenuItem menuExecuteScript;
	@FXML private MenuItem menuSelectDefaultScripts;


	

	
	private static final Logger logger = LogManager.getLogger();
	
	public MenuController() {
		//currently unused
		
	}
	
	@FXML private void handleMenuItem(ActionEvent action) {
		Object source = action.getSource();
		if(source == menuQuit) {
			Platform.exit();
		}
		if(source == menuImportMusic) {
			// launch file selector
			
		}
		if(source == menuCreatePlaylist) {
			// playlist name dialog box
			
		}
		if(source == menuPreferences) {
			// open preferences view
			MasterController.getInstance().changeView(ViewType.PREFERENCES_VIEW, null);
			return;
		}
		if(source == menuImportScripts) {
			// open file selector
		}
		if(source == menuExecuteScript) {
			// ??
		}
		if(source == menuSelectDefaultScripts) {
			MasterController.getInstance().changeView(ViewType.SCRIPT_SELECTOR, null);

		}
	}
	
	public void initialize() {
		logger.info("Controller init has been called");
				
	}
}
