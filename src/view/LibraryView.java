package view;

import controller.AlbumTableController;
import controller.ArtistTableController;
import controller.LibraryController;
import controller.SongTableController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LibraryView extends VBox {

	// Container for the Artist and Album views
	private HBox artistAlbumBox;

	private ArtistTable artistTable;
	private ArtistTableController artistTableController;

	private AlbumTable albumTable;
	private AlbumTableController albumTableController;

	private SongTable songTable;
	private SongTableController songTableController;
	
	public LibraryView(LibraryController libraryController) {

		artistAlbumBox = new HBox();

		artistTableController = new ArtistTableController();
		artistTable = new ArtistTable(artistTableController);

		albumTableController = new AlbumTableController();
		albumTable = new AlbumTable(albumTableController);

		artistAlbumBox.getChildren().addAll(artistTable, albumTable);
		HBox.setHgrow(artistAlbumBox.getChildren().get(0), Priority.ALWAYS);
		HBox.setHgrow(artistAlbumBox.getChildren().get(1), Priority.ALWAYS);

		songTableController = new SongTableController();
		songTable = new SongTable(songTableController);

		this.getChildren().addAll(artistAlbumBox, songTable);
	}
}
