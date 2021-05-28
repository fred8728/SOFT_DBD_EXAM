package com.example.spring.postgress.Model;

public class Cities {
    private int city_id;
    private String name;

    public Cities(int city_id, String name) {
        this.city_id = city_id;
        this.name = name;
    }

    public int getCity_id() {
        return city_id;
    }
    public String getName() {
        return name;
    }
}
