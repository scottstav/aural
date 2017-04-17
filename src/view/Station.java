package view;

import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Station extends VBox
{
	
    public Station(String name)
    {
        getChildren().addAll(new Label(name), new Button("Listen"));
        setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 5;"
                + "-fx-border-color: blue;");
        getChildren().get(1).setAccessibleRole(AccessibleRole.BUTTON);
        getChildren().get(1).setAccessibleHelp("Plays the " + name + " radio station");
        getChildren().get(1).setAccessibleText(name + " radio");
        setSpacing(20.0);
        setAlignment(Pos.CENTER);
    }
    
}
