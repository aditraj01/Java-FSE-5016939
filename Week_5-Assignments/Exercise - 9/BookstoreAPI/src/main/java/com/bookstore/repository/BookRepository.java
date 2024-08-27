package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b from Book b where (:title IS NULL or b.title = :title) AND (:author IS NULL or b.author = :author)")
	List<Book> searchBook(@Param("title") String title,@Param("author") String author);
}
