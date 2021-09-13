package hibernate_project.db;

import hibernate_project.entity.Address;
import hibernate_project.entity.City;
import hibernate_project.entity.Country;
import hibernate_project.entity.Region;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HibernateUTILTest {
    private static HibernateUTIL hibernateUTIL = new HibernateUTIL();
    private static ArrayList<Address> citiesAddress;
    private static ArrayList<Address> countiesAddress;
    private static ArrayList<Address> regionsAddress;
    private static City city1;
    private static City city2;
    private static Country country1;
    private static Country country2;
    private static Country country3;
    private static Region region1;
    private static Region region2;

    @BeforeClass
    public static void setUp() {
        citiesAddress = new ArrayList<>();
        countiesAddress = new ArrayList<>();
        regionsAddress = new ArrayList<>();

        city1 = new City(50, "Test1");
        city2 = new City(51, "Test2");
        country1 = new Country(60, "TestC1");
        country2 = new Country(61, "TestC2");
        country3 = new Country(62, "TestC3");
        region1 = new Region(70, "TestR1");
        region2 = new Region(71, "TestR2");

        countiesAddress.add(country1);
        countiesAddress.add(country2);

        citiesAddress.add(city1);
        citiesAddress.add(city2);

        regionsAddress.add(region1);
        regionsAddress.add(region2);

        hibernateUTIL.saveAddressesToDB(countiesAddress);
        hibernateUTIL.saveAddressesToDB(citiesAddress);
        hibernateUTIL.saveAddressesToDB(regionsAddress);
    }

    @Test
    public void getSessionFactory() {
        HibernateUTIL.getSessionFactory();
        assertNotNull(HibernateUTIL.getSessionFactory());
    }


    @Test
    public void saveAddressesToDB() {
        countiesAddress.add(country3);
        hibernateUTIL.saveAddressesToDB(countiesAddress);
        List<Country> countries = hibernateUTIL.selectByIndexFromCountry(62);
        assertEquals(country3, countries.get(0));
    }

    @Test
    public void selectAllFromCityTable() {
        List<City> cities = hibernateUTIL.selectAllFromCityTable();
        assertEquals(city1, cities.get(0));
        assertEquals(city2, cities.get(1));
    }

    @Test
    public void selectAllFromCountriesTable() {
        List<Country> countries = hibernateUTIL.selectAllFromCountriesTable();
        assertEquals(country1, countries.get(0));
        assertEquals(country2, countries.get(1));
    }

    @Test
    public void selectAllFromRegionsTable() {
        List<Region> regions = hibernateUTIL.selectAllFromRegionsTable();
        assertEquals(region1, regions.get(0));
        assertEquals(region2, regions.get(1));
    }

    @Test
    public void selectByIndexFromCity() {
        List<City> cityList = hibernateUTIL.selectByIndexFromCity(50);
        assertEquals(city1, cityList.get(0));
    }

    @Test
    public void selectByNameFromCity() {
        List<City> cityList = hibernateUTIL.selectByNameFromCity("Test1");
        assertEquals(city1, cityList.get(0));
    }

    @Test
    public void selectByIndexFromCountry() {
        List<Country> countryList = hibernateUTIL.selectByIndexFromCountry(60);
        assertEquals(country1, countryList.get(0));
    }

    @Test
    public void selectByNameFromCountry() {
        List<Country> countryList = hibernateUTIL.selectByNameFromCountry("TestC2");
        assertEquals(country2, countryList.get(0));
    }

    @Test
    public void selectByIndexFromRegion() {
        List<Region> regionList1 = hibernateUTIL.selectByIndexFromRegion(70);
        Region region = regionList1.get(0);
        assertEquals(region1, region);
    }

    @Test
    public void selectByNameFromRegion() {
        List<Region> regionList = hibernateUTIL.selectByNameFromRegion("TestR2");
        assertEquals(region2, regionList.get(0));
    }
}