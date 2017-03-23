package view;

import controller.KeyMapViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Profile;

public class KeyMapTest extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Profile profile = new Profile();
        primaryStage.setTitle("KeyMap Test");
        primaryStage
                .setScene(new Scene(new KeyMapView(new KeyMapViewController()), 500, 500));
        primaryStage.show();
    }
}
