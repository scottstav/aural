package view;

import controller.ArtistTableController;
import controller.ScreenReader;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
		artist.setSortType(TableColumn.SortType.ASCENDING);
		artist.setCellValueFactory(new PropertyValueFactory<Artist,String>("name"));
		this.getSortOrder().add(artist);
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		            Node node = ((Node) event.getTarget()).getParent();
		            TableRow<?> row;
		            if (node instanceof TableRow) {
		                row = (TableRow<?>) node;
		            } else {
		                // clicking on text part
		                row = (TableRow<?>) node.getParent();
		            }
		            MasterController.getInstance().getLibraryController().filterByArtist((Artist) row.getItem());;

		        }
		    }
		});
		this.setOnKeyPressed(new EventHandler<KeyEvent> () {

            @Override
            public void handle(KeyEvent event)
            {
                if(event.getCode().equals(KeyCode.SPACE) && event.isControlDown())
                {
                    Task<Integer> task = new Task<Integer>() {
                        @Override protected Integer call() throws Exception {
                            ScreenReader sr = new ScreenReader(getSelectionModel().getSelectedItem(), "Artist");
                            sr.readInfo();
                            return 0;
                        }
                    };
                    
                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
                }
                else if(event.getCode().equals(KeyCode.SPACE))
                {
                    MasterController.getInstance().getLibraryController().filterByArtist((Artist) getSelectionModel().getSelectedItem());;
                }
            }
		});
		
		this.setAccessibleText("Artist Table");
		
		this.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        MasterController.getInstance().setSelected(newSelection, "Artist");
		    }
		});
		
		this.setItems(controller.getArtists());

		artist.setMinWidth(200);
	    setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
