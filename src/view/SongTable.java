package view;

import controller.SongTableController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artist;
import model.SongEntry;

/**
 * TableView containing the songs and their relevant information.
 * 
 * @author Daniel Garcia
 *
 */
public class SongTable extends TableView<SongEntry> {
	private SongTableController controller;
	private TableColumn<SongEntry, Integer> track_id;
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

		track_id = new TableColumn<SongEntry, Integer>("Track");
		title = new TableColumn<SongEntry, String>("Title");
		artist = new TableColumn<SongEntry, Artist>("Artist");
		album = new TableColumn<SongEntry, String>("Album");
		length = new TableColumn<SongEntry, String>("Length");

		getColumns().add(track_id);
		getColumns().add(title);
		getColumns().add(artist);
		getColumns().add(album);
		getColumns().add(length);
		
		title.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("title"));
		artist.setCellValueFactory(new PropertyValueFactory<SongEntry,Artist>("artist"));
		album.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("album"));
		length.setCellValueFactory(new PropertyValueFactory<SongEntry,String>("length"));
		track_id.setCellValueFactory(new PropertyValueFactory<SongEntry,Integer>("track_id"));

		this.setItems(controller.getSongs());
		
		
	}
}
