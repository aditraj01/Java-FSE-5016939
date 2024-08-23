package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {
	
	@JsonProperty("id")
	private Long id;
	
	@NotNull(message = "Please provide a title")
    @Size(min = 1, max = 100, message = "Title must be between 1 to 100 characters only")
    private String title;
	@NotNull(message = "Please provide a author name")
    @Size(min = 1, max = 100, message = "Author must be between 1 to 100 characters only")
    private String author;
	@Min(0)
    private double price;
	@NotNull(message = "Please provide a isbn no")
    @Size(min = 10, max = 13, message = "isbn no. must be between 10 to 13 characters only")
    private String isbn;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
