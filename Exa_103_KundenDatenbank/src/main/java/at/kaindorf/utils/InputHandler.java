package at.kaindorf.utils;

import at.kaindorf.beans.Country;
import at.kaindorf.beans.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    //Variables
    private static final Path XML_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "customers.xml");
    private static final Path JSON_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +"main" + File.separator + "resources" + File.separator + "customers.json");


    //TODO: Import data from XML
    /*
        importXML(): Einlesen der XML-Datei
     */
    public static List<Customer> importXML(){

        return null;
    }

    //TODO: Import data from JSON
    /*
        importJSON(): Einlesen der JSON-Datei
     */
    public static List<Customer> importJSON(){
        ObjectMapper mapper = new ObjectMapper();
        List<Customer> customers = new ArrayList<>();
        try {
            customers = mapper.readValue(JSON_PATH.toFile(), new TypeReference<List<Customer>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        customers.stream().forEach(System.out::println);
        return customers;
    }


    public static void main(String[] args) {
        importJSON();
    }
}

