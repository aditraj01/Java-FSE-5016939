package com.bookstore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.entity.Book;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
	    Book book = bookService.getBookById(id);
	    if(book == null) {
	    	throw new BookNotFoundException("Book of id: " + id + " does not exists");
	    }
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Book-Header-Value");
	    return new ResponseEntity<Book>(book, headers, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			bookService.createBook(book);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Custom-Header", "Book-Creation-Header");
			return new ResponseEntity<Book>(book, headers, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Book>(book, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
	    Book book = bookService.getBookById(id);
	    if (book != null) {
	        book.setTitle(updatedBook.getTitle());
	        book.setAuthor(updatedBook.getAuthor());
	        book.setPrice(updatedBook.getPrice());
	        book.setIsbn(updatedBook.getIsbn());
	        Book Book = bookService.createBook(book);
	        HttpHeaders headers = new HttpHeaders();
		    headers.add("Custom-Header", "Book-Updation-Header");
	        return new ResponseEntity<Book>(Book, headers, HttpStatus.OK);
	    }
	    return new ResponseEntity<Book>(book, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
		Book book = bookService.getBookById(id);
		if(book == null) {
			return new ResponseEntity<Book>(book, HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "Book-Deletion-Header");
	    bookService.deleteBook(id);
		return new ResponseEntity<Book>(book, headers, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.FOUND)
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
		return bookService.searchBook(title, author);
		
	}
}
