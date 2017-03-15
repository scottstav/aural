package view;

import controller.PreferencesViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * View for changing preferences.
 * 
 * @author Daniel Garcia
 *
 */
public class PreferencesView extends HBox
{
    private static final double labelSpacing = 65.0;
    private static final double inputSpacing = 50.0;
    private static final double topSpacing   = 40.0;
    private static final double totalSpacing = 400.0;

    private VBox labelBox;
    private VBox inputBox;

    private Label     screenSize;
    private TextField screenSizeInput;

    private Label     defaultVolume;
    private TextField defaultVolumeInput;

    private Label     fontSize;
    private TextField fontSizeInput;

    private PreferencesViewController preferencesViewControler;

    public PreferencesView(PreferencesViewController preferencesViewController)
    {
        createAndPlaceView();
        setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 10;"
                + "-fx-border-color: lightblue;");
    }

    private void createAndPlaceView()
    {
        labelBox = new VBox();
        screenSize = new Label("Screen Size");
        defaultVolume = new Label("Default Volume");
        fontSize = new Label("Font Size");
        labelBox.getChildren().addAll(screenSize, defaultVolume, fontSize);
        labelBox.setSpacing(labelSpacing);
        /*
         * labelBox.setStyle("-fx-border-style: solid inside;"+
         * "-fx-border-width: 5;");
         */

        inputBox = new VBox();
        screenSizeInput = new TextField();
        defaultVolumeInput = new TextField();
        fontSizeInput = new TextField();
        inputBox.getChildren().addAll(screenSizeInput, defaultVolumeInput, fontSizeInput);
        inputBox.setSpacing(inputSpacing);
        /*
         * inputBox.setStyle("-fx-border-style: solid inside;"+
         * "-fx-border-width: 5;");
         */

        getChildren().addAll(labelBox, inputBox);
        setAlignment(Pos.CENTER);
        setSpacing(totalSpacing);
        setPadding(new Insets(topSpacing, 0, 0, 0));
    }
}
