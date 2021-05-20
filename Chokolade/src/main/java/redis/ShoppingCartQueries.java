package redis;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class ShoppingCartQueries {

    private static Jedis jedis;

    public ShoppingCartQueries(Jedis jedis) {
        this.jedis = jedis;
    }

    public void addToCart(String username){
        jedis.hset("shoppingcart:username","productid","orderid:userid:price:status:amount");
    }

    public void getUserShoppingcart(String username){
        jedis.hgetAll("shoppingcart:username");
    }

    public void addMultiple(Map<String, String> value){
        //mapp.put("productid","orderid:userid:price:status:amount");
        //mapp.put("productid1","orderid1:userid1:price1:status1:amount1");
        jedis.hmset("shoppingcart:username1",value);
    }

}
