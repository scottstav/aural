package view;

import controller.AlbumTableController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import launch.MasterController;
import model.Album;
import model.Artist;

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
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		            Node node = ((Node) event.getTarget()).getParent();
		            TableRow row;
		            if (node instanceof TableRow) {
		                row = (TableRow) node;
		            } else {
		                // clicking on text part
		                row = (TableRow) node.getParent();
		            }
		            MasterController.getInstance().getLibraryController().filterByAlbum((Album) row.getItem());;

		        }
		    }
		});
		this.setItems(controller.getAlbums());
		album.setMinWidth(200);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
