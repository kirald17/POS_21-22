package at.kaindorf.console;

import at.kaindorf.beans.Address;
import at.kaindorf.beans.Customer;
import at.kaindorf.utils.InputHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
            case "0":
                open();
                break;
            case "1":
                importXML();
                break;
            case "2":
                importJSON();
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
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
