package aural;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Core extends Application
{
   private MainView mainView;
   private Controller controller;
   public static void main(String[] args)
   {
      Application.launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      controller = new Controller();
      mainView = new MainView(controller);
      primaryStage.setTitle("Aural");
      primaryStage.setScene(new Scene(mainView, 1000, 1000));
      primaryStage.show();
   }
}
