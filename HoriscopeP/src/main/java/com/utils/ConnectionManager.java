package com.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager {

    public static ConnectionManager connManager;
    public static Connection connection;

    //private constructor so it can only be instantiated in itself
    private ConnectionManager() {}

    //hold and host the connection
    private ConnectionManager getConnManager() {
        //if it has been instantiated return it do not create a new one

        if (connManager == null) {
            connManager = new ConnectionManager();
        }
        return connManager;
    }

    // establish connection
    public static Connection getConnection() {
        // if the connection has already been made, meaning we've seen this method before and it's checked it
        // and we made it null we then connect it to something
        if (connection == null) {
            connection = connect();
        }
        return connection;
    }

    public static Connection connect() {

        try {
            Properties props = new Properties();
            FileReader fileReader = new FileReader("/Users/Tyler's Work/Documents/IntelliJ/HoriscopeP" + "/src/main/resources/jdbc.properties");

            props.load(fileReader);

            String connectionURL;

            StringBuilder sb = new StringBuilder();

            sb.append("jdbc:postgresql://");
            sb.append(props.get("hostname"));
            sb.append(":");
            sb.append(props.get("port"));
            sb.append("/");
            sb.append(props.get("database"));

            connectionURL = sb.toString();

            String user = String.valueOf(props.getProperty("user"));
            String password = String.valueOf(props.getProperty("password"));

            System.out.println(connectionURL);
            //this fixed the 500 Internal error we received
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionURL, user, password);

            System.out.println(connection.getClientInfo().toString() + " - This is to confirm the connection was made");
            // multiple exceptions can be thrown so we use Exception e
        } catch (Exception e) {
            System.out.println("Error in the connection: " + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        ConnectionManager.getConnection();
    }
}