package view;

import controller.SongTableController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.SongEntry;

/**
 * TableView containing the songs and their relevant information.
 * 
 * @author Daniel Garcia
 *
 */
public class SongTable extends TableView<SongEntry>
{
    private SongTableController controller;
    private TableColumn         trackId;
    private TableColumn         title;
    private TableColumn         artist;
    private TableColumn         album;
    private TableColumn         length;

    public SongTable(SongTableController controller)
    {
        this.controller = controller;

        createColumns();
    }

    /**
     * Creates and places appropriate columns for the table.
     */
    public void createColumns()
    {
        /*
         * trackId = new TableColumn<SongEntry, Integer>("Track"); title = new
         * TableColumn<SongEntry, String>("Title"); artist = new
         * TableColumn<SongEntry, String>("Artist"); album = new
         * TableColumn<SongEntry, String>("Album"); length = new
         * TableColumn<SongEntry, String>("Length");
         */

        trackId = new TableColumn("Track");
        title = new TableColumn("Title");
        artist = new TableColumn("Artist");
        album = new TableColumn("Album");
        length = new TableColumn("Length");

        getColumns().setAll(trackId, title, artist, album, length);
    }
}
