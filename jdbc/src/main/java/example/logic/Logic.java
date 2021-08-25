package example.logic;

import example.csvParcer.CsvParserImpl;
import example.db.connector.DBConnector;
import example.intity.Address;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    private String cityTableName = "cities";
    private String countryTableName = "countries";
    private String regionTAbleName = "regions";

    private DBConnector dbConnector = new DBConnector();

    public void preparedDB() throws IOException {
//        Select data from zip file
        CsvParserImpl parser = new CsvParserImpl();
        ArrayList<Address> city;
        ArrayList<Address> country;
        ArrayList<Address> regions;
        String filePath = "import.zip";

        city = parser.ParserCSVFiles(parser.unzipFile(filePath, "cities.csv"));
        country = parser.ParserCSVFiles(parser.unzipFile(filePath, "country.csv"));
        regions = parser.ParserCSVFiles(parser.unzipFile(filePath, "regions.csv"));

        String cityTableName = "cities";
        String countryTableName = "countries";
        String regionTAbleName = "regions";

//        Create and insert data to DB
        dbConnector.isCreateTablet(cityTableName);
        dbConnector.isInsertIntoTable(cityTableName, city);
        dbConnector.isCreateTablet(countryTableName);
        dbConnector.isInsertIntoTable(countryTableName, country);
        dbConnector.isCreateTablet(regionTAbleName);
        dbConnector.isInsertIntoTable(regionTAbleName, regions);
    }

    public void mainLogic() {
        try {
            for (int x = 0; x < 5; x++) {
                Scanner scanner = new Scanner(System.in);
                Scanner scanner1 = new Scanner(System.in);
                String s;

                System.out.println("\n *");
                System.out.println("What do you want to find, please entered the Number: \n" +
                        "1. Country, \n" +
                        "2. Cities, \n" +
                        "3. Regions, \n" +
                        "4. If you want finished dialog.");
                int i = scanner.nextInt();
                if (i == 1) {
                    System.out.println("Please entered the Number: \n" +
                            "1. if you want find all Countries, \n" +
                            "2. if you want find by the Index,\n" +
                            "3. if you want find by the Name\n" +
                            "4. if you want stopped dialog\n" +
                            "5. if you want back to previous menu");
                    i = scanner.nextInt();
                    if (i == 1) {
                        dbConnector.printResult(dbConnector.isSelectAllFromTable(countryTableName));
                    } else if (i == 2) {
                        System.out.println("Please entered Index of the Country");
                        i = scanner.nextInt();
                        dbConnector.printResult(dbConnector.isSelectByIndex(countryTableName, i));
                    } else if (i == 3) {
                        System.out.println("Please entered the Name");
                        s = scanner1.nextLine();
                        dbConnector.printResult(dbConnector.isSelectByName(countryTableName, s));
                    } else if (i == 4) {
                        System.out.println("Program was stopped!");
                        System.out.println("***");
                        dbConnector.closeConnection();
                        break;
                    } else if (i == 5) {
                        continue;
                    }
                } else if (i == 2) {
                    System.out.println("Please entered the Number: \n" +
                            "1. if you want find all Cities, \n" +
                            "2. if you want find by the Index,\n" +
                            "3. if you want find by the Name\n" +
                            "4. if you want stopped dialog\n" +
                            "5. if you want back to previous menu");
                    i = scanner.nextInt();
                    if (i == 1) {
                        dbConnector.printResult(dbConnector.isSelectAllFromTable(cityTableName));
                    } else if (i == 2) {
                        System.out.println("Please entered Index of the Cities");
                        i = scanner.nextInt();
                        dbConnector.printResult(dbConnector.isSelectByIndex(cityTableName, i));
                    } else if (i == 3) {
                        System.out.println("Please entered the Name");
                        s = scanner1.nextLine();
                        dbConnector.printResult(dbConnector.isSelectByName(cityTableName, s));
                    } else if (i == 4) {
                        System.out.println("Program was stopped!");
                        System.out.println("***");
                        dbConnector.closeConnection();
                        break;
                    } else if (i == 5) {
                        continue;
                    }
                } else if (i == 3) {
                    System.out.println("Please entered the Number: \n" +
                            "1. if you want find all Regions, \n" +
                            "2. if you want find by the Index,\n" +
                            "3. if you want find by the Name\n" +
                            "4. if you want stopped dialog\n" +
                            "5. if you want back to previous menu");
                    i = scanner.nextInt();
                    if (i == 1) {
                        dbConnector.printResult(dbConnector.isSelectAllFromTable(regionTAbleName));
                    } else if (i == 2) {
                        System.out.println("Please entered Index of the Regions");
                        i = scanner.nextInt();
                        dbConnector.printResult(dbConnector.isSelectByIndex(regionTAbleName, i));
                    } else if (i == 3) {
                        System.out.println("Please entered the Name");
                        s = scanner1.nextLine();
                        dbConnector.printResult(dbConnector.isSelectByName(regionTAbleName, s));
                    } else if (i == 4) {
                        System.out.println("Program was stopped!");
                        System.out.println("***");
                        dbConnector.closeConnection();
                        break;
                    } else if (i == 5) {
                        continue;
                    }
                } else if (i == 4) {
                    System.out.println("Program was stopped!");
                    System.out.println("***");
                    dbConnector.closeConnection();
                    break;
                } else {
                    System.out.println("You entered wrong Number, please try again");
                }
                System.out.println("***");
            }
        } catch (Exception e) {
            System.out.println("You Entered wrong value!");
            mainLogic();
        }
    }
}
