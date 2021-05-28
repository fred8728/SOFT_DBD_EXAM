package com.example.spring.model;

public class ShoppingCart {

    public String product_id;
    public String order_id;
    public String customer_id;
    public int price;
    public int amount;

    public ShoppingCart(String product_id, String order_id, String customer_id, int price, int amount) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "product_id=" + product_id +
                ", order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
