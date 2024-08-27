package com.bookstore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<EntityModel<BookDTO>>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        List<EntityModel<BookDTO>> bookResources = books.stream()
                .map(book -> {
                    EntityModel<BookDTO> bookResource = EntityModel.of(book);
                    Link selfLink = WebMvcLinkBuilder
                    		.linkTo(WebMvcLinkBuilder
                    				.methodOn(BookController.class)
                    				.getBookById(book.getId()))
                    		.withSelfRel();
                    Link updateLink = WebMvcLinkBuilder
                    		.linkTo(WebMvcLinkBuilder
                    				.methodOn(BookController
                    						.class).updateBook(book.getId(), book))
                    		.withRel("update");
                    Link deleteLink = WebMvcLinkBuilder
                    		.linkTo(WebMvcLinkBuilder
                    				.methodOn(BookController.class)
                    				.deleteBook(book.getId()))
                    		.withRel("delete");
                    bookResource.add(selfLink, updateLink, deleteLink);
                    return bookResource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResources);
    }
	
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable @Min(1) Long id) {
	    BookDTO book = bookService.getBookById(id);
	    if(book == null) {
	    	throw new BookNotFoundException("Book of id: " + id + " does not exists");
	    }
	    EntityModel<BookDTO> bookResource = EntityModel.of(book);
	    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).updateBook(id, book)).withRel("update");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteBook(id)).withRel("delete");
        bookResource.add(selfLink, updateLink, deleteLink);
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Book-Header-Value");
	    return new ResponseEntity<>(bookResource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<EntityModel<Book>> createBook(@Valid @RequestBody BookDTO book) {
		try {
			Book createdBook = bookService.createBook(book);
			 EntityModel<Book> bookResource = EntityModel.of(createdBook);
		     Link selfLink = WebMvcLinkBuilder
		    		 .linkTo(WebMvcLinkBuilder
		    				 .methodOn(BookController.class)
		    				 .getBookById(createdBook.getId()))
		    		 .withSelfRel();
		     bookResource.add(selfLink);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Custom-Header", "Book-Creation-Header");
			return new ResponseEntity<>(bookResource, headers, HttpStatus.OK);
			
		}catch(Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Error", "Internal Server Error");
			return new ResponseEntity<>(null,headers,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable @Min(1) Long id,@Valid @RequestBody BookDTO updatedBook) {
	    BookDTO book = bookService.getBookById(id);
	    if (book != null) {
	        book.setTitle(updatedBook.getTitle());
	        book.setAuthor(updatedBook.getAuthor());
	        book.setPrice(updatedBook.getPrice());
	        book.setIsbn(updatedBook.getIsbn());
	        Book Book = bookService.createBook(book);
	        EntityModel<Book> bookResource = EntityModel.of(Book);
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel();
            bookResource.add(selfLink.withType("PUT"));
	        HttpHeaders headers = new HttpHeaders();
		    headers.add("Custom-Header", "Book-Updation-Header");
	        return new ResponseEntity<>(bookResource, headers, HttpStatus.OK);
	    }
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Error", "Book-Not-Found");
	    return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BookDTO> deleteBook(@PathVariable @Min(1) Long id) {
		BookDTO book = bookService.getBookById(id);
		if(book == null) {
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Error", "Book-Not-Found");
			return new ResponseEntity<BookDTO>(null, headers, HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "Book-Deletion-Header");
	    bookService.deleteBook(id);
		return new ResponseEntity<BookDTO>(book, headers, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/search" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<EntityModel<BookDTO>>>searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
		List<BookDTO> books = bookService.searchBook(title, author);
		List<EntityModel<BookDTO>> bookResources = books.stream()
                .map(book -> {
                    EntityModel<BookDTO> bookResource = EntityModel.of(book);
                    Link selfLink = WebMvcLinkBuilder
                    		.linkTo(WebMvcLinkBuilder
                    				.methodOn(BookController.class)
                    				.getBookById(book.getId()))
                    		.withSelfRel();
                    bookResource.add(selfLink);
                    return bookResource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResources);
	}
}
