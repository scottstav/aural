package launch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.MainView;
import view.ViewType;

/**
 * Launches the application
 * 
 * @author Daniel Garcia
 *
 */
public class Core extends Application {

	private Scene mainScene;
	private MainView mainView;
	private static Logger logger = LogManager.getLogger();
	
	public static Logger getLogger()
	{
	    return logger;
	}

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
		mainView = new MainView();
		mainScene = new Scene(mainView, 1000, 1000);
		
		mainScene.focusOwnerProperty().addListener((prop, oldNode, newNode)
				-> MasterController.getInstance().readScreen(newNode));
		
		MasterController.getInstance().setRootPane((BorderPane) mainView);
		MasterController.getInstance().setPrimaryStage(primaryStage);
		if(MasterController.getInstance().isFirstRun())
		{
			MasterController.getInstance().updateView(ViewType.HELP, null);
		}

		// Setting the stage and showing it
		primaryStage.setTitle("Aural");
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}
}
