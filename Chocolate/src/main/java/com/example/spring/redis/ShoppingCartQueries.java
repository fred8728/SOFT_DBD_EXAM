package com.example.spring.redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartQueries {

    private static Jedis jedis;
    Map<String,String> allMap = new HashMap<String, String>();
    //ShoppingCart sc = new ShoppingCart();

    public ShoppingCartQueries(Jedis jedis) {
        this.jedis = jedis;
    }

    public void addToCart(String username){
        jedis.hset("shoppingcart:"+username,"productid","orderid:userid:price:status:amount");
    }

    public ArrayList<String[]> getUserShoppingcart(String username, ShoppingCart cart){
        ArrayList<String[]> res = new ArrayList<String[]>();
        var ty = jedis.hgetAll("shoppingcart:"+username);
        for (String i : ty.values()){
            String [] vals = i.split(":",5);
            res.add(vals);
        }
        return res;
    }

    public void addMultiple(String username, Map<String, String> value){
        //mapp.put("productid","orderid:userid:price:status:amount");
        //mapp.put("productid1","orderid1:userid1:price1:status1:amount1");
        jedis.hmset("shoppingcart:"+username,value);
    }

    public void shoppingCartPrint(String username){
        System.out.println(jedis.hgetAll("shoppingcart:"+username));
    }

    public void addAllFromSing(String username){
        jedis.hmset("shoppingcart:"+username,allMap);
    }

    public void addSingle(String username, ShoppingCart cart){
        //Map<String,String> allMap1 = new HashMap<String, String>();
        allMap.put(cart.product_id,cart.order_id+":"+cart.customer_id +":"+cart.price+":"+cart.amount);

        //var ty = cart.orderid+":"+cart.userid+":"+cart.price+":"+cart.status+":"+cart.amount;
        //return ty;
    }

    public void removeSingle(String username, String pId){
        allMap.remove(pId);
    }
    public void removeAllFromSing(String username){

        jedis.del("shoppingcart:"+username);
    }
    public void updateAmount(String username,ShoppingCart cart, boolean more){
        int newamount = 0;
        if(more){
            newamount = cart.amount + 1;
        } else{
            newamount = cart.amount - 1;
        }

        allMap.put(cart.product_id,cart.order_id+":"+cart.customer_id +":"+cart.price+":"+newamount);
    }



}
