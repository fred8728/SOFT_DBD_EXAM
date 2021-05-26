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


}
