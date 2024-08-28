package com.bookstore.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.dto.*;
import com.bookstore.entity.UserData;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
@Transactional
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@Autowired
	private AuthController authController;

	@Autowired
	private ObjectMapper objectMapper;

	private BookStoreMapper mapper = BookStoreMapper.INSTANCE;

	private static String bearer;
	
	private BookDTO book = new BookDTO();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		
		book.setId(1L);
		book.setTitle("Title");
		book.setAuthor("Author");
		book.setPrice(19.99);
		book.setIsbn("1234567890");
        
        UserData user = new UserData();
		user.setUsername("Aditya");
		user.setPassword("Admin");
		authController.register(user);
		UserData user1 = new UserData();
		user1.setUsername("Aditya");
		user1.setPassword("Admin");
		ResponseEntity<String> token = authController.login(user1);
		bearer = "Bearer " + token.getBody();

	}

	@Test
	@Transactional
	public void testCreateBook() throws Exception {
		
		BookDTO bookDTO = book;
		when(bookService.createBook(bookDTO)).thenReturn(mapper.bookDTOToBook(bookDTO));

		mockMvc.perform(post("/books/create").contentType(MediaType.APPLICATION_JSON).header("Authorization", bearer)
				.content(objectMapper.writeValueAsString(bookDTO))).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
	}
	
	@Test
	@Transactional
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1").header("Authorization", bearer))
                .andExpect(status().isNotFound());
    }
	
	@Test
	@Transactional
    public void testUpdateBook() throws Exception {
		
		BookDTO bookDTO = book;
		bookDTO.setTitle("Updated Title");
		
		when(bookService.updateBook(1L, bookDTO)).thenReturn(mapper.bookDTOToBook(bookDTO));
		
		mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", bearer)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

	@Test
	@Transactional
	public void testGetBookById() throws Exception {
		BookDTO bookDTO = book;
		when(bookService.getBookById(1L)).thenReturn(bookDTO);

		mockMvc.perform(get("/books/1").header("Authorization", bearer)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
	}

}
