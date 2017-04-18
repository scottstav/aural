package view;

import controller.AlbumTableController;
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
import model.Album;

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
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event)
            {
                if(event.getCode().equals(KeyCode.SPACE) && event.isControlDown())
                {
                    Task<Integer> task = new Task<Integer>() {
                        @Override protected Integer call() throws Exception {
                            ScreenReader sr = new ScreenReader(getSelectionModel().getSelectedItem(), "Album");
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
                    MasterController.getInstance().getLibraryController().filterByAlbum((Album) getSelectionModel().getSelectedItem());;
                }
                
            }
		});
		album.setMinWidth(200);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
}
