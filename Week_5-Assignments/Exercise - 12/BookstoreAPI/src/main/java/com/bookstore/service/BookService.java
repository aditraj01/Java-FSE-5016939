package com.bookstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.metrics.CustomBookMetrics;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookReposiotry;
	private BookStoreMapper mapper  = BookStoreMapper.INSTANCE;
	@Autowired
	private CustomBookMetrics metrics;

	public BookDTO getBookById(long id){
		return mapper.bookToBookDTO(bookReposiotry.findById(id).orElse(null));
	}
	
	public List<BookDTO> getAllBooks(){
		return mapper.bookToBookDTO(bookReposiotry.findAll());
	}
	
	public Book createBook(BookDTO book) {
		metrics.incrementBookCreationCounter();
		return bookReposiotry.save(mapper.bookDTOToBook(book));
	}
	
	public void deleteBook(Long id) {
        bookReposiotry.deleteById(id);
    }
	
	public List<BookDTO> searchBook(String title, String author){
		return mapper.bookToBookDTO(bookReposiotry.searchBook(title, author));
	}
}
