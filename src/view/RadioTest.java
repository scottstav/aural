package view;

import controller.RadioController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RadioTest extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Radio Test");
        primaryStage.setScene(new Scene(new RadioView(new RadioController()), 500, 500));
        primaryStage.show();
    }
}
