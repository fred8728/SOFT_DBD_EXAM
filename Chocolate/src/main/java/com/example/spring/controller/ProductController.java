package com.example.spring.controller;

import com.example.spring.neo4j.Model.Category;
import com.example.spring.neo4j.Model.Product;
import com.example.spring.neo4j.Querys;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    Querys t = new Querys("bolt://localhost:7689", "neo4j", "1234");


    //Tested and works
    //URL http://localhost:8080/allProducts
    @GetMapping("allProducts")
    public List<Product> getproduct(){
        return t.getAllProduct();
    }

    //Tested and works
    //URL http://localhost:8080/product/name
    @GetMapping("product/{name}")
    public Product getSingleproduct(@PathVariable String name){
        return t.getSingleProduct(name);
    }

    //Tested and works
    //URL http://localhost:8080/categoryProducts/name
    @GetMapping("categoryProducts/{name}")
    public List<Product> getproductByCategoryName(@PathVariable String name){
        return t.getProductByCategoryName(name);
    }

    //Tested and works
    //URL http://localhost:8080/product/add
    @PostMapping("product/add")
    public void addProduct(@RequestBody Product product){
        t.createProduct(product.getName(),product.getPrice(),product.getDescription(),product.getWeight());
    }
    //Tested and works
    //URL http://localhost:8080/product/update
    @PutMapping("product/update")
    public void updateProduct(@RequestBody Product product){
        t.updateProduct(product.getName(),product.getPrice(),product.getDescription(), product.getWeight());
    }

    //Tested and works
    //URL http://localhost:8080/product/delete/Mars
    @DeleteMapping("product/delete/{name}")
    public void deleteProduct(@PathVariable String name){
        t.deleteProduct(name);
    }

    //Tested and doest not work
    //URL http://localhost:8080/category/add
    @PostMapping("category/add")
    public void addCategory(@RequestBody String category){
        t.createCategory(category);
    }

    //Tested and works
    //URL http://localhost:8080/category/delete/newChocolate
    @DeleteMapping("category/delete/{name}")
    public void deleteCategory(@PathVariable String name){
        t.deleteCategory(name);
    }


    //Tested and works
    //URL http://localhost:8080/categoryRelation/add/new Chocolate/Milky Way
    @PostMapping("categoryRelation/add/{categoryName}/{productName}")
    public void addRelationToProduct(@PathVariable String categoryName,@PathVariable String productName){
        t.createRelationToProduct(categoryName,productName);
    }

    //Tested and works
    //URL http://localhost:8080/product/relation/Mars
    @DeleteMapping("product/relation/{name}")
    public void deleteProductsRelations(@PathVariable String name){
        t.deleteProductsRelations(name);
    }

    //Tested and works
    //URL http://localhost:8080/datbase/deleteeverything
    @DeleteMapping("datbase/deleteeverything")
    public void deleteEverythinginDatabase(){
        t.deleteEverythingInDatabase();
    }









}
