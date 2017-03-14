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

	private static final Logger logger = LogManager.getLogger();

    public MenuController() {
		// currently unused

	}

	public void initialize() {
		logger.info("Controller init has been called");

	}
}
