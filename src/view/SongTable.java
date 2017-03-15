package view;

import controller.SongTableController;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import launch.MasterController;
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
	private TableColumn<SongEntry, String> artist;
	private TableColumn<SongEntry, String> album;
	private TableColumn<SongEntry, Integer> length;

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

		trackId = new TableColumn<SongEntry, Integer>("Track");
		title = new TableColumn<SongEntry, String>("Title");
		artist = new TableColumn<SongEntry, String>("Artist");
		album = new TableColumn<SongEntry, String>("Album");
		length = new TableColumn<SongEntry, Integer>("Length");

		getColumns().add(trackId);
		getColumns().add(title);
		getColumns().add(artist);
		getColumns().add(album);
		getColumns().add(length);
		
		title.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("title"));
		this.setItems(MasterController.getInstance().getSongs());
		
		
	}
}
