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

        String query = "SELECT * from customer;";
        PreparedStatement statement = conn.prepareStatement(query);
        try(ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Customer c = new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));
                customers.add(c);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM customer WHERE customer_id = ?;";
        Customer customer = null;
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            customer = new Customer(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5), result.getString(6));

        }
        return customer;
    }

    public void addCustomer(Customer customer, Cities city) throws SQLException {
        String query = "CALL insertCustomer(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getStreet());
            stmt.setInt(3, customer.getCity_id());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, city.getName());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCustomer(int id, String name, String street, int city_id, String email, String phone) throws SQLException {
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
                String update = "CALL updateCustomer(?,?,?,?,?);";
                PreparedStatement st = conn.prepareStatement(update);
                st.setInt(1, customer.getId());
                if(name != null) st.setString(2,name);
                else st.setString(2, customer.getName());

                if(street != null) st.setString(3, street);
                else st.setString(3, customer.getStreet());

                if(city_id != 0) st.setInt(4, city_id);
                else st.setInt(4, customer.getCity_id());

                if(email != null) st.setString(5, email);
                else st.setString(5, customer.getEmail());

                if(phone != null) st.setString(6, phone);
                else st.setString(6, customer.getPhone());
                st.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        CustomerFacade customerFacade = new CustomerFacade();
        //Customer c = new Customer("Tina Larsen", 1, "hello@email.dk", "23453215");
        //customerFacade.addCustomer(c)
//        System.out.println("__________________");

        Customer c = new Customer(0,"Testing test", "Vejleåen 2",4567, "12g11@email.dk", "65656565");

        Cities city = new Cities(4567, "Sukkerland");
        customerFacade.addCustomer(c, city);
        //customerFacade.addCustom(c);
        //customerFacade.updateCustomer(1, null, 0, null,"42424242");
        customerFacade.getCustomerById(1);
//        for(Customer c1 : customerFacade.getCustomers()){
//            System.out.println(c1.toString());
//        }

    }
}
