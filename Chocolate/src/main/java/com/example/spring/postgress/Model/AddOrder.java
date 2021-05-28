package com.example.spring.postgress.Model;

import java.util.ArrayList;

public class AddOrder {
    Order order;
    ArrayList<OrderDetails> details;

    public AddOrder(Order order, ArrayList<OrderDetails> details) {
        this.order = order;
        this.details = details;
    }

    public Order getOrder() {
        return order;
    }
    public ArrayList<OrderDetails> getDetails() {
        return details;
    }
}
