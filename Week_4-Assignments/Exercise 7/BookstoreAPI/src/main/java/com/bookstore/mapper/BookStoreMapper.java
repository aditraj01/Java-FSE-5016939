package com.bookstore.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.bookstore.dto.BookDTO;
import com.bookstore.dto.CustomerDTO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;

@Mapper
public interface BookStoreMapper {
	
    BookStoreMapper INSTANCE = Mappers.getMapper(BookStoreMapper.class);

    // Mapping methods for Book
    BookDTO bookToBookDTO(Book book);
    @Mapping(target = "id", ignore = true)
    Book bookDTOToBook(BookDTO bookDTO);

    // Mapping methods for Customer
    CustomerDTO customerToCustomerDTO(Customer customer);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", defaultValue = "India")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
	List<CustomerDTO> customerToCustomerDTO(List<Customer> customers);
	List<BookDTO> bookToBookDTO(List<Book> books);
}