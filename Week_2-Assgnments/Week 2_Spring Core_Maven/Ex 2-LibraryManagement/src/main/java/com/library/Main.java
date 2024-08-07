package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class Main {
    public static void main(String[] args) {
        // Load the Spring context from XML configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the BookService bean
        BookService bookService = context.getBean("bookService", BookService.class);

        // Call a method on BookService
        bookService.manageBooks();

        // Close the Spring context
        context.close();
    }
}
