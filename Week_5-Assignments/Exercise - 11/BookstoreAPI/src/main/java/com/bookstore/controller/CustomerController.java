package com.bookstore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
	public ResponseEntity<List<EntityModel<CustomerDTO>>> getAllCustomers() {
		 List<CustomerDTO> customers = customerService.getAllCustomers();
		 List<EntityModel<CustomerDTO>> customerResources = customers.stream()
	                .map(customer -> {
	                    EntityModel<CustomerDTO> customerResource = EntityModel.of(customer);
	                    Link selfLink = WebMvcLinkBuilder
	                    		.linkTo(WebMvcLinkBuilder
	                    				.methodOn(CustomerController.class)
	                    				.getCustomerById(customer.getId()))
	                    		.withSelfRel();
	                    customerResource.add(selfLink);
	                    return customerResource;
	                })
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(customerResources);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<CustomerDTO>> getCustomerById(@PathVariable @Min(1) Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer != null) {
        	EntityModel<CustomerDTO> customerResource = EntityModel.of(customer);
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(id)).withSelfRel();
            Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).updateCustomer(id, customer)).withRel("update");
            Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).deleteCustomer(id)).withRel("delete");
            customerResource.add(selfLink, updateLink, deleteLink);
            return ResponseEntity.ok(customerResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> updateCustomer(@PathVariable @Min(1) Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.getCustomerById(id);
        if (customer != null) {
        	customer.setName(customerDTO.getName());
        	customer.setEmail(customerDTO.getEmail());
        	customer.setAddress(customerDTO.getAddress());
        	Customer Customer = customerService.createCustomer(customer);
            EntityModel<Customer> customerResource = EntityModel.of(Customer);
            Link selfLink = WebMvcLinkBuilder
            		.linkTo(WebMvcLinkBuilder
            				.methodOn(CustomerController.class)
            				.getCustomerById(id))
            		.withSelfRel();
            customerResource.add(selfLink);
            return ResponseEntity.ok(customerResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


	@PostMapping("/create")
    public ResponseEntity<EntityModel<Customer>> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = customerService.createCustomer(customerDTO);
        EntityModel<Customer> customerResource = EntityModel.of(createdCustomer);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(createdCustomer.getId())).withSelfRel();
        customerResource.add(selfLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResource);
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntityModel<Customer>> registerCustomer(@ModelAttribute @Valid CustomerDTO customerDTO) {
    	Customer createdCustomer = customerService.createCustomer(customerDTO);
        EntityModel<Customer> customerResource = EntityModel.of(createdCustomer);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(createdCustomer.getId())).withSelfRel();
        customerResource.add(selfLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResource);
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
