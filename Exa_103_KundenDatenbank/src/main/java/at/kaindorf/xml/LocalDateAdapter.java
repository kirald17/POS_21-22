package at.kaindorf.xml;

import at.kaindorf.beans.Country;
import at.kaindorf.beans.XMLDummy;
import at.kaindorf.beans.XMLDummyList;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate > {

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, DTF);
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.format(DTF);
    }
}
