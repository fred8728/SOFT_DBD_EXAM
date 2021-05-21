package Facade;

import Model.Customer;
import Model.Order;
import Model.OrderDetails;

import javax.swing.plaf.nimbus.State;
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

    public static void main(String[] args) throws SQLException {
        OrderFacade orderFacade = new OrderFacade();
        for(String o : orderFacade.getOrders()){
            System.out.println(o);
        }
        Order order = new Order(0,2, 700, "received order", 1);
        ArrayList<OrderDetails> items = new ArrayList<>();
        OrderDetails o1 = new OrderDetails(1,100,1);
        OrderDetails o2 = new OrderDetails(2,200,2);
        OrderDetails o3 = new OrderDetails(3,200,1);
        items.add(o1);
        items.add(o2);
        items.add(o3);
        orderFacade.addOrder(order, items);
        for(OrderDetails o : orderFacade.getOrderDetails(1)){
            System.out.println(o);
        }

        //orderFacade.deleteOrder(1);
    }

    public void addOrder(Order order, ArrayList<OrderDetails> details) throws SQLException {
        try {
            String query1 = "INSERT INTO orders (amount, price, customer_id) VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, order.getAmount());
            stmt.setInt(2, order.getPrice());
            stmt.setInt(3, order.getCustomer_id());
            stmt.execute();

            ResultSet result = stmt.getGeneratedKeys();
            int generatedKey = 0;
            if (result.next()) {
                generatedKey = result.getInt(1);
            }
            details = new ArrayList<>();
            for(OrderDetails item : details){
                String query2 = "INSERT INTO order_details (item_id, price, amount, order_id) VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query2);
                ps.setInt(1, item.getItem_id());
                ps.setInt(2, item.getPrice());
                ps.setInt(3, item.getAmount());
                ps.setInt(4, generatedKey);
                ps.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn.close();
        }
    }

}
