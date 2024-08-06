package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public BookRepository getBookRepository() {
		return bookRepository;
	}

    // Example method
    public void manageBooks() {
        // Logic to manage books using bookRepository
        System.out.println("Managing books...");
    }
}
