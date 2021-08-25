package example.db.connector;

import example.intity.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DBTest {
    private DB db = new DBConnector();
    private ArrayList<Address> addresses;
    private ArrayList<Address> addresses1;
    private Address address1;
    private Address address2;
    private Address address3;

    @Before
    public void setUp() throws Exception {
        address1 = new Address(30, "test_city");
        address2 = new Address(31, "test_city2");
        address3 = new Address(32, "test_city3");
        addresses = new ArrayList<>();
        addresses1 = new ArrayList<>();
        addresses.add(address1);
        addresses.add(address2);
        addresses1.add(address3);
        db.isCreateTablet("test");
        db.isInsertIntoTable("test", addresses);
    }

    @After
    public void tearDown() throws Exception {
        db.closeConnection();
    }

    @Test
    public void isSelectByIndex() {
        int index = 30;
        ArrayList<Address> resultAddresses = db.isSelectByIndex("test", index);
        assertEquals(address1, resultAddresses.get(0));
    }

    @Test
    public void isSelectByName() {
        String name = "test_city2";
        ArrayList<Address> resultAddresses = db.isSelectByName("test", name);
        assertEquals(address2, resultAddresses.get(0));
    }

    @Test
    public void isSelectAllFromTable() {
        ArrayList<Address> resultFromDB = db.isSelectAllFromTable("test");
        assertEquals(addresses, resultFromDB);
    }

    @Test
    public void isInsertIntoTable() {
        assertTrue(db.isInsertIntoTable("test", addresses1));
    }

    @Test
    public void isCreateTable() {
        boolean isCreated = db.isCreateTablet("test1");
        assertTrue(isCreated);
    }
}