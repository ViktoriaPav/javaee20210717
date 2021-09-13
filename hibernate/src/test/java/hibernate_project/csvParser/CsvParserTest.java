package hibernate_project.csvParser;

import hibernate_project.entity.Address;
import hibernate_project.entity.City;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CsvParserTest {

    private CsvParserImpl csvParser = new CsvParserImpl();

    @Test
    public void unzipFile() throws IOException {
        File file = csvParser.unzipFile("src" + File.separator + "test" + File.separator + "resources" + File.separator + "import.zip", "cities.csv");
        assertEquals("cities.csv", file.getName());
    }

    @Test
    public void parserCSVFiles() throws IOException {
        ArrayList<Address> expectListAddresses = new ArrayList<>();
        expectListAddresses.add(new City(1, "Kyiv"));
        expectListAddresses.add(new City(2, "Kharkiv"));
        expectListAddresses.add(new City(3, "Odesa"));
        ArrayList<Address> actualListAddress = csvParser.parserCSVFiles(new File("src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "cities1.csv"));
        assertEquals(expectListAddresses, actualListAddress);

    }
}