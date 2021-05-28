package com.example.spring.facade;

import com.example.spring.model.Cities;
import com.example.spring.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CustomerFacade {

    private static String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=chocolate";
    private Connection conn;

    public CustomerFacade() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user","softdb");
        props.setProperty("password","softdb");

        conn = DriverManager.getConnection(url,props);
    }

    public ArrayList<Customer> getCustomers () throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        String query = "SELECT * FROM allCustomers";
        PreparedStatement stmt = conn.prepareStatement(query);
        try(ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                Customer customer = new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));
                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM customer where customer_id = ?";
        Customer customer = null;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,id);
        ResultSet result = stmt.executeQuery();
        while(result.next()){
            customer = new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));
        }
        return customer;
    }

    public void addCustomer(Customer customer) throws SQLException {
        String query = "CALL insertCustomer(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getStreet());
            stmt.setInt(3, customer.getCity_id());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addCity(Cities city){
        String query = "CALL insertCity(?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, city.getCity_id());
            stmt.setString(2, city.getName());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomerBasic(int id, String name, String email, String phone) throws SQLException {
        try {
            String query = "SELECT * FROM customer WHERE customer_id = ? ";
            Customer customer = null;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customer = new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));
            }
            if(customer != null){
                String update = "CALL updateCustomerBasic(?,?,?,?);";
                PreparedStatement stmt = conn.prepareStatement(update);
                stmt.setInt(1, customer.getId());
                if(name != null) stmt.setString(2,name);
                else stmt.setString(2, customer.getName());

                if(email != null) stmt.setString(3, email);
                else stmt.setString(3, customer.getEmail());

                if(phone != null) stmt.setString(4, phone);
                else stmt.setString(4, customer.getPhone());
                stmt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomerAddress(int id, String street, int city_id) throws SQLException {
        try {
            String query = "SELECT * FROM customer WHERE customer_id = ? ";
            Customer customer = null;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customer = new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));
            }
            if(customer != null){
                String update = "CALL updateCustomerAddress(?,?,?);";
                PreparedStatement st = conn.prepareStatement(update);
                st.setInt(1, customer.getId());
                if(street != null && city_id != 0){
                    st.setString(2, street);
                    st.setInt(3, city_id);
                } else {
                    st.setString(2, customer.getStreet());
                    st.setInt(3, customer.getCity_id());
                }
                st.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCustomer(int id) throws SQLException {
        try{
            String query = "CALL deleteCustomer(?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
