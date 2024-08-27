package com.bookstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.dto.CustomerDTO;
import com.bookstore.entity.Customer;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.metrics.CustomCustomerMetrics;
import com.bookstore.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomCustomerMetrics metrics;
	private final BookStoreMapper mapper = BookStoreMapper.INSTANCE;
	
	public List<CustomerDTO> getAllCustomers(){
		return mapper.customerToCustomerDTO(customerRepository.findAll());
	}
	
	public Customer createCustomer(CustomerDTO customer) {
		metrics.incrementBookCreationCounter();
		return customerRepository.save(mapper.customerDTOToCustomer(customer));
	}
	
	public CustomerDTO getCustomerById(Long id) {
		return mapper.customerToCustomerDTO(customerRepository.findById(id).orElse(null));
	}
	
	public void deleteCustomer(Long id) {
		  customerRepository.deleteById(id);
	}

}
