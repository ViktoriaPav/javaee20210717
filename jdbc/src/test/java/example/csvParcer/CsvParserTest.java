package example.csvParcer;

import example.intity.Address;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
        expectListAddresses.add(new Address(1, "Kyiv"));
        expectListAddresses.add(new Address(2, "Kharkiv"));
        expectListAddresses.add(new Address(3, "Odesa"));
        ArrayList<Address> actualListAddress = csvParser.ParserCSVFiles(new File("src" + File.separator
                + "test" + File.separator + "resources" + File.separator + "cities1.csv"));
        assertEquals(expectListAddresses, actualListAddress);

    }
}