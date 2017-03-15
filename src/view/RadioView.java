package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class RadioView extends GridPane
{
    ArrayList<Station> stations;

    public RadioView()
    {
        createAndPlaceStations();
        setVgap(40);
        setHgap(40);
        setAlignment(Pos.CENTER);
        setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 10;"
                + "-fx-border-color: lightblue;");
    }

    private void createAndPlaceStations()
    {
        stations = new ArrayList<Station>();

        // I have to think of some way of dynamically
        // populating the stations; but for now, I'm just going to manually add
        // a couple to the list.

        stations.add(new Station("Country"));
        stations.add(new Station("Rock"));
        stations.add(new Station("Rap"));
        stations.add(new Station("Electro-House"));
        stations.add(new Station("Pop"));
        stations.add(new Station("RNB"));

        int topLoopLength = stations.size() / 3;
        for (int i = 0; i < topLoopLength; i++)
            for (int j = 0; j < 3; j++)
                GridPane.setConstraints(stations.get(i * 3 + j), j, i);

        getChildren().addAll(stations);
    }
}
