package launch;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.Controller;
import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import launch.MasterController;
import view.MainView;

/**
 * Launches the application
 * 
 * @author Daniel Garcia
 *
 */
public class Core extends Application {

	private Scene mainScene;
	private MainView mainView;
	private Controller controller;
	private static Logger logger = LogManager.getLogger();

	public static BorderPane rootPane;

	@Override
	public void init() throws Exception {

		super.init();

		// create author gateway
		logger.error("Calling init");
		MasterController.getInstance();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		// close gateway
		logger.error("Calling stop");
		MasterController.getInstance().close();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Setting up any objects that are easier to reference from this point.
		controller = new Controller();
		mainView = new MainView(controller);
		mainScene = new Scene(mainView, 1000, 1000);

		// Setting the stage and showing it
		primaryStage.setTitle("Aural");
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}
}
