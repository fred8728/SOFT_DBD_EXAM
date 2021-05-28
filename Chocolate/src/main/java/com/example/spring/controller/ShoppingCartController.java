package com.example.spring.controller;

import com.example.spring.redis.CartFacade;
import com.example.spring.redis.ShoppingCart;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class ShoppingCartController {

    CartFacade cartFacade;

    public ShoppingCartController(){
        cartFacade = new CartFacade();
    }

    //http://localhost:8080/cart/someid
    @GetMapping("/cart/{id}")
    public Map<String, String> getCustomersShoppingCart(@PathVariable String id){
        return cartFacade.getShoppingCartById(id);
    }

    //Add shoppingcart to DB
    //http://localhost:8080/cart/addAll/someid
    @PostMapping("cart/addAll/{customer_id}")
    public void addShoppingCart(@PathVariable String customer_id){
        cartFacade.addShoppingCart(customer_id);
    }

    //Add parameters to add one product at a time
    //http://localhost:8080/cart/addSingle/someid
    @PostMapping("cart/addSingle")
    public void addSingleItem(@RequestBody ShoppingCart cart){
        cartFacade.addProductToCart(cart);
    }
}
