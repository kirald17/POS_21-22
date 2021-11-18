package at.kaindorf.schuldatenbank.util;

import at.kaindorf.schuldatenbank.beans.*;

import javax.xml.bind.JAXB;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XMLAdapter {
    private static final Path PATH = Paths.get(System.getProperty("user.dir"),"src","main","resources", "schooldata.xml");

    public static List<ClassTeacher> getData(){
        School school = JAXB.unmarshal(PATH.toString(), School.class);
        List<DummyTeacher> dummyTeachers = school.getTeacherList();
        List<ClassTeacher> classTeachers = new ArrayList<>();

        dummyTeachers.forEach(teacher -> {
            Room room;
            Classname classname;
            ClassTeacher classTeacher;
            if(teacher.getRaum().charAt(0) == '1'){
                room = new Room(
                        teacher.getRaum(),
                        Room.Floor.FIRST
                );
            }else{
                room = new Room(
                        teacher.getRaum(),
                        Room.Floor.GROUND
                );
            }

            classname = new Classname(
                    teacher.getClassName(),
                    Integer.parseInt(teacher.getClassName().charAt(0) + ""),
                    teacher.getAnzSchüler()
            );

            classTeacher = new ClassTeacher(
                    teacher.getKuerzel(),
                    teacher.getFirstname(),
                    teacher.getLastname(),
                    teacher.getTitel()
            );

            room.setClassname(classname);
            classname.setRoom(room);

            classname.setClassTeacher(classTeacher);
            classTeacher.setClassname(classname);

            classTeachers.add(classTeacher);
        });

        return classTeachers;
    }
}
