package com.example.spring.facade;

import com.example.spring.model.Order;
import com.example.spring.model.OrderDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class OrderFacade {

    private static String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=chocolate";
    private Connection conn;

    public OrderFacade() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user","softdb");
        props.setProperty("password","softdb");

        conn = DriverManager.getConnection(url,props);
    }

    public ArrayList<String> getOrders () throws SQLException {
        ArrayList<String> orders = new ArrayList<>();

        String query = "SELECT * FROM allOrders";
        PreparedStatement stmt = conn.prepareStatement(query);
        try(ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                String order =  "ID: " + result.getInt(1)
                                + ", Customer name: " + result.getString(2)
                                + ", Customer email: " + result.getString(3)
                                + ", Customer phone: "+ result.getString(4)
                                + ", Amount: "+ result.getInt(5)
                                + ", Price: "+ result.getInt(6)
                                + ", Status: "+ result.getString(7);
                orders.add(order);
            }
        }
        return orders;
    }

    public ArrayList<OrderDetails> getOrderDetails (int id) throws SQLException {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();

        String query = "SELECT * FROM order_details where order_id = ?;";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,id);
        try(ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                OrderDetails details = new OrderDetails(result.getInt(1),result.getInt(2),result.getInt(3));
                orderDetails.add(details);
            }
        }
        return orderDetails;
    }

    public void deleteOrder(int id) throws SQLException {
        try{
            String query = "CALL deleteOrder(?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addOrder(Order order, ArrayList<OrderDetails> details) throws SQLException {
        try {
            String q = "INSERT INTO orders (amount, price, customer_id) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(q) ;
            stmt.setInt(1, order.getAmount());
            stmt.setInt(2, order.getPrice());
            stmt.setInt(3, order.getCustomer_id());
            stmt.execute();

            int maxOrderId = 0;
            String query2 = "SELECT * FROM MaxOrderNo";
            PreparedStatement stmt1 = conn.prepareStatement(query2);
            ResultSet result = stmt1.executeQuery();
            while (result.next()){
                maxOrderId = result.getInt(1);
            }
            for (OrderDetails item : details){
                String q2 = "INSERT INTO order_details ( item_id, price, amount, order_id) VALUES (?,?,?,?);";
                PreparedStatement stmt2 = conn.prepareStatement(q2);
                stmt2.setInt(1, item.getItem_id());
                stmt2.setInt(2, item.getPrice());
                stmt2.setInt(3, item.getAmount());
                stmt2.setInt(4, maxOrderId);
                stmt2.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
