package com.example.spring.redis;

import redis.clients.jedis.Jedis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFacade {

    private static Jedis jedis;
    Map<String,String> allItems;
    Setups setup;

    public CartFacade() {
        jedis = new Jedis();
        new HashMap<Integer, String>();
    }

//    public ArrayList<String[]> getUserShoppingcart(String username, ShoppingCart cart){
//        ArrayList<String[]> res = new ArrayList<String[]>();
//        var ty = jedis.hgetAll("shoppingcart:"+username);
//        for (String i : ty.values()){
//            String [] vals = i.split(":",5);
//            res.add(vals);
//        }
//        return res;
//    }
//
//    public void addMultiple(String username, Map<String, String> value){
//        //mapp.put("productid","orderid:userid:price:status:amount");
//        //mapp.put("productid1","orderid1:userid1:price1:status1:amount1");
//        jedis.hmset("shoppingcart:"+username,value);
//    }

    public Map<String, String> getShoppingCartById(int customer_id){
        var cart = jedis.hgetAll("shoppingcart:"+customer_id);
        return cart;
    }

    public String getJedis(){
        return jedis.ping();
    }

////    // Use
////    public void addAllFromSing(int customer_id){
////
////        jedis.hmset("shoppingcart:"+customer_id, allItems);
////    }
//
//    public void addItemToCart(ShoppingCart cart){
//        allItems.put(cart.product_id,cart.order_id+":"+cart.customer_id +":"+cart.price+":"+cart.amount);
//    }
//
//    public void addAllItemsToCustomer(int customer_id, ArrayList<ShoppingCart> cart){
//        //HashMap<String, Integer> languageMap = convertArrayListToHashMap(languageList);
//        jedis.hmset("shoppingcart:" + customer_id, allItems);
//    }
//
//    //Use
//    public void addSingle(int customer_id, ShoppingCart cart){
//        //Map<String,String> allMap1 = new HashMap<String, String>();
//        allItems.put(cart.product_id,cart.order_id+":"+cart.customer_id +":"+cart.price+":"+cart.status+":"+cart.amount);
//
//        //var ty = cart.orderid+":"+cart.userid+":"+cart.price+":"+cart.status+":"+cart.amount;
//        //return ty;
//    }
//
//    public void removeSingle(String username, String pId){
//        allItems.remove(pId);
//    }
//    public void removeAllFromSing(String username){
//
//        jedis.del("shoppingcart:"+username);
//    }
//    public void updateAmount(String username,ShoppingCart cart, boolean more){
//        int newamount = 0;
//        if(more){
//            newamount = cart.amount + 1;
//        } else{
//            newamount = cart.amount - 1;
//        }
//
//        allItems.put(cart.product_id,cart.order_id+":"+cart.customer_id +":"+cart.price+":"+cart.status+":"+newamount);
//    }
//
//

}
