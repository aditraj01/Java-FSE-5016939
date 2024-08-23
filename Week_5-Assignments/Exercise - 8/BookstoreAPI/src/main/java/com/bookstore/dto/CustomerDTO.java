package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDTO {
	
	@JsonProperty("id")
	private Long id;
	
	@NotNull(message = "Please provide a name")
    private String name;
	@Email(message = "Please provide a valid email address")
    private String email;
	@NotNull(message = "Please provide a address")
    private String address;
    
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    

}
