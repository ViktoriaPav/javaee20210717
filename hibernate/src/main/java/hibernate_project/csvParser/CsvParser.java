package hibernate_project.csvParser;

import hibernate_project.entity.Address;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface CsvParser {

    public File unzipFile(String fileZip, String nameCSVFile) throws IOException;

    public ArrayList<Address> parserCSVFiles(File file) throws IOException;

}
