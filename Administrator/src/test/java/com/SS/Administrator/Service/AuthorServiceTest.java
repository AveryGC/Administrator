package com.SS.Administrator.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Author;

@SpringBootTest
class AuthorServiceTest {
	@Autowired
	private AuthorService service;

	@Test
	void testReadAll() {
		assertNotEquals(0, service.readAllAuthors().size());
	}
	
	@Test
	void testAddFailure() {
		try {
			Author author = new Author();
			author.setAuthorName(null);
			service.addAuthor(author);
			fail();
		}catch(IllegalArgumentException e) {}
		try {
			Author author = new Author();
			author.setAuthorName("bobby");
			author.setAuthorId(10000);
			service.addAuthor(author);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	void testUpdateFail() {
		try {
			Author author = new Author();
			author.setAuthorId(-1);
			service.updateAuthor(author);
			fail();
		}catch(NoSuchElementException e) {}
	}
	
	@Test
	void testAddThenUpdate() {
		//add test
		Author author = new Author();
		author.setAuthorName("I am the creator");
		service.addAuthor(author);
		assertNotNull(author.getAuthorId());
		
		//testUpdateSuccessful
		author.setAuthorName("fake name 2");
		service.updateAuthor(author);
		
		//testFindReadAndCreation
		Author foundBy = service.findAuthorById(author.getAuthorId());
		assertEquals(author, foundBy);
		//deleteTestSubject
		service.deleteAuthor(author.getAuthorId());
		
	}

}
