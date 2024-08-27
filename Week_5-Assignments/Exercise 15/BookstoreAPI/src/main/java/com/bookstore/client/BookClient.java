package com.bookstore.client;

import org.springframework.web.client.RestTemplate;
import com.bookstore.dto.BookDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class BookClient {
	private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080/books";

    public void getBookById(Long id) {
    	
        @SuppressWarnings("unchecked")
		EntityModel<BookDTO> bookResource = (EntityModel<BookDTO>) restTemplate.getForObject(baseUrl + "/" + id, EntityModel.class);
        if (bookResource != null) {
            System.out.println("Book: " + bookResource.getContent());
            Link updateLink = bookResource.getRequiredLink("update");
            Link deleteLink = bookResource.getRequiredLink("delete");

            System.out.println("Update link: " + updateLink.getHref());
            System.out.println("Delete link: " + deleteLink.getHref());
        }
    }

}
