package com.example.spring.neo4j.Model;

public class Product {

    private int weight;
    private int price;
    private String name;
    private String description;

    public Product(String name, int price,String description,int weight  ) {
        this.weight = weight;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "weight=" + weight +
                ", price=" + price +
                ", name=" + name +
                ", description=" + description +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
