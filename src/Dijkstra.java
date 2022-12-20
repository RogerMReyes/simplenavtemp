import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dijkstra {
    public static void navi(City start, HashMap<City, List<Road>> adjCities, HashMap<City,TravelDetails> travelInfo){
        travelInfo.get(start).shortestTravelTime = 0;

        ArrayList<City> completedCities = new ArrayList<>();
        ArrayList<City> citiesToCheck = new ArrayList<>();

        citiesToCheck.add(start);

        while(!citiesToCheck.isEmpty()){
            City currentCity = getCityWithShortestTime(citiesToCheck, travelInfo);
            citiesToCheck.remove(currentCity);

            for(Road road : adjCities.get(currentCity)){
                if(!completedCities.contains(road.destination)){
                    calcMinTime(currentCity, road.destination, road.travelTime, travelInfo);
                    citiesToCheck.add(road.destination);
                }
            }
            completedCities.add(currentCity);
        }
    }

    public static City getCityWithShortestTime(ArrayList<City> citiesToCheck,HashMap<City, TravelDetails> travelInfo){
        City shortestTimeCity = null;
        int shortestTime = Integer.MAX_VALUE;

        for(City city : citiesToCheck){
            if(travelInfo.get(city).shortestTravelTime < shortestTime){
                shortestTime = travelInfo.get(city).shortestTravelTime;
                shortestTimeCity = city;
            }
        }
        return shortestTimeCity;
    }

    public static void calcMinTime(City origin, City destination, int roadTravelTime, HashMap<City, TravelDetails> travelInfo){
        if(travelInfo.get(origin).shortestTravelTime + roadTravelTime < travelInfo.get(destination).shortestTravelTime){
            travelInfo.get(destination).shortestTravelTime = travelInfo.get(origin).shortestTravelTime +roadTravelTime;
            ArrayList<City> shortestPath = new ArrayList<>(travelInfo.get(origin).shortestPath);
            shortestPath.add(origin);
            travelInfo.get(destination).shortestPath = shortestPath;
        }
    }
}
