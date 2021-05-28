package com.example.spring.redis;

import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

public class CartFacade {

    private static Jedis jedis;
    private  static String host = "localhost";
    Map<String,String> allItems;

    public CartFacade() {
        jedis = new Jedis(host);
        allItems = new HashMap<>();
    }

    public Map<String, String> getShoppingCartById(String customer_id) {
        return jedis.hgetAll("shoppingcart:" + customer_id);
    }

    public void addShoppingCart(String customer_id){
        jedis.hmset("shoppingcart:"+customer_id,allItems);
    }

    public void addProductToCart(ShoppingCart cart){
        allItems.put("product_id:" + cart.product_id,"order_id:" + cart.order_id+":customer_id:"+cart.customer_id +":price:"+cart.price+":amount:"+cart.amount);
    }

    public void deleteShoppingCart(String customer_id){
        jedis.del("shoppingcart:"+customer_id);
    }

    public static void main(String[] args) {
        CartFacade facade = new CartFacade();

        ShoppingCart sc = new ShoppingCart("p5","1","5",10,10);
        ShoppingCart sc1 = new ShoppingCart("p6","1","5",7,6);
        ShoppingCart sc2 = new ShoppingCart("p7","1","5",2,200);
        ShoppingCart sc3 = new ShoppingCart("p8","1","5",8,88);
        facade.addProductToCart(sc);
        facade.addProductToCart(sc1);
        facade.addProductToCart(sc2);
        facade.addProductToCart(sc3);

        facade.addShoppingCart("1");
        String customer_id = "1";
        var list = facade.getShoppingCartById(customer_id);
        for (Map.Entry<String, String> book : list.entrySet()) {
            System.out.println(book.getKey() + book.getValue());
        }

        //facade.deleteShoppingCart(customer_id);
        System.out.println("_________________");

    }
}