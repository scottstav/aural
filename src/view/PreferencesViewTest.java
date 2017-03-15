package view;

import controller.PreferencesViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PreferencesViewTest extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Preferences View Test");
        primaryStage.setScene(new Scene(
                new PreferencesView(new PreferencesViewController()), 500, 500));
        primaryStage.show();
    }
}
