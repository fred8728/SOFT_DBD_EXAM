package com.example.spring.controller;

import com.example.spring.postgress.Facade.OrderFacade;
import com.example.spring.postgress.Model.Customer;
import com.example.spring.postgress.Model.Order;
import com.example.spring.postgress.Model.OrderDetails;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class OrderController implements ErrorController {

    OrderFacade orderFacade;

    public OrderController() throws SQLException {

        orderFacade = new OrderFacade();
    }

    //Tested and works
    // http://localhost:8080/allOrders
    @GetMapping("allOrders")
    public ArrayList<String> getOrders() throws SQLException {
        return orderFacade.getOrders();
    }

    //Tested and works
    // http://localhost:8080/orderDetails/someid
    @GetMapping("orderDetails/{id}")
    public ArrayList<OrderDetails> getOrderDetails(@PathVariable int id) throws SQLException {
        return orderFacade.getOrderDetails(id);
    }


    // Needs to be tested
    @DeleteMapping("deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id) throws SQLException {
        orderFacade.deleteOrder(id);
    }

    // Needs to be tested
    @PostMapping("order/add")
    public void addOrder(Order order, ArrayList<OrderDetails> details){
        try{
            orderFacade.addOrder(order, details);
        }catch (Exception ex){
            ex.getMessage();
        }
    }

}
