package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Address;
import at.kaindorf.intro.pojos.SchoolClass;
import at.kaindorf.intro.pojos.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Baut die connection zu unsererer Datenbank auf
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        // Verwaltet alle Entities
        EntityManager em = emf.createEntityManager();

        /*
            1. Den EM und den EMF immer schließen, sonst kann es zu Problemen kommen
            2. Zuerst EM schließen, dann den EMF
         */


        Student student = new Student("5DHIF", 1L, "Nico", "Baumann", LocalDate.now());
        Student student2 = new Student("5DHIF", 2L,"Adrian", "Berner", LocalDate.now().minusDays(1000));


        Address address = new Address("Kaindorf", "GrazerStr", "220");
        Address address2 = new Address("Kaindorf", "GrazerStr", "221");
        student.setAddress(address);
        address.setStudent(student);
        student2.setAddress(address2);
        address2.setStudent(student2);

        SchoolClass sc = new SchoolClass("5DHIF");
        sc.addStudent(student);
        sc.addStudent(student2);
        em.persist(sc);



        em.getTransaction().begin();
        /*
            Beim aufruf von persist wird aus dem Java Kontext ein Objekt in den Persist Kontext verschoben
            Unser Javaobjekt ist aber noch nicht in der Datenbank
         */
        em.persist(student);
        em.persist(student2);

        //em.persist(address);
        //em.persist(address2);

        /*
            Erst mit dem Aufruf der .getTransaction().commit() wird unser Objekt in die Datenbank gespeichert.

            update() Verändert ein Objekt im Kontext und anschließend in der Datenbank
            detach() nimmt Objekte aus dem Percistence Kontext raus
            merch()  fügt Objekte wieder in den Percistence Kontext ein
            remove() wird zuerst aus dem Percistence Kontext gelöscht und dann beim nächsten Commit
            auch aus der Datenbank
         */
        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}
