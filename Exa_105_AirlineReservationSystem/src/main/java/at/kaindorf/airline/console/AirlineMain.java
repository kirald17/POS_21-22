package at.kaindorf.airline.console;

import at.kaindorf.airline.pojos.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AirlineMain {
    //Alle Paths
    private static final Path PATH_AIRCRAFT_TYPE = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "aircrafttypes.csv");
    private static final Path PATH_AIRLINES = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "airlines.csv");
    private static final Path PATH_AIRPORTS = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "airports.csv");
    private static EntityManagerFactory emf;
    private static EntityManager em;

    /*
        TODO: Eine Methode erstellen die mir meine Daten Random zusammenbaut
        Ich brauche dafür eine Liste an allen Aircraft, Airlines und Airports

     */
    public static void createRandomData(){
        Set<AircraftType> aircraftTypeSet = DataImport.getAircraftTypes(PATH_AIRCRAFT_TYPE);
        List<Airline> airlines = DataImport.getAirlines(PATH_AIRLINES);
        List<Airport> airports = DataImport.getAirports(PATH_AIRPORTS);

        List<Aircraft> aircrafts = DataImport.createAircrafts(aircraftTypeSet, airlines);
        List<Flight> flights = DataImport.createFlights(aircrafts, airlines, airports);

        //Alle Daten in die Datenbank einspielen
        flights.forEach(flight -> {
            em.persist(flight);
        });

    }


    /*
        TODO: Eine Methode die alle Queries Aufruft und ausführt
        + Und das alles in einem akzeptablen Format
     */
    public static void loadQueries(){
        List<Flight> flights;
        List<Aircraft> aircrafts = new ArrayList<>();

        System.out.println("--------------------------------\n");
        System.out.println("!Einige mit fixen Werten erstellten Abfragen!");
        System.out.println("--------------------------------\n");

        TypedQuery<Flight> queryOne = em.createNamedQuery("Flight.GETALLFLIGHTSOFAIRLINE", Flight.class);
        queryOne.setParameter("airlineName", "Air Atonabee");
        flights = queryOne.getResultList();
        System.out.println("1) GETALLFLIGHTSOFAIRLINE");
        flights.forEach(System.out::println);
        System.out.println("--------------------------------\n");

        TypedQuery<Flight> queryTwo = em.createNamedQuery("Flight.GETALLARRIVALFLIGHTSOFAIRPORT", Flight.class);
        queryTwo.setParameter("id", 46);
        flights = queryTwo.getResultList();
        System.out.println("2) GETALLARRIVALFLIGHTSOFAIRPORT");
        flights.stream().forEach(System.out::println);
        System.out.println("--------------------------------\n");

        TypedQuery<Flight> queryThree = em.createNamedQuery("Flight.GETALLDEPARTUREFLIGHTSOFAIRPORT", Flight.class);
        queryThree.setParameter("name", "Killian Airfield");
        flights = queryThree.getResultList();
        System.out.println("3) GETALLDEPARTUREFLIGHTSOFAIRPORT");
        flights.stream().forEach(System.out::println);
        System.out.println("--------------------------------\n");

        TypedQuery<Airport> queryFour = em.createNamedQuery("Airport.GETALLAIRPORTSOFAIRLINE", Airport.class);
        queryFour.setParameter("airlineName", "Aermarche");
        List<Airport> airports = queryFour.getResultList();
        System.out.println("4) GETALLAIRPORTSOFAIRLINE");
        airports.forEach(System.out::println);
        System.out.println("--------------------------------\n");


        TypedQuery<Aircraft> queryFive = em.createNamedQuery("Aircraft.GETALLAIRPORTSOFAIRLINE", Aircraft.class);
        queryFive.setParameter("airlineName", "Air Memphis");
        aircrafts = queryFive.getResultList();
        System.out.println("5) GETALLAIRPORTSOFAIRLINE");
        aircrafts.forEach(System.out::println);
        System.out.println("--------------------------------\n");

        TypedQuery<Aircraft> querySix = em.createNamedQuery("Aircraft.GETALLAIRPORTSOFAIRPORT", Aircraft.class);
        querySix.setParameter("airport", "Air Memphis");
        aircrafts = querySix.getResultList();
        System.out.println("6) GETALLAIRPORTSOFAIRPORT");
        aircrafts.forEach(System.out::println);
        System.out.println("--------------------------------\n");

    }


    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("PU_Airline");
        em = emf.createEntityManager();

        em.getTransaction().begin();
        createRandomData();
        em.getTransaction().commit();

        //Funktionieren leider nicht sehr gut, da eben immer neue zufällige Daten in der Datenbank stehen
        loadQueries();

        em.close();
        emf.close();

    }
}
