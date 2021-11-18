package at.kaindorf.airline.console;

import at.kaindorf.airline.pojos.*;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class DataImport {

    /*
        TODO: Da ich bei allen Sachen die Erste Zeile Skippe werde ich hier eine Methode schreiben die es für mich einmal machen
     */
    public static Set<String> skipFirstLine(Path path, int lines){
        //Ich Returne dann ein Set mit all den Lines in der Methode
        Set<String> data = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
            data = br.lines().skip(lines).map(String::toString).collect(Collectors.toSet());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

    /*
        TODO: Alle AircraftTypes einlesen
     */
    public static Set<AircraftType> getAircraftTypes(Path path){
        Set<AircraftType> aircrafts = new HashSet<>();
        Set<String> data = skipFirstLine(path, 1);

        // In dieser For-Each Schleife lösche ich Schritt für Schritt alle Leerzeichen
        data.forEach(line -> {
            String[] lines = line.split(",");
            for(int i = 0; i < lines.length; i++){
                //Bei einem leeren String
                if(lines[i].contains(" ")){
                    while(true){
                        //Schauen ob der letzte Char ein Leerzeichen ist
                        if(lines[i].charAt(lines[i].length() - 1) == ' '){
                            //Durch den Substring wird die Länge immer und immer kürzer
                            lines[i] = lines[i].substring(0, (lines[i].length() - 1));
                        }else{
                            break;
                        }
                    }
                }
            }
            //Das Element ins Set einfügen
            aircrafts.add(new AircraftType(lines[1], Integer.parseInt(lines[8])));
        });

        return aircrafts;
    }

    /*
        TODO: Alle Airlines einlesen
     */
    public static List<Airline> getAirlines(Path path){
        List<Airline> airlines = new ArrayList<>();
        Set<String> data = skipFirstLine(path, 0);

        data.forEach(line -> {
            String[] lines = line.split(",");
            // Element hinzufügen
            airlines.add(new Airline(Long.parseLong(lines[0]), lines[1]));
        });

        return airlines;
    }

    /*
        TODO: Alle Airports einlesen
     */
    public static List<Airport> getAirports(Path path){
        List<Airport> airports = new ArrayList<>();
        Set<String> data = skipFirstLine(path, 1);

        data.forEach(line -> {
            String[] lines = line.split(",");
            airports.add(new Airport(lines[8], lines[10], lines[3]));
        });

        return airports;
    }

    /*
        TODO: Ich muss mir jetzt Aircrafts erstellen
     */
    public static List<Aircraft> createAircrafts(Set<AircraftType> aircrafts, List<Airline> airlines){
        Random r = new Random();
        List<Aircraft> aircraftList = new ArrayList<>();
        //Set zu liste weil ich später mit Index auf die Werte zugreifen will
        List<AircraftType> aircraftTypeList = aircrafts.stream().collect(Collectors.toList());
        airlines.forEach(airline -> {
//            if(airline.getAirlineId() == (airlines.size() / 2)){
//                return;
//            }
            //Erstellen eines Aircraft --> Welches wird Random ausgewählt
            aircraftList.add(new Aircraft(airline, aircraftTypeList.get(r.nextInt(aircraftTypeList.size()))));
        });

        return aircraftList;
    }

    /*
        TODO: Ich muss mir jetzt Flights erstellen
     */
    public static List<Flight> createFlights(List<Aircraft> aircrafts, List<Airline> airlines, List<Airport> airports){
        Random r = new Random();
        List<Flight> flights = new ArrayList<>();
        //Ich erstelle nur 1000 Flüge um Java und meinen Laptop nicht komplett auszulasten
        int anz = 0;
        for(Airline airline : airlines){
            if(anz == 1000){
                break;
            }

            //Zwei zufällige Zeiten für die Flüge erstellen
            LocalTime departureTime = LocalTime.now().minus(r.nextInt(12), ChronoUnit.HOURS).minus(r.nextInt(59), ChronoUnit.MINUTES);
            LocalTime arrivalTime = LocalTime.now().plus(r.nextInt(12), ChronoUnit.HOURS).plus(r.nextInt(59), ChronoUnit.MINUTES);

            //Neues Flight Objekt erstellen
            flights.add(new Flight(
                    departureTime,
                    arrivalTime,
                    airlines.get(r.nextInt(airlines.size())),
                    aircrafts.get(r.nextInt(aircrafts.size())),
                    airports.get(r.nextInt(airports.size())),
                    airports.get(r.nextInt(airports.size()))
                    ));
            anz++;
        }

        createRelations(flights, airports);
        return flights;
    }

    /*
        TODO: Ich erstelle mir hier eine Methode die alle meine Bidirektionalen Beziehungen erstellt
        INFO:
        Ich habe mir diese Funktion von Thomas erklären lassen.
        Wir haben sie zusammen besprochen und ich habe sie zusammen mit ihm zusammen gecoded
     */
    public static void createRelations(List<Flight> flights, List<Airport> airports){
        Random r = new Random();
        flights.forEach(flight -> {
            //Das Flugelement mit allen Elementen verbinden
            flight.getAircraft().getFlights().add(flight);
            flight.getAirline().getFlights().add(flight);
            flight.getDepartureAirport().getDepartureFlights().add(flight);
            flight.getArrivalAirport().getArrivalFlight().add(flight);
            flight.getAircraft().getAirline().getAircrafts().add(flight.getAircraft());
            //Bidirektionale Verbindung von Aircraft zu Airport
            flight.getAircraft().getAirports().add(airports.get(r.nextInt(airports.size())));
            //Aircraft mit dem neusten Airport verbinden
            flight.getAircraft().getAirports().get(flight.getAircraft().getAirports().size() - 1).getAircrafts().add(flight.getAircraft());
        });
    }


}
