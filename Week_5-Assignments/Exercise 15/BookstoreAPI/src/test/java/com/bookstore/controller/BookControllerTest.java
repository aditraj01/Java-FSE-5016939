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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bookstore.dto.*;
import com.bookstore.entity.UserData;
import com.bookstore.mapper.BookStoreMapper;
import com.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
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

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateBook() throws Exception {
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1L);
		bookDTO.setTitle("Title");
		bookDTO.setAuthor("Author");
		bookDTO.setPrice(19.99);
		bookDTO.setIsbn("1234567890");
		when(bookService.createBook(bookDTO)).thenReturn(mapper.bookDTOToBook(bookDTO));

		mockMvc.perform(post("/books/create").contentType(MediaType.APPLICATION_JSON).header("Authorization", bearer)
				.content(objectMapper.writeValueAsString(bookDTO))).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
	}
	
	@Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1").header("Authorization", bearer))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void testUpdateBook() throws Exception {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1L);
		bookDTO.setTitle("Updated Title");
		bookDTO.setAuthor("Author");
		bookDTO.setPrice(19.99);
		bookDTO.setIsbn("1234567890");
        when(bookService.updateBook(1L, bookDTO)).thenReturn(mapper.bookDTOToBook(bookDTO));
        
        UserData user = new UserData();
		user.setUsername("Aditya");
		user.setPassword("Admin");
		ResponseEntity<UserData> User = authController.register(user);
		UserData data = User.getBody();
		data.setPassword("Admin");
		ResponseEntity<String> token = authController.login(user);
		bearer = "Bearer " + token.getBody();


        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", bearer)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

	@Test
	public void testGetBookById() throws Exception {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1L);
		bookDTO.setTitle("Title");
		bookDTO.setAuthor("Author");
		bookDTO.setPrice(19.99);
		bookDTO.setIsbn("1234567890");
		when(bookService.getBookById(1L)).thenReturn(bookDTO);

		mockMvc.perform(get("/books/1").header("Authorization", bearer)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"));
	}

}
