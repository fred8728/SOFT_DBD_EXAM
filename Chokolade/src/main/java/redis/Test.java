package redis;


import redis.clients.jedis.Jedis;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.utility.DockerImageName;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private static Jedis jedis;

    public Test(Jedis jedis) {
        this.jedis = jedis;
    }

    public static void main(String[] args) {
        Setups st = new Setups();
        st.setupCon();
        System.out.println("hey");
        System.out.println(jedis.ping());


        jedis.hset("shoppingcart:username","productid","orderid:userid:price:status:amount");
        jedis.hset("shoppingcart:username","productid1","orderid1:userid1:price1:status1:amount1");

        Map<String,String> mapp = new HashMap<String, String>();
        mapp.put("productid","orderid:userid:price:status:amount");
        mapp.put("productid1","orderid1:userid1:price1:status1:amount1");
        jedis.hmset("shoppingcart:username1",mapp);
        System.out.println(jedis.hgetAll("shoppingcart:username"));
        //,"productid1","orderid1:userid1:price1:status1:amount"
                //hset shoppingcart:username
        // productid "orderid:userid:price:status:amount"
        // productid1 "orderid1:userid1:price1:status1:amount"

    }



}
