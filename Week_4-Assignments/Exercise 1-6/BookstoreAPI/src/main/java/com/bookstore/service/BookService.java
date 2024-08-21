package com.bookstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookReposiotry;

	public Book getBookById(long id){
		return bookReposiotry.findById(id).orElse(null);
	}
	
	public List<Book> getAllBooks(){
		return bookReposiotry.findAll();
	}
	
	public Book createBook(Book book) {
		return bookReposiotry.save(book);
	}
	
	public void deleteBook(Long id) {
        bookReposiotry.deleteById(id);
    }
	
	public List<Book> searchBook(String title, String author){
		return bookReposiotry.searchBook(title, author);
	}
}
