import java.util.ArrayList;
import java.util.List;

public class TravelDetails {
    public List<City> shortestPath;
    public int shortestTravelTime;

    public TravelDetails() {
        this.shortestPath = new ArrayList<>();
        this.shortestTravelTime = Integer.MAX_VALUE;
    }

}
