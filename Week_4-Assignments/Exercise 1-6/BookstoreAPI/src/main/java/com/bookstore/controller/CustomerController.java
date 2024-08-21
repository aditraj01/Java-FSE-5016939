package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bookstore.entity.Customer;
import com.bookstore.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Customer registerCustomer(@ModelAttribute Customer customer) {
        return customerService.createCustomer(customer);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
    	customerService.deleteCustomer(id);
    }

}
