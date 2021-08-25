package example.db.connector;

import example.intity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector implements DB {
    private Connection connection = createConnection();
    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);


    public ArrayList<Address> isSelectByIndex(String tableName, int index) {
        ArrayList<Address> addresses = new ArrayList<>();
        boolean addedToAddress = false;
        try {
            String querySelectByIndex = "SELECT * FROM " + tableName + " where index = (?)";
            PreparedStatement st = null;
            logger.trace("Method: Select by index. Check connection " + connection.toString());
            st = connection.prepareStatement(querySelectByIndex);
            st.setInt(1, index);
            ResultSet result;
            result = st.executeQuery();
            while (result.next()) {
                addresses.add(new Address(result.getInt("INDEX"), result.getString("NAME")));
                addedToAddress = true;
            }
            logger.debug("Method: Select by Index. ArrayList Address was created. Result Query has value = " + addedToAddress);
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Method: Select by Index. ERROR");
        }
        return addresses;
    }

    public ArrayList<Address> isSelectByName(String tableName, String name) {
        ArrayList<Address> addresses = new ArrayList<>();
        boolean addedToAddress = false;
        try {
            String querySelectByIndex = "SELECT * FROM " + tableName + " where name like (?)";
            PreparedStatement st = null;
            logger.trace("Method: Select by Name. Check connection " + connection.toString());
            st = connection.prepareStatement(querySelectByIndex);
            st.setString(1, "%" + name + "%");

            ResultSet result;
            result = st.executeQuery();
            while (result.next()) {
                addresses.add(new Address(result.getInt("INDEX"), result.getString("NAME")));
                addedToAddress = true;
            }
            logger.debug("Method: Select by Name: Result query has value = " + addedToAddress);
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Method: Select by Name. ERROR");
        }
        return addresses;
    }

    public ArrayList<Address> isSelectAllFromTable(String tableName) {
        ArrayList<Address> addresses = new ArrayList<>();
        boolean addedToAddress = false;
        try {
            String querySelectAll = "SELECT * FROM " + tableName;
            Statement st = null;
            logger.trace("Method: selectAll. Check connection. Connection:  " + connection.toString());
            st = this.connection.createStatement();
            ResultSet result;
            result = st.executeQuery(querySelectAll);
            while (result.next()) {
                addresses.add(new Address(result.getInt("INDEX"), result.getString("NAME")));
                addedToAddress = true;
            }
            logger.debug("Method: SelectAll. ArrayList Addresses was created. Result query has value = " + addedToAddress);
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return addresses;
    }

    public boolean isInsertIntoTable(String tableName, ArrayList<Address> addresses) {
        boolean added = false;
        try {
            for (Address address : addresses) {
                String insertQuery = "insert into " + tableName + "  (index, name) values(?, ?)";
                PreparedStatement st = null;
                st = connection.prepareStatement(insertQuery);
                st.setInt(1, address.getIndex());
                st.setString(2, address.getName());
                st.execute();
                added = true;
            }
            logger.debug("Method: Insert into " + tableName + " table = " + added);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Method: InsertIntoTable. Error");
        }
        return false;
    }

    public boolean isCreateTablet(String tabletName) {
        try {
            Statement st = null;
            String query = "CREATE TABLE " + tabletName + " (INDEX int, NAME varchar(50)) ";
            st = connection.createStatement();
            st.execute(query);
            logger.debug("Method: Create " + tabletName + " table was successfully");
            return true;
        } catch (SQLException e) {
            logger.error(e.toString());
            logger.error("Method: " + tabletName + " has not been created");
        }
        return false;
    }

    public Connection createConnection() {
        try {
            Class.forName("org.h2.Driver").newInstance();
            String url = "jdbc:h2:mem:H2";
            String user = "user";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);
            logger.debug("Method: CreateConnection. Connection has been created");
            return connection;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        logger.error("Method: Create connection. Connection has not been created");
        return null;
    }

    public boolean closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.debug("Method: CloseConnection. connection has been closed");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.error("Method: CloseConnection. connection has not been closed");
        return false;
    }

    public void printResult(ArrayList<Address> addresses) {
        boolean wasPrinted = false;
        for (Address address : addresses) {
            System.out.println("INDEX: " + address.getIndex() + ", NAME: " + address.getName());
            wasPrinted = true;
        }
        logger.debug("Method: printResult. Data has been printed = " + wasPrinted);
    }
}
