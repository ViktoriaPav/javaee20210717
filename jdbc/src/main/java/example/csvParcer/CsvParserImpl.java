package example.csvParcer;

import au.com.bytecode.opencsv.CSVReader;
import example.intity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CsvParserImpl implements CsvParser {
    private static final Logger logger = LoggerFactory.getLogger(CsvParserImpl.class);

    public File unzipFile(String fileZip, String nameCSVFile) throws IOException {
        File destDir = new File("jdbc" + File.separator + "src" + File.separator + "main" + File.separator + "resources");
        File newFile = new File("");
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            if (zipEntry.getName().equals(nameCSVFile)) {
                newFile = newFile(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }
                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        logger.debug("Method: UnzipFile. Text " + nameCSVFile + " was created");
        return newFile;
    }

    public ArrayList<Address> ParserCSVFiles(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        CSVReader reader = new CSVReader(fileReader, '|', '"', 1);
        String[] nextLine;
        ArrayList<Address> addresses = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            addresses.add(new Address(Integer.valueOf(nextLine[0]), nextLine[1]));
        }
        logger.debug("Method: ParserCSVFile. Address ArrayList was created from " + file.getName());
        return addresses;
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }
}






