package view;

import java.util.List;

import controller.KeyMapViewController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.KeyMap;

/**
 * View for mapping keys to functions
 * 
 * @author Daniel Garcia
 *
 */
public class KeyMapView extends TableView<KeyMap>
{
    private TableColumn<KeyMap, String> key;
    private TableColumn<KeyMap, String> function;

    private KeyMapViewController keyMapViewController;

    public KeyMapView(KeyMapViewController keyMapViewController)
    {
        this.keyMapViewController = keyMapViewController;
        createAndPlaceKeyMapTable();
    }

    private void createAndPlaceKeyMapTable()
    {
        key = new TableColumn<KeyMap, String>("Key Combination");
        function = new TableColumn<KeyMap, String>("Function");

        // configure columns so that they each take up half of the table
        key.setCellValueFactory(new PropertyValueFactory<KeyMap, String>("keyCombination"));
        key.prefWidthProperty().bind(widthProperty().multiply(0.5));
        
        function.prefWidthProperty().bind(widthProperty().multiply(0.5));
        function.setCellValueFactory(new PropertyValueFactory<KeyMap, String>("function"));

        this.setItems(keyMapViewController.getKeyMaps());
        getColumns().addAll(function, key);
    }
    
    public KeyMapViewController getController()
    {
        return keyMapViewController;
    }
    
    public List<KeyMap> getFields()
    {
        return getItems();
    }
}
