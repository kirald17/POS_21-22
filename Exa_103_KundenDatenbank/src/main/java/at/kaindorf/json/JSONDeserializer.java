package at.kaindorf.json;


import at.kaindorf.beans.Address;
import at.kaindorf.beans.Country;
import at.kaindorf.beans.Customer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;


public class JSONDeserializer extends StdDeserializer<Customer> {

    //Variables
    public JsonNode node;
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
    private static HashSet<Country> countrySet = new HashSet<>();
    private static HashSet<Address> addressSet = new HashSet<>();

    public JSONDeserializer() {
        super(Customer.class);
    }

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException {
        node = dc.readValue(jsonParser, JsonNode.class);
        //Create Country
        Country country = new Country(
                node.get("country").asText(),
                node.get("country_code").asText()
        );
        countrySet.add(country);
        // Element aus dem Set holen
        // addCountry ist eigentlich nur eine Referenz auf
        Country addCountry = countrySet.stream().filter(c -> c.equals(country)).findFirst().get();

        //Create Address
        Address address = new Address(
                node.get("streetname").asText(),
                node.get("streetnumber").asInt(),
                node.get("postal_code").asText(),
                node.get("city").asText(),
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
                node.get("firstname").asText(),
                node.get("lastname").asText(),
                node.get("gender").asText().charAt(0),
                node.get("active").asBoolean(),
                node.get("email").asText(),
                LocalDate.parse(node.get("since").asText(), DTF),
                addAddress
        );
        //Customer zur Adresse hinzufügen
        addAddress.getCustomerList().add(customer);

        return customer;
    }
}
