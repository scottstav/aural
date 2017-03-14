package aural;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

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
   }
   
   private void createAndPlaceElements()
   {
      personalLibray = new Button("PersonaLibrary");
      playlists = new ComboBox();
      playlists.setValue("Playlists");
      radio = new Button("Radio");
      
      getChildren().addAll(personalLibray, playlists, radio);
   }
}
