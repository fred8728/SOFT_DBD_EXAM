package com.example.spring.redis;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;

public class Setups {

    public GenericContainer redisContainer;
    protected Main tt;
    protected CartFacade cart;
    public String host = "localhost";
    public int port = 6379;
    private Jedis jedis;


    public  void setupContainer() {
        redisContainer = new GenericContainer(DockerImageName.parse("redis:alpine"))
                .withExposedPorts(6379);
        redisContainer.start();
        host = redisContainer.getHost();
        port = redisContainer.getFirstMappedPort();
    }

    public void setupCon(){
        host = "localhost";
        port = 6379;

        //setupContainer();

        jedis = new Jedis(host, port);
        jedis.select(9);
        //tt = new Main(jedis);
        cart = new CartFacade();

    }

    public void beforeEachCon() {
        jedis.flushDB();
    }
}
