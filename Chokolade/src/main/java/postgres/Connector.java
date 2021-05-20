package postgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Connector {

    private static String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=chocolate";
    private Connection conn;

    public Connector() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user","softdb");
        props.setProperty("password","softdb");

        conn = DriverManager.getConnection(url,props);
    }

    public ArrayList<Customer> getCustomers () throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

            String sql = "SELECT * from customer;";
            PreparedStatement statement = conn.prepareStatement(sql);
            try(ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    Customer c = new Customer(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5));
                    customers.add(c);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return customers;
    }

    public static void main(String[] args) throws SQLException {
        Connector conn = new Connector();
        for(Customer c : conn.getCustomers()){
            System.out.println(c.toString());
        }
    }
}
