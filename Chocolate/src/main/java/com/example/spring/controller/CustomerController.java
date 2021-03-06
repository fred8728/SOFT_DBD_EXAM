package com.example.spring.controller;

import com.example.spring.facade.CustomerFacade;
import com.example.spring.model.Cities;
import com.example.spring.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class CustomerController {

    CustomerFacade customerFacade;

    public CustomerController() throws SQLException {
        customerFacade = new CustomerFacade();
    }

    // URL: http://localhost:8080/allCustomers
    @GetMapping("/allCustomers")
    public ArrayList<Customer> getCustomers() throws SQLException {
        return customerFacade.getCustomers();
    }

    // URL: http://localhost:8080/customer/someid
    @GetMapping("customer/{id}")
    public Customer getCustomerById(@PathVariable int id) throws SQLException {
        return customerFacade.getCustomerById(id);
    }

    // Add parameters to body
    @PostMapping("city/add")
    public void addCity(@RequestBody Cities city){
        try{
            customerFacade.addCity(city);
        }catch (Exception ex){
            ex.getMessage();
        }
    }

    // Add parameters to body
    @PostMapping("customer/add")
    public void addCustomer(@RequestBody Customer customer){
        try{
            customerFacade.addCustomer(customer);
        }catch (Exception ex){
            ex.getMessage();
        }
    }

    // Add parameters to update in body
    @PutMapping("customer/updateBasic/{id}")
    public void updateCustomerBasic(@PathVariable int id, @RequestBody Customer customer) throws SQLException {
        try{
            customerFacade.updateCustomerBasic(id, customer.getName(), customer.getEmail(), customer.getPhone());
        } catch(Exception ex){
            ex.getMessage();
        }
    }

    // Add parameters to update in body
    @PutMapping("customer/updateAddress/{id}")
    public void updateCustomerAddress(@PathVariable int id, @RequestBody Customer customer) throws SQLException {
        try{
            customerFacade.updateCustomerAddress(id, customer.getStreet(), customer.getCity_id());
        } catch(Exception ex){
            ex.getMessage();
        }
    }

    //URL http://localhost:8080/deleteCustomer/someid
    @DeleteMapping("deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable int id) throws SQLException {
        customerFacade.deleteCustomer(id);
    }
}
