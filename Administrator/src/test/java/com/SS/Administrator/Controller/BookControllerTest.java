/**
 * 
 */
package com.SS.Administrator.Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Entity.Publisher;

/**
 * @author acorb
 *
 */
@SpringBootTest
class BookControllerTest {
	@Autowired
	private BookController controller;
	

	@Test
	void testGetbooks() {
		ResponseEntity<List<Book>> response = controller.getBooks();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotEquals(0, response.getBody());
	}
	
	@Test
	void testPostAndPutFail() {
		Book book = new Book();
		book.setBookId(1000);
		ResponseEntity<Book> response = controller.addBook(book);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
		book.setTitle("fake");
		book.setAuthors(new ArrayList<Author>());
		book.setGenres(new ArrayList<Genre>());
		book.setPublisher(new Publisher());
		ResponseEntity<Book> putResponse= controller.updateBook(book, 2000);
		assertEquals(HttpStatus.BAD_REQUEST, putResponse.getStatusCode());
	}
	

}
