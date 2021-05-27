package com.example.spring.controller;

import com.example.spring.neo4j.Querys;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @RequestMapping("/neo4j/{name}")
    public String getproduct(@PathVariable String name){
        Querys t = new Querys("bolt://localhost:7687", "neo4j", "1234");
        System.out.println(t.getProduct(name));
        return "t.getProduct(name)";

    }
}
