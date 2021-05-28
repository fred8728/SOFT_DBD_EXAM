package com.example.spring.neo4j;

import com.example.spring.neo4j.Model.Product;
import com.example.spring.neo4j.Model.Category;
import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;



public class Querys implements AutoCloseable {


    private final Driver driver;

    public Querys(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void createProduct(String name, int price, String description, int weight) {
        try (Session session = driver.session()) {
            session.run("CREATE (a:Product {name: $name, price: $price, description: $description, weight: $weight})"
                    , parameters( "name", name, "price", price, "description", description, "weight", weight));
        }
    }

    public void updateProduct(String name, int price, String description, int weight) {
        try (Session session = driver.session()) {
            session.run("MATCH (p:Product {name: $name})\n" +
                    "SET p.price = $price" +
                    ", p.description = $description" +
                    ", p.weight= $weight"
                    ,  parameters( "name", name, "price", price, "description", description, "weight", weight));


        }
    }



    public Product getSingleProduct( final String name )
    {
        try ( Session session = driver.session() )
        {
            ArrayList<Product> people = session.readTransaction(new TransactionWork<ArrayList>()
            {
                @Override
                public ArrayList execute( Transaction tx )
                {
                    ArrayList list = new ArrayList();
                    tx.run("MATCH (n:Product) WHERE n.name = $name RETURN n",
                            parameters("name", name)).list().forEach(person -> {
                        Product product = new Product(person.get("n").get("name").toString(),person.get("n").get("price").asInt(),person.get("n").get("description").toString(),person.get("n").get("weight").asInt());
                        list.add(product);

                    });
                    return list;
                }
            });
            return people.get(0);
            }
        }



    public List<Product> getAllProduct( )
    {
        try ( Session session = driver.session() )
        {
            ArrayList<Product> people = session.readTransaction(new TransactionWork<ArrayList>()
            {
                @Override
                public ArrayList execute( Transaction tx )
                {
                    ArrayList list = new ArrayList();
                    tx.run("MATCH (n:Product) RETURN n"
                    ).list().forEach(person -> {
                        Product product = new Product(person.get("n").get("name").toString(),person.get("n").get("price").asInt(),person.get("n").get("description").toString(),person.get("n").get("weight").asInt());
                        list.add(product);

                    });
                    return list;
                }
            });
            return people;
        }
    }

    public List<Product> getProductByCategoryName( final String categoryName )
    {
        try ( Session session = driver.session() )
        {
            ArrayList<Product> people = session.readTransaction(new TransactionWork<ArrayList>()
            {
                @Override
                public ArrayList execute( Transaction tx )
                {
                    ArrayList list = new ArrayList();
                    tx.run("MATCH (Category {name: $categoryName})--(Product) RETURN Product"
                            ,parameters("categoryName", categoryName))
                            .list().forEach(person -> {
                        Product product = new Product(person.get("Product").get("name").toString(),person.get("Product").get("price").asInt(),person.get("Product").get("description").toString(),person.get("Product").get("weight").asInt());
                        list.add(product);
                    });
                    return list;
                }
            });
            return people;
        }
    }

    public void deleteProduct(String name) {
        try (Session session = driver.session()) {
            session.run("MATCH (m:Product {name: $name}) DELETE m"
            ,parameters("name", name));
        }
    }

    public void createCategory(String name) {
        try (Session session = driver.session()) {
            session.run("MERGE (n:Category {name:$name})",parameters("name", name));
        }
    }


    public void deleteCategory(String name) {
        try (Session session = driver.session()) {
            session.run("MATCH (m:Category {name:$name}) DELETE m",parameters("name", name));
        }
    }


    public void createRelationToProduct(String categoryName, String productName) {

        //tilføj rigtigt ralations navn
        try (Session session = driver.session()) {
            session.run("MATCH\n" +
                    "  (a:Category),\n" +
                    "  (b:Product)\n" +
                    "WHERE a.name = $categoryName  AND b.name = $productName \n" +
                    "MERGE (a)-[r:INVENTORY]->(b)\n" +
                    "RETURN type(r)"
                    ,parameters("categoryName", categoryName,"productName",productName));
        }
    }

    public void deleteProductsRelations(String productName) {

        //tilføj rigtigt ralations navn
        try (Session session = driver.session()) {
            session.run("MATCH (n:Product {name: $productName })<-[r:INVENTORY]-() " +
                    "DELETE r",
                     parameters( "productName", productName));
        }
    }

    public void deleteEverythingInDatabase() {
        try (Session session = driver.session()) {
            session.run("match (a) -[r] -> () delete a, r");
            session.run("match (a) delete a");
        }
    }


    public static void main(String... args) throws Exception {
        Querys t = new Querys("bolt://localhost:7688", "neo4j", "1234");

        //t.createCategory("Dark Chocolate 2");
        //t.createProduct("Twix",15,"now even better",500);
        //t.updateProduct("Twix",55,"now even more twix",5000);
        //t.deleteProduct("Twix");
        //t.deleteProductsRelations("Mars");
        //t.deleteProduct("Mars");
        //t.getProduct("Mars");

        //t.createRelationToProduct("Dark Chocolate 2", "Mars bar");

        //Result result5 = t.getProduct("Mars bar");
        //t.getProduct("Mars");

        //t.deleteProduct("Twix");
        //t.deleteEverythingInDatabase(); //DELETES EVERYTHING IN THE DATABASE

        t.createProduct("Twix",15,"now even better",500);
        t.createProduct("Mars",15,"now even better",500);
        t.createProduct("Bounty",15,"now even better",500);
        t.createProduct("Kitkat",15,"now even better",500);
        t.createProduct("Guldbar",15,"now even better",500);
        t.createProduct("Snickers",15,"now even better",500);
        t.createProduct("After Eight",15,"now even better",500);
        t.createProduct("Toblerone",15,"now even better",500);
        t.createProduct("Marabou",15,"now even better",500);
        t.createProduct("Ritter Sport",15,"now even better",500);
        t.createCategory("Dark Chocolate");
        t.createCategory("Light Chocolate");
        t.createCategory("White Chocolate");
        t.createCategory("Ruby Chocolate");
        t.createRelationToProduct("Dark Chocolate", "Mars");
        t.createRelationToProduct("Light Chocolate", "Twix");
        t.createRelationToProduct("Dark Chocolate", "Bounty");
        t.createRelationToProduct("Dark Chocolate", "Kitkat");
        t.createRelationToProduct("Dark Chocolate", "Guldbar");
        t.createRelationToProduct("Light Chocolate", "Snickers");
        t.createRelationToProduct("Dark Chocolate", "After Eight");
        t.createRelationToProduct("Dark Chocolate", "Toblerone");
        t.createRelationToProduct("White Chocolate", "Marabou");
        t.createRelationToProduct("Ruby Chocolate", "Ritter Sport");

        t.close();
    }
}
