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
import java.util.Locale;


public class JSONDeserializer extends StdDeserializer<Customer> {

    //Variables
    public JsonNode node;
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    public JSONDeserializer() {
        super(Customer.class);
    }

    @Override
    public Customer deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException {
        //TODO: Create a customer object
        node = dc.readValue(jsonParser, JsonNode.class);
        //Create Address
        Address address = new Address(
                node.get("streetname").asText(),
                node.get("streetnumber").asInt(),
                node.get("postal_code").asText(),
                node.get("city").asText()
                );
        //Create Country
        Country country = new Country(
                node.get("country").asText(),
                node.get("country_code").asText()
        );
        //Create Customer
        Customer customer = new Customer(
                node.get("firstname").asText(),
                node.get("lastname").asText(),
                node.get("gender").asText().charAt(0),
                node.get("active").asBoolean(),
                node.get("email").asText(),
                LocalDate.parse(node.get("since").asText(), DTF)
        );

        return customer;
    }
}
