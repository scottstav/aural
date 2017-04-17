package model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class KeyCodeTest
{
    public static void main(String[] args )
    {
        KeyCodeCombination combo = new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN);
        System.out.println(combo.getName());
        KeyCombination e = KeyCodeCombination.keyCombination(combo.getName());
        System.out.println(e);
        return;
    }
}