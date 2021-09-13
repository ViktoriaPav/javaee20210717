package hibernate_project.logic;

import hibernate_project.csvParser.CsvParserImpl;
import hibernate_project.db.HibernateUTIL;
import hibernate_project.entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainLogic {
    private HibernateUTIL hibernateUTIL = new HibernateUTIL();

    public void preparedInfo() throws IOException {
//        Select data from zip file
        CsvParserImpl parser = new CsvParserImpl();
        ArrayList<Address> cities;
        ArrayList<Address> countries;
        ArrayList<Address> regions;
        String filePath = "import.zip";

        cities = parser.parserCSVFiles(parser.unzipFile(filePath, "cities.csv"));
        countries = parser.parserCSVFiles(parser.unzipFile(filePath, "country.csv"));
        regions = parser.parserCSVFiles(parser.unzipFile(filePath, "regions.csv"));

        //Save to DB:
        hibernateUTIL.saveAddressesToDB(cities);
        hibernateUTIL.saveAddressesToDB(countries);
        hibernateUTIL.saveAddressesToDB(regions);

    }

    private void printResult(List<? extends Address> addresses) {
        for (Address st : addresses) {
            System.out.println(String.format("City [index=%d, name=%s]\n",
                    st.getIndex(), st.getName()));
        }
    }

    public void mainLogic() {
        try {
            for (int x = 0; x < 15; x++) {
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
                        printResult(hibernateUTIL.selectAllFromCountriesTable());
                    } else if (i == 2) {
                        System.out.println("Please entered Index of the Country");
                        i = scanner.nextInt();
                        printResult(hibernateUTIL.selectByIndexFromCountry(i));
                    } else if (i == 3) {
                        System.out.println("Please entered the Name");
                        s = scanner1.nextLine();
                        printResult(hibernateUTIL.selectByNameFromCountry(s));
                    } else if (i == 4) {
                        System.out.println("Program was stopped!");
                        System.out.println("***");
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
                        printResult(hibernateUTIL.selectAllFromCityTable());
                    } else if (i == 2) {
                        System.out.println("Please entered Index of the Cities");
                        i = scanner.nextInt();
                        printResult(hibernateUTIL.selectByIndexFromCity(i));
                    } else if (i == 3) {
                        System.out.println("Please entered the Name");
                        s = scanner1.nextLine();
                        printResult(hibernateUTIL.selectByNameFromCity(s));
                    } else if (i == 4) {
                        System.out.println("Program was stopped!");
                        System.out.println("***");
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
                        printResult(hibernateUTIL.selectAllFromRegionsTable());
                    } else if (i == 2) {
                        System.out.println("Please entered Index of the Regions");
                        i = scanner.nextInt();
                        printResult(hibernateUTIL.selectByIndexFromRegion(i));
                    } else if (i == 3) {
                        System.out.println("Please entered the Name");
                        s = scanner1.nextLine();
                        printResult(hibernateUTIL.selectByNameFromRegion(s));
                    } else if (i == 4) {
                        System.out.println("Program was stopped!");
                        System.out.println("***");
                        break;
                    } else if (i == 5) {
                        continue;
                    }
                } else if (i == 4) {
                    System.out.println("Program was stopped!");
                    System.out.println("***");
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
