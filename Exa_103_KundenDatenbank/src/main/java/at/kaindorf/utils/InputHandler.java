package at.kaindorf.utils;

import at.kaindorf.beans.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InputHandler {
    //Variables
    private static final Path XML_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "customers.xml");
    private static final Path JSON_PATH = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator +"main" + File.separator + "resources" + File.separator + "customers.json");

    private static HashSet<Country> countrySet = new HashSet<>();
    private static HashSet<Address> addressSet = new HashSet<>();

    /*
        importXML(): Einlesen der XML-Datei
     */
    public static List<Customer> importXML(){
        List<Customer> customers = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(XMLDummyList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLDummyList xmlDummyList = (XMLDummyList) unmarshaller.unmarshal(XML_PATH.toFile());
            for (XMLDummy  dummy : xmlDummyList.getDummyList()) {
                //Create Country
                Country country = new Country(
                        dummy.getCountry(),
                        dummy.getCountry_code()
                );
                countrySet.add(country);
                // Element aus dem Set holen
                // addCountry ist eigentlich nur eine Referenz auf
                Country addCountry = countrySet.stream().filter(c -> c.equals(country)).findFirst().get();

                //Create Address
                Address address = new Address(
                        dummy.getStreetname(),
                        Integer.parseInt(dummy.getStreetnumber()),
                        dummy.getPostal_code(),
                        dummy.getCity(),
                        addCountry
                );
                addressSet.add(address);
                // Element aus dem Set holen
                // addCountry ist eigentlich nur eine Referenz auf
                // Adresse zum Country hinzufügen
                addCountry.getAddressList().add(address);
                Address addAddress = addressSet.stream().filter(a -> a.equals(address)).findFirst().get();

                //Create Customer
                Customer customer = new Customer(
                        dummy.getFirstname(),
                        dummy.getLastname(),
                        dummy.getGender().charAt(0),
                        dummy.isActive(),
                        dummy.getEmail(),
                        dummy.getSince(),
                        addAddress
                );
                //Customer zur Adresse hinzufügen
                addAddress.getCustomerList().add(customer);
                //Customer zur Liste hinzufügen
                customers.add(customer);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //customers.stream().forEach(System.out::println);
        return customers;
    }

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
        //customers.stream().forEach(System.out::println);
        return customers;
    }

    // public static void main(String[] args) {}
}

