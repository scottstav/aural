package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * view to explaining main functionality of application
 */
public class HelpView extends AnchorPane{

	 private VBox mainBox;

	 public HelpView()
	 {
		 createAndPlaceInfo();
	 }

	 public void createAndPlaceInfo()
	 {
		 mainBox = new VBox();
		 
		 Text importing = new Text("\nImporting Music\n");
		 importing.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		 Text importInfo = new Text("1. File -> Import\n"
		 		+ "2. Select Mp3 Files\n"
		 		+ "3. Selected songs appear in your library\n\n"
		 		+ "Note: Song data is saved in database in your home directory");
		 importInfo.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
		 VBox importingStuff = new VBox(importing, importInfo);
		 
		 Text navigation = new Text("\nNavigation Help\n");
		 navigation.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		 Text navInfo = new Text("Navigation is done by filtering the Main library view\n"
		 		+ "* Double click an artist to fiter songs by artist\n"
		 		+ "* Double click a album to filter songs by album\n"
		 		+ "* Single Click a playlist to filter songs, artists, albums by playlist\n"
		 		+ "* To go back to your full library, select \"Personal Library\"");
		 navInfo.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
		 VBox navStuff = new VBox(navigation, navInfo);
		 	 
		 Text playlists = new Text("\nCreating Playlists\n");
		 playlists.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		 Text playlistInfo = new Text("Create a playlist with File -> Create Playlist\n"
		 		+ "A box pops up asking for the playlist name, press Enter when done.\n"
		 		+ "To add songs to the playlist:"
		 		+ "1. Select a song (make it blue)\n"
		 		+ "2. Right click and choose a playlist in the box\n"
		 		+ "3. You can also use Edit -> Add to Playlist\n"
		 		+ "View the playlist by clicking it on the left sidebar");
		 playlistInfo.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
		 VBox playlistStuff = new VBox(playlists, playlistInfo);
		 
		 mainBox.setPadding(new Insets(15, 12, 15, 12));
		 mainBox.setSpacing(50);
		 mainBox.getChildren().addAll(importingStuff, navStuff, playlistStuff);
		 
		 getChildren().add(mainBox);
	 }
}
