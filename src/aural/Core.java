package aural;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the application
 * 
 * @author Daniel Garcia
 *
 */
public class Core extends Application
{
   private Scene mainScene;
   private MainView mainView;
   private Controller controller;
   
   public static void main(String[] args)
   {
      Application.launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      // Setting up any objects that are easier to reference from this point.
      controller = new Controller();
      mainView = new MainView(controller);
      mainScene = new Scene(mainView, 1000, 1000);
      
      // Setting the stage and showing it
      primaryStage.setTitle("Aural");
      primaryStage.setScene(mainScene);
      primaryStage.show();
   }
}
