package at.kaindorf.utils;

import at.kaindorf.beans.Country;
import at.kaindorf.beans.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    //Variables
    private static final Path XML_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "customers.xml");
    private static final Path JSON_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +"main" + File.separator + "resources" + File.separator + "customers.xml");


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

        return null;
    }


    public static void main(String[] args) {
        importJSON();
    }
}

