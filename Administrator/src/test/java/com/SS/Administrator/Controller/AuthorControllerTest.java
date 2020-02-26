package com.SS.Administrator.Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SS.Administrator.Entity.Author;

@SpringBootTest
class AuthorControllerTest {
	@Autowired
	private AuthorController controller;

	@Test
	void test() {
		ResponseEntity<List<Author>> response = controller.getAuthors();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testFailMessages() {
		Author author = new Author();
		author.setAuthorId(10202);
		author.setAuthorName("Bobby Smurda");
		ResponseEntity<Author> response = controller.addAuthor(author);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
		ResponseEntity<Author> responseTwo = controller.updateAuthor(author, 2);
		assertEquals(HttpStatus.BAD_REQUEST, responseTwo.getStatusCode());
	}

}
