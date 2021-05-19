package neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;


public class Querys implements AutoCloseable{


    private final Driver driver;

    public Querys(String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void createProduct(String name, int price, String description,int weight){
        try (Session session = driver.session()) {
            Result result1 = session.run("CREATE (n:Product {name:" + name + ", price: " + price +", description: " + description +", weight: " + weight +"})");
            //Result result1 = session.run("MATCH (n:Person) RETURN n.name");
            System.out.println("query1: returns all nodes id" + result1.list()+ "\n" );

        }
        driver.close();

    }

    public static void main( String... args ) throws Exception
    {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234"));
        String name = "\"Mars bar\"";
        int price = 16;
        String description = "\"a tasty classic\"";
        int weight = 200;

        //Querys q = new Querys()
        //createProduct(name,price,description,weight);
/*

        try (Session session = driver.session()) {
            Result result1 = session.run("CREATE (n:Product {name:" + name + ", price: " + price +", discription: " + description +", weight: " + weight +"})");
            //Result result1 = session.run("MATCH (n:Person) RETURN n.name");
            System.out.println("query1: returns all nodes id" + result1.list()+ "\n" );

        }
        driver.close();*/
    }

}
