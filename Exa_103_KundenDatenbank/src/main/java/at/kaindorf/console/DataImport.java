package at.kaindorf.console;

import at.kaindorf.beans.Address;
import at.kaindorf.beans.Country;
import at.kaindorf.beans.Customer;
import at.kaindorf.utils.InputHandler;

import javax.persistence.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class DataImport {

    /*
        open(): Erstellen der Datenbankverbindung
     */

    private static void open(){
        em.getTransaction().begin();
    }

    /*
        close(): Schließen der Datenbankverbindung
     */
    private static void close(){
        em.close();
        emf.close();
    }

    /*
        importXML(): Daten werden über die XML-Datei eingelesen
     */
    private static void importXML(){
        List<Customer> customers = InputHandler.importXML();
        for(Customer c : customers){
            em.persist(c);
        }
        em.getTransaction().commit();
        importControl();
    }

    /*
        importJSON(): Daten werden über JSON eingelesen
     */
    private static void importJSON(){
        List<Customer> customers =  InputHandler.importJSON();
        for(Customer c : customers){
            em.persist(c);
        }
        em.getTransaction().commit();
        importControl();
    }

    private static void importControl(){
        TypedQuery<Number> countryQuery = em.createNamedQuery("Country.COUNT_ALL", Number.class);
        System.out.println("Countries imported: " + countryQuery.getSingleResult());
        TypedQuery<Number> aQuery = em.createNamedQuery("Address.COUNT_ALL", Number.class);
        System.out.println("Addresses imported: " + aQuery.getSingleResult());
        TypedQuery<Number> customerQuery = em.createNamedQuery("Customer.COUNT_ALL", Number.class);
        System.out.println("Customers imported: " + customerQuery.getSingleResult());

    }

    private static void inputSwitch(String input){
        switch (input){
            case "1":
                importXML();
                break;
            case "2":
                importJSON();
                break;
            case "3":
                System.out.print("Please enter country name or code: ");
                String countryInfo = scanner.nextLine();
                TypedQuery<Country> case3 = em.createNamedQuery("Country.FIND_BY_NAME", Country.class);
                case3.setParameter("countryInfo", countryInfo);
                try {
                    System.out.println("Found country: " + case3.getSingleResult());
                }catch (NoResultException nre){
                    System.out.println("Couldn't find a country, sorry :(");
                }
                break;
            case "4":
                TypedQuery<String> case4 = em.createNamedQuery("Country.FIND_ALL", String.class);
                System.out.println("Countries that were found:");
                case4.getResultList().stream().forEach(System.out::println);
                break;
            case "5":
                TypedQuery<Number> case5 = em.createNamedQuery("Country.COUNT_ALL", Number.class);
                System.out.println("Number of countries: " + case5.getSingleResult());
                break;
            case "6":
                TypedQuery<Number> case8 = em.createNamedQuery("Customer.FIND_YEARS", Number.class);
                System.out.println("Years that were found:");
                case8.getResultList().stream().forEach(number -> System.out.println(number.intValue()));
                break;
            case "7":
                TypedQuery<Number> case6 = em.createNamedQuery("Customer.COUNT_ALL", Number.class);
                System.out.println("Number of customers: " + case6.getSingleResult());
                break;
            case "8":
                System.out.print("Please enter country name: ");
                String countryName = scanner.nextLine();
                TypedQuery<Customer> case7 = em.createNamedQuery("Customer.FIND_FROM_COUNTRY", Customer.class);
                case7.setParameter("country", countryName);
                if(case7.getResultList().size() == 0){
                    System.out.println("Couldn't find customers with that country, sorry :(");
                }else{
                    case7.getResultList().stream().forEach(System.out::println);
                }
                break;
            default:
                System.out.printf("Please enter valid number!");
                break;
        }
    }

    //Global Variables
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Exa_103_KundenDatenbank");
    private static EntityManager em = emf.createEntityManager();
    private static Scanner scanner = new Scanner(System.in);

    /*
        main(): Die komplette Menüsteuerung erfolgt über die Main-Methode
     */
    public static void main(String[] args) {
        open();
        System.out.println("Choose an action");
        System.out.println("(1) Import data from XML");
        System.out.println("(2) Import data from JSON");
        System.out.print("Please enter option: ");
        String input = scanner.nextLine();
        inputSwitch(input);
        while(true){
            System.out.println("\nChoose an action");
            System.out.println("(3) Find Country by name");
            System.out.println("(4) Get all countries");
            System.out.println("(5) Count all countries");
            System.out.println("(6) Get all Years");
            System.out.println("(7) Count all customers");
            System.out.println("(8) Find customers of a specific country");
            System.out.println("(9) Exit and close database connection");
            System.out.print("Please enter option: ");
            input = scanner.nextLine();
            if(input.equals("9")){
                close();
                break;
            }else{
                inputSwitch(input);
            }

        }
    }

}
