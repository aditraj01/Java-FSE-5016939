package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bookstore.dto.CustomerDTO;
import com.bookstore.entity.Customer;
import com.bookstore.service.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDTO> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable @Min(1) Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Customer registerCustomer(@ModelAttribute @Valid CustomerDTO customer) {
        return customerService.createCustomer(customer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable @Min(1) Long id) {
    	CustomerDTO customer = customerService.getCustomerById(id);
        if (customer != null) {
        	customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
