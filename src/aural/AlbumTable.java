package aural;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AlbumTable extends TableView<SongEntry>
{
   private TableColumn album;
   
   private AlbumTableController controller;
   
   public AlbumTable(AlbumTableController controller)
   {
      this.controller = controller;
      createColumns();
   }
   
   private void createColumns()
   {
      album = new TableColumn("Album");
      getColumns().add(album);
      album.setMinWidth(200);
   }
}
