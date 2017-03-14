package aural;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * View for mapping keys to functions
 * 
 * @author Daniel Garcia
 *
 */
public class KeyMapView extends TableView<KeyMap>
{
    private TableColumn key;
    private TableColumn function;

    private KeyMapController keyMapController;

    public KeyMapView(KeyMapController keyMapController)
    {
        this.keyMapController = keyMapController;

        createAndPlaceKeyMapTable();
    }

    private void createAndPlaceKeyMapTable()
    {
        key = new TableColumn("Key");
        function = new TableColumn("Function");
        
        //configure columns so that they each take up half of the table
        key.prefWidthProperty().bind(widthProperty().multiply(0.5));
        function.prefWidthProperty().bind(widthProperty().multiply(0.5));
        
        getColumns().addAll(key, function);
    }
}
