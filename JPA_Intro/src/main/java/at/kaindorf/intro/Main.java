package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Address;
import at.kaindorf.intro.pojos.SchoolClass;
import at.kaindorf.intro.pojos.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

        // JPQL-Queries
        /*
            Query oder TypedQuery
            Entity Manager -> Queries, NamedQueries, Native Queries
            Es gibt kein SELECT *
         */
        TypedQuery<Student> typedQuery = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = typedQuery.getResultList();
        students.stream().forEach(System.out::println);

        TypedQuery<Address> addressTypedQuery = em.createNamedQuery("Address.GetAll", Address.class);
        addressTypedQuery.setParameter("street", "%Str%");
        List<Address> addresses = addressTypedQuery.getResultList();
        addresses.stream().forEach(System.out::println);

        TypedQuery<Address> addressTypedQuery2 = em.createNamedQuery("Address.GetByClassname", Address.class);
        addressTypedQuery2.setParameter("classname", "5DHIF");
        addresses.stream().forEach(System.out::println);

        Query query = em.createNamedQuery("Student.Count");
        Long anz = (Long) query.getSingleResult();
        System.out.println(anz);

        em.close();
        emf.close();

    }
}
