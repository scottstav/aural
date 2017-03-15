package launch;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
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

	// keep current controller instance to compare when changing views
	private Object currentViewController = null;

	private BorderPane rootPane;

	private MasterController() {
		// create gateways

	}

	/**
	 * transition to new view in window's center area of border pane TODO: if
	 * current view has changed then prompt to save
	 * 
	 * @param vType
	 * @param data
	 * @return
	 */
	public boolean changeView(ViewType vType, Object data) {
		FXMLLoader loader = null;

		// if we were at a detail view, we need to check if input has changed

		// load view appropriate to the give vType
		if (vType == ViewType.RADIO_VIEW) {

		} else if (vType == ViewType.LIBRARY_VIEW) {

		} else if (vType == ViewType.PLAYBACK_VIEW) {

		}

		Parent view = null;
		try {
			view = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// attach view to application center of border pane
		rootPane.setCenter(view);

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

}
