package view;

import controller.KeyMapController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KeyMapTest extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("KeyMap Test");
        primaryStage
                .setScene(new Scene(new KeyMapView(new KeyMapController()), 500, 500));
        primaryStage.show();
    }
}
