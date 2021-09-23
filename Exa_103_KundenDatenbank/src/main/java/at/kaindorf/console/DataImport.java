package at.kaindorf.console;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataImport {

    /*
        open(): Erstellen der Datenbankverbindung
     */

    public static void open(){
        em.getTransaction().begin();
    }

    /*
        close(): Schließen der Datenbankverbindung
     */
    public static void close(){
        em.close();
        emf.close();
    }

    /*
        importXML(): Daten werden über die XML-Datei eingelesen
     */

    /*
        importJSON(): Daten werden über JSON eingelesen
     */

    //Global Variables
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Exa_103_KundenDatenbank");
    private static EntityManager em = emf.createEntityManager();

    /*
        main(): Die komplette Menüsteuerung erfolgt über die Main-Methode
     */
    public static void main(String[] args) {
        open();
        close();
    }



}
