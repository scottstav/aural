package view;

import controller.LibrarySelectorController;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * VBox containing all the necessary elements for the left panel on the main
 * view
 * 
 * @author Daniel Garcia
 *
 */
public class LibrarySelector extends VBox
{
    private Button   personalLibray;
    private ComboBox playlists;
    private Button   radio;

    private LibrarySelectorController controller;

    public LibrarySelector(LibrarySelectorController controller)
    {
        this.controller = controller;
        createAndPlaceElements();
        setSpacing(10);
    }

    /**
     * Initializes any objects that are needed and places them in the
     * appropriate container
     */
    private void createAndPlaceElements()
    {
        personalLibray = new Button("PersonaLibrary");

        playlists = new ComboBox();
        // Set value so user knows what this ComboBox is for
        playlists.setValue("Playlists");

        radio = new Button("Radio");

        getChildren().addAll(personalLibray, playlists, radio);
    }
}
