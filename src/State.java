import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class State {

    public HashMap<City, List<Road>> adjCities;

    public State() {
        adjCities = new HashMap<>();
    }

    public void addCities(City city){
        adjCities.put(city,new ArrayList<>());
    }

    //Creates a Bidirectional connection between two cities by adding each other to their respective List of Roads
    public void addRoad(City cityOne, City cityTwo, int time){
        if(adjCities.containsKey(cityOne) && adjCities.containsKey(cityTwo)){
            adjCities.get(cityOne).add(new Road(cityTwo, time));
            adjCities.get(cityTwo).add(new Road(cityOne, time));
        } else{
            System.out.println("One of the cities is not present");
        }
    }
}
