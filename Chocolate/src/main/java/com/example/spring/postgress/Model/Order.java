package com.example.spring.postgress.Model;

public class Order {

    private int id;
    private int amount;
    private int price;
    private int customer_id;

    public Order(int id, int amount, int price, int customer_id) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public int getPrice() {
        return price;
    }
    public int getCustomer_id() {
        return customer_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", price=" + price +
                ", customer_id=" + customer_id +
                '}';
    }
}
