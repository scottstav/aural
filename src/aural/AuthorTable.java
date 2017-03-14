package aural;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AuthorTable extends TableView<SongEntry>
{
   private TableColumn author;
   
   private AuthorTableController controller;
   
   public AuthorTable(AuthorTableController controller)
   {
      this.controller = controller;
      createColumns();
   }
   
   private void createColumns()
   {
      author = new TableColumn("Author");
      getColumns().add(author);
      author.setMinWidth(200);
   }
}
