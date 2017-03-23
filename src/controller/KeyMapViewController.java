package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import launch.MasterController;
import model.KeyMap;
import view.KeyMapView;

public class KeyMapViewController
{
    private KeyMapView view;
    private ObservableList<KeyMap> keys = FXCollections.observableArrayList();
    public KeyMapViewController()
    {
        this.initialize();
    }
    
    public void initialize() 
    {
        keys.setAll(MasterController.getInstance().getProfile().getKeyMaps());
    }
    
    public void update()
    {
        for(Menu m: MasterController.getInstance().getMenus())
        {
            for(MenuItem mi : m.getItems())
            {
                for(KeyMap k : keys)
                {
                    if(mi != null && mi.getText() != null && mi.getText().equals(k.getFunction()))
                    {
                        mi.setAccelerator(k.getKeyCombination());
                    }
                }
               
            }
        }
        
        MasterController.getInstance().getPrimaryStage().show();
    }
    
    public ObservableList<KeyMap> getKeyMaps()
    {
        return keys;
    }

    public KeyMapView getView()
    {
        return view;
    }

    public void setView(KeyMapView view)
    {
        this.view = view;
    }
}
