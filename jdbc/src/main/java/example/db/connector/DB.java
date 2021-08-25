package example.db.connector;

import example.intity.Address;

import java.sql.Connection;
import java.util.ArrayList;

public interface DB {

    public ArrayList<Address> isSelectByIndex(String tableName, int index);

    public ArrayList<Address> isSelectByName(String tableName, String name);

    public ArrayList<Address> isSelectAllFromTable(String tableName);

    public boolean isInsertIntoTable(String tableName, ArrayList<Address> addresses);

    public boolean isCreateTablet(String tabletName);

    public Connection createConnection();

    public boolean closeConnection();

    public void printResult(ArrayList<Address> addresses);

}
