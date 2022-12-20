import java.util.ArrayList;
import java.util.HashMap;

public class TravelInformation {
    public HashMap<City, TravelDetails> travelFromSource;

    public TravelInformation() {
        this.travelFromSource = new HashMap<>();
    }

    public void addCities(City city){

        travelFromSource.put(city,new TravelDetails());
    }
}
