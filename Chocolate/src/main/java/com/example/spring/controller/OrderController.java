package com.example.spring.controller;

import com.example.spring.postgress.Facade.OrderFacade;
import com.example.spring.postgress.Model.AddOrder;
import com.example.spring.postgress.Model.Customer;
import com.example.spring.postgress.Model.Order;
import com.example.spring.postgress.Model.OrderDetails;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class OrderController{

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


    // Tested and works
    // http://localhost:8080/deleteOrder/someid
    @DeleteMapping("deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id) throws SQLException {
        try{
            orderFacade.deleteOrder(id);
        }catch (Exception ex){
            ex.getMessage();
        }
    }

    // Tested and works
    // Add parameters to body - remember object + list
    @PostMapping("order/add")
    public void addOrder(@RequestBody AddOrder order){
        try{
            orderFacade.addOrder(order.getOrder(), order.getDetails());
        }catch (Exception ex){
            ex.getMessage();
        }
    }
}
