package com.example.spring.neo4j;

import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class HelloWorldExample implements AutoCloseable
{
    private final Driver driver;

    public HelloWorldExample( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printGreeting( final String name )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "MATCH (n:Product) where n.name = $name RETURN n.description, n.price, n.name", parameters("name", name));
                    return result.single().get(1).asString();
                }
            } );
            System.out.println( greeting );
        }
    }

    public List<String> getPeople(String name)
    {
        try ( Session session = driver.session() )
        {
            return session.readTransaction( tx -> {
                List<String> names = new ArrayList<>();
                Result result = tx.run( "MATCH (n:Product) where n.name = $name RETURN n.name, n.description" , parameters("name", name));
                while ( result.hasNext() )
                {
                    names.add( result.next().get( 0 ).asString() );
                }
                return names;
            } );
        }
    }

    public static void main( String... args ) throws Exception
    {
        try ( HelloWorldExample greeter = new HelloWorldExample( "bolt://localhost:7689", "neo4j", "password" ) )
        {
            System.out.println(greeter.getPeople("Mars"));
            //greeter.printGreeting( "Mars" );
        }
    }
}
