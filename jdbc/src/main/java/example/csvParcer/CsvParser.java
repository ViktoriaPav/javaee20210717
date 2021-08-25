package example.csvParcer;

import example.intity.Address;

import java.io.*;
import java.util.ArrayList;

public interface CsvParser {

    public File unzipFile(String fileZip, String nameCSVFile) throws IOException;

    public ArrayList<Address> ParserCSVFiles(File file) throws IOException;

}
