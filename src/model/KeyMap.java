package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.KeyCombination;

/**
 * Model upon which keys are going to be made.
 * 
 * @author Daniel Garcia
 *
 */
public class KeyMap
{
    private KeyCombination key;
    
    private SimpleStringProperty function = new SimpleStringProperty();
    private SimpleStringProperty keyCombination = new SimpleStringProperty();
    
    public KeyMap(String function, KeyCombination KeyCombination)
    {
        this.function.set(function);
        this.key = KeyCombination;
        this.keyCombination.set(key.getName());
    }
    
    public KeyCombination getKeyCombination()
    {
        return key;
    }
    
    public String getFunction()
    {
        return function.get();
    }
    
    public String getKeyCombinationString()
    {
        return key.getName();
    }
    
    public boolean equals(Object obj)
    {
        if(obj == null || obj.getClass() != getClass())
            return false;
        KeyMap k = (KeyMap) obj;
        return k.getFunction() == getFunction() &&
               k.getKeyCombination() == getKeyCombination();
    }
}