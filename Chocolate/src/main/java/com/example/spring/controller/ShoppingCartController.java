package com.example.spring.controller;

import com.example.spring.redis.CartFacade;
import com.example.spring.redis.ShoppingCart;
import com.example.spring.redis.ShoppingCartQueries;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShoppingCartController {

    CartFacade cartFacade;

    public ShoppingCartController(){
        cartFacade = new CartFacade();
    }

    @GetMapping("/cart/{id}")
    public Map<String, String> getCustomersShoppingCart(@PathVariable int id){
        return cartFacade.getShoppingCartById(id);
    }

//    @PostMapping("cart/add")
//    public void addToCart(@RequestBody ShoppingCart cart){
//        cartFacade.addItemToCart(cart);
//    }

    @GetMapping("/ping")
    public String getJedis(){
        return cartFacade.getJedis();
    }
}
