package view;

import controller.ScreenReader;
import controller.SongTableController;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import launch.MasterController;
import model.Artist;
import model.Playlist;
import model.PlaylistNode;
import model.SongEntry;

/**
 * TableView containing the songs and their relevant information.
 * 
 * @author Daniel Garcia
 *
 */
public class SongTable extends TableView<SongEntry> {
	private SongTableController controller;
	private TableColumn<SongEntry, Integer> trackId;
	private TableColumn<SongEntry, String> title;
	private TableColumn<SongEntry, Artist> artist;
	private TableColumn<SongEntry, String> album;
	private TableColumn<SongEntry, String> length;

	public SongTable(SongTableController controller) {
		this.controller = controller;
		createColumns();
	}

	/**
	 * Creates and places appropriate columns for the table.
	 */
	public void createColumns() {
		/*
		 * trackId = new TableColumn<SongEntry, Integer>("Track"); title = new
		 * TableColumn<SongEntry, String>("Title"); artist = new
		 * TableColumn<SongEntry, String>("Artist"); album = new
		 * TableColumn<SongEntry, String>("Album"); length = new
		 * TableColumn<SongEntry, String>("Length");
		 */

	    this.setAccessibleRole(AccessibleRole.TABLE_VIEW);
	    this.setAccessibleHelp("A table of songs that can be played");
	    this.setAccessibleText("Song Table");
		trackId = new TableColumn<SongEntry, Integer>("Track");
		title = new TableColumn<SongEntry, String>("Title");
		artist = new TableColumn<SongEntry, Artist>("Artist");
		album = new TableColumn<SongEntry, String>("Album");
		length = new TableColumn<SongEntry, String>("Length");

		getColumns().add(trackId);
		getColumns().add(title);
		getColumns().add(artist);
		getColumns().add(album);
		getColumns().add(length);
		
		title.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("title"));
		artist.setCellValueFactory(new PropertyValueFactory<SongEntry,Artist>("artist"));
		album.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("album"));
		length.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("length"));
		trackId.setCellValueFactory(new PropertyValueFactory<SongEntry,Integer>("trackId"));

		this.setItems(controller.getSongs());
		
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
		            MasterController.getInstance().setSelected((SongEntry) row.getItem());

		            MasterController.getInstance().getPlaybackController().playSelection();

		        } else if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
		            Node node = ((Node) event.getTarget()).getParent();
		            TableRow<?> row;
		            if (node instanceof TableRow) {
		                row = (TableRow<?>) node;
		            } else {
		                // clicking on text part
		                row = (TableRow<?>) node.getParent();
		            }
		            MasterController.getInstance().setSelected((SongEntry) row.getItem());;

		        } 
		    }
		});
		
		this.setOnKeyPressed(new EventHandler<KeyEvent> () {
		    @Override
		    public void handle(KeyEvent event)
		    {
		        //System.out.println(getSelectionModel().getSelectedItem());
		        if(event.getCode() == KeyCode.SPACE && event.isControlDown())
                {
		            Task<Integer> task = new Task<Integer>() {
                        @Override protected Integer call() throws Exception {
                            ScreenReader sr = new ScreenReader(getSelectionModel().getSelectedItem(), "SongEntry");
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
		            if(MasterController.getInstance().getPlaybackController().getPlayOrPauseProperty().equals("Pause"))
		            {
		                MasterController.getInstance().getPlaybackController().
                        setSelected(getSelectionModel().getSelectedItem());
		                MasterController.getInstance().getPlaybackController().playSelection();
		            }
		            else if (MasterController.getInstance().getPlaybackController().getPlayOrPauseProperty().equals("Play") &&
		                     !MasterController.getInstance().getPlaybackController().getNowPlayingProperty().equals(""))
		            {
		                MasterController.getInstance().getPlaybackController().pauseSong();
		            }
		            else
		            {
		                MasterController.getInstance().getPlaybackController().
                        setSelected(getSelectionModel().getSelectedItem());
                        MasterController.getInstance().getPlaybackController().playSelection();
		            }
		        }
		        else if(event.getCode() == KeyCode.LEFT && event.isControlDown())
		        {
		            MasterController.getInstance().getPlaybackController().previousSong();
		        }
		        else if(event.getCode() == KeyCode.RIGHT && event.isControlDown())
		        {
		            MasterController.getInstance().getPlaybackController().nextSong();
		        }
		    }
		});
		
		this.setRowFactory(new Callback<TableView<SongEntry>, TableRow<SongEntry>>() {  

	        @Override  
	        
	        public TableRow<SongEntry> call(TableView<SongEntry> tableView) {  
	            final TableRow<SongEntry> row = new TableRow<>();
	            final ContextMenu contextMenu = new ContextMenu();  
	            ComboBox<Playlist> playlists = new ComboBox<Playlist>(MasterController.getInstance().getSidebarController().getPlaylists());
	            playlists.setPromptText("add to playlist...");
	            //playlists.getItems().add(null);
	            playlists.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	            	System.out.println("left click on song id: " + row.getItem().getId());
	            	if(newValue != null) {
	            	    MasterController.getInstance().getSidebarController().getPlaylistById(newValue.getId()).addToPlaylist(new PlaylistNode((SongEntry) row.getItem(), null));
	            	    playlists.getSelectionModel().clearSelection();

	            	}
	    	    });
	            final CustomMenuItem addToPlaylistMenuItem = new CustomMenuItem(playlists );  
	    		addToPlaylistMenuItem.setHideOnClick(false);


	            contextMenu.getItems().add(addToPlaylistMenuItem);  
	           // Set context menu on row, but use a binding to make it only show for non-empty rows:  
	            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu)null).otherwise(contextMenu));  
	            return row ;  
	        }  
	    });
	}
}
