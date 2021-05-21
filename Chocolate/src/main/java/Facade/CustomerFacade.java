package Facade;

import Model.Cities;
import Model.Customer;

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
        PreparedStatement statement = conn.prepareStatement(query);
        try(ResultSet result = statement.executeQuery()) {
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
        String query = "CALL deleteCustomer(?);";
        Customer customer = null;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,id);
        stmt.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        CustomerFacade customerFacade = new CustomerFacade();
        //Customer c = new Customer("Tina Larsen", 1, "hello@email.dk", "23453215");
        //customerFacade.addCustomer(c)
//        System.out.println("__________________");

//        Customer c = new Customer(0,"Testing test", "Vejleåen 2",4567, "12g11@email.dk", "65656565");
//
//        Cities city = new Cities(4567, "Sukkerland");
//        customerFacade.addCustomer(c, city);
        //customerFacade.updateCustomerBasic(2, null, "newemail@email.dk", null);
        //customerFacade.updateCustomerAddress(2, "Vejlegården 4", 2635);
        //customerFacade.addCustom(c);
        //customerFacade.updateCustomer(1, null, 0, null,"42424242");
        //customerFacade.getCustomerById(1);
//        Customer c3 = new Customer(0, "Test person1", "Testvej 1", 2670, "testemail2@test.dk", "54343434");
//        Customer c2 = new Customer(0, "Test person2", "Testvej 2", 2670, "testemail1@test.dk", "54333434");
//        customerFacade.addCustomer(c3);
//        customerFacade.addCustomer(c2);
//        for(Customer c1 : customerFacade.getCustomers()){
//            System.out.println(c1.toString());
//        }
        //Cities ci = new Cities(1234, "CityTest");
        //customerFacade.addCity(ci);
        System.out.println(customerFacade.getCustomerById(3));
        //customerFacade.deleteCustomer(5);
    }
}
