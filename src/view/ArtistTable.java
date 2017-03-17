package view;

import controller.ArtistTableController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artist;
import model.SongEntry;

/**
 * TableView displaying all authors
 * 
 * @author Daniel Garcia
 *
 */
public class ArtistTable extends TableView<Artist> {
	private TableColumn<Artist, String> artist;

	private ArtistTableController controller;

	public ArtistTable(ArtistTableController controller) {
		this.controller = controller;
		createColumns();
	}

	/**
	 * Creates and adds appropriate columns to the TableView
	 */
	private void createColumns() {
		artist = new TableColumn<Artist, String>("Artist");
		getColumns().add(artist);
		artist.setCellValueFactory(new PropertyValueFactory<Artist,String>("name"));
		this.setItems(controller.getArtists());


		artist.setMinWidth(200);
	    setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
