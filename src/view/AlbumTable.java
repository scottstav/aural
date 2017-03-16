package view;

import controller.AlbumTableController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Album;
import model.Artist;
import model.SongEntry;

/**
 * TableView that displays all albums under currently selected artist.
 * 
 * @author Daniel Garcia
 *
 */
public class AlbumTable extends TableView<Album> {
	private TableColumn<Album, String> album;

	private AlbumTableController controller;

	public AlbumTable(AlbumTableController controller) {
		this.controller = controller;
		createColumns();
	}

	/**
	 * Creates and adds the appropriate columns to the tableview
	 */
	private void createColumns() {
		album = new TableColumn<Album, String>("Album");
		getColumns().add(album);
		album.setCellValueFactory(new PropertyValueFactory<Album,String>("name"));
		this.setItems(controller.getAlbums());
		album.setMinWidth(200);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
