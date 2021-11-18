package at.kaindorf.schuldatenbank.console;

import at.kaindorf.schuldatenbank.beans.ClassTeacher;
import at.kaindorf.schuldatenbank.beans.Room;
import at.kaindorf.schuldatenbank.util.XMLAdapter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void open(){
        emf = Persistence.createEntityManagerFactory("PU_Schuldatenbank");
        em = emf.createEntityManager();
    }

    public static void close(){
        em.close();
        emf.close();
    }

    public static void importTable(){
        em.getTransaction().begin();
        List<ClassTeacher> classTeacherList = XMLAdapter.getData();
        classTeacherList.forEach(classTeacher -> {
            em.persist(classTeacher);
        });

        em.getTransaction().commit();
    }

    private static void queryTests(){
        TypedQuery<Room> queryOne = em.createNamedQuery("Room.findByClassName", Room.class);
        queryOne.setParameter("name", "2.7.4");
        List<Room> rooms1 = queryOne.getResultList();
        System.out.println(rooms1.toString());
        TypedQuery<Room> queryTwo = em.createNamedQuery("Room.findAll", Room.class);
        List<Room> rooms2 = queryTwo.getResultList();
        System.out.println(rooms2.toString());
        TypedQuery<Room> queryThree = em.createNamedQuery("Room.findByFloor", Room.class);
        queryThree.setParameter("floor", Room.Floor.GROUND);
        List<Room> rooms3 = queryOne.getResultList();
        System.out.println(rooms3.toString());
        TypedQuery<Number> queryFour = em.createNamedQuery("Room.countAll", Number.class);
        Number anzahl = queryFour.getSingleResult();
        System.out.println(anzahl);
    }

    public static void main(String[] args) {
        open();
        importTable();
        queryTests();
        close();
    }




}
