package view;

import controller.ArtistTableController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import launch.MasterController;
import model.Artist;

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
		            MasterController.getInstance().getLibraryController().filterByArtist((Artist) row.getItem());;

		        }
		    }
		});
		this.setItems(controller.getArtists());

		artist.setMinWidth(200);
	    setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
