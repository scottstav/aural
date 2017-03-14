package aural;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
      controller = new Controller();
      mainView = new MainView(controller);
      mainScene = new Scene(mainView, 1000, 1000);
      configureListeners();
      primaryStage.setTitle("Aural");
      primaryStage.setScene(mainScene);
      primaryStage.show();
   }
   
   private void configureListeners()
   {
     /* mainScene.widthProperty().addListener(new ChangeListener<Number>() {
         @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
             mainView.getArtistAlbumBox().resize(newSceneWidth.doubleValue() - 20, mainView.getArtistAlbumBox().getHeight());
         }
     });*/
   }
}
