import java.util.Scanner;

public class Main {
    static City tacoma = new City("Tacoma");
    static City federalWay = new City("Federal Way");
    static City auburn = new City("Auburn");
    static City seattle = new City("Seattle");

    public static void main(String[] args) {
        State washington = createWashington();
        TravelInformation travelInfo = createTravelInfo();
        navPrompt(washington, travelInfo);
    }

    public static void navPrompt(State state, TravelInformation travelInfo){
        Scanner scan = new Scanner(System.in);
        City currentLocation = null;
        boolean prompt1 = true;
        while(prompt1) {
            boolean prompt2 = true;
            System.out.println("""
                    Choose a starting location!
                    1) Tacoma,
                    2) Federal Way,
                    3) Auburn,
                    4) Seattle""");
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> currentLocation = tacoma;
                case 2 -> currentLocation = federalWay;
                case 3 -> currentLocation = auburn;
                case 4 -> currentLocation = seattle;
                default -> System.out.println("Please choose one of the options!");
            }
            while(prompt2) {
                System.out.printf("""
                        Current Location : %s
                        Choose a destination!
                        1) Tacoma,
                        2) Federal Way,
                        3) Auburn,
                        4) Seattle""", currentLocation.name);
                choice = scan.nextInt();
                switch (choice) {
                    case 1 -> navigate(currentLocation, tacoma, state, travelInfo);
                    case 2 -> navigate(currentLocation, federalWay, state, travelInfo);
                    case 3 -> navigate(currentLocation, auburn, state, travelInfo);
                    case 4 -> navigate(currentLocation, seattle, state, travelInfo);
                    default -> System.out.println("Please choose one of the options!");
                }
                System.out.println("""
                        1) Check another destination.
                        2) Choose another Starting location
                        3) Exit""");
                choice = scan.nextInt();
                switch (choice){
                    case 1 -> System.out.println("Okay!");
                    case 2 -> {
                        prompt2 = false;
                        for(City key : travelInfo.travelFromSource.keySet()) {
                            travelInfo.travelFromSource.put(key, new TravelDetails());
                        };
                    }
                    case 3 -> {
                        prompt2 = false;
                        prompt1 = false;
                    }
                }
            }
        }
    }

    public static void navigate(City currentLocation, City destination, State state, TravelInformation travelInfo){
        Dijkstra.navi(currentLocation, state.adjCities, travelInfo.travelFromSource);
        for(City city : travelInfo.travelFromSource.get(destination).shortestPath){
            System.out.print(city.name + " --> ");
        }
        System.out.println(destination.name);
        System.out.println(travelInfo.travelFromSource.get(destination).shortestTravelTime + "min drive");
    }



    public static TravelInformation createTravelInfo(){
        TravelInformation travelInfo = new TravelInformation();

        travelInfo.addCities(tacoma);
        travelInfo.addCities(federalWay);
        travelInfo.addCities(auburn);
        travelInfo.addCities(seattle);
        return travelInfo;
    }
    public static State createWashington() {
        State washington = new State();

        washington.addCities(tacoma);
        washington.addCities(federalWay);
        washington.addCities(auburn);
        washington.addCities(seattle);
//        washington.addCities(lakewood);
//        washington.addCities(parkland);
//        washington.addCities(lacey);
//        washington.addCities(olympia);


        washington.addRoad(tacoma, federalWay, 15);
        washington.addRoad(tacoma, auburn, 20);
        washington.addRoad(federalWay, auburn, 2);
        washington.addRoad(federalWay, seattle, 30);
        washington.addRoad(auburn, seattle, 35);

        return washington;
    }
}