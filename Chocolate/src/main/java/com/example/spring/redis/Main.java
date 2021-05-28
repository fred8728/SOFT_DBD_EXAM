package com.example.spring.redis;


import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Jedis jedis;

    public Main(Jedis jedis) {
        this.jedis = jedis;
    }

    public static String username = "username1";

    public static void main(String[] args) {

        Setups st = new Setups();
        st.setupCon();
        //st.beforeEachCon();

        ShoppingCartQueries query = new ShoppingCartQueries(jedis);

        ShoppingCart sc = new ShoppingCart("p1","1","5",10,10);
        ShoppingCart sc1 = new ShoppingCart("p2","1","5",7,6);
        ShoppingCart sc2 = new ShoppingCart("p3","1","5",2,200);
        ShoppingCart sc3 = new ShoppingCart("p4","1","5",8,88);

        Map<String,String> mapp = new HashMap<String, String>();
        mapp.put("productid3","orderid3:userid3:price3:status3:amount3");
        mapp.put("productid4","orderid4:userid4:price4:status4:amount4");

        System.out.println(jedis.ping());

        query.addSingle(username,sc);
        query.addSingle(username,sc1);
        query.addSingle(username,sc2);
        //query.removeSingle(username,sc2.productid);
        query.addSingle(username,sc3);
        //query.updateAmount(username, sc2,true);
        query.addAllFromSing(username);
        //query.removeAllFromSing(username);
        var t = query.getUserShoppingcart(username,sc);

        for (String[] e : t){
            System.out.println(Arrays.toString(e));
            //do stuff here
        }


    }



}
