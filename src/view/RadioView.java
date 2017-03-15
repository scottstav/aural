package view;

import java.util.ArrayList;

import controller.RadioController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class RadioView extends AnchorPane
{
    private ArrayList<Station> stations;
    private FlowPane stationPane;
        
    public RadioView(RadioController radioController)
    {
        createAndPlaceStations();
    }
    
    public void createAndPlaceStations()
    {
        stations = new ArrayList<Station>();
        stationPane = new FlowPane();
        
        stations.add(new Station("Country"));
        stations.add(new Station("Rock"));
        stations.add(new Station("Rap"));
        stations.add(new Station("RNB"));
        stations.add(new Station("Pop"));
        stations.add(new Station("Jazz"));
        
        stationPane.getChildren().addAll(stations);
        
        stationPane.setPrefWrapLength(500);
        stationPane.setHgap(20);
        stationPane.setVgap(20);
        
        getChildren().add(stationPane);
    }
}
