package view;

import controller.AlbumTableController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.SongEntry;

/**
 * TableView that displays all albums under currently selected artist.
 * 
 * @author Daniel Garcia
 *
 */
public class AlbumTable extends TableView<SongEntry> {
	private TableColumn album;

	private AlbumTableController controller;

	public AlbumTable(AlbumTableController controller) {
		this.controller = controller;
		createColumns();
	}

	/**
	 * Creates and adds the appropriate columns to the tableview
	 */
	private void createColumns() {
		album = new TableColumn("Album");
		getColumns().add(album);
		album.setMinWidth(200);
	}
}
