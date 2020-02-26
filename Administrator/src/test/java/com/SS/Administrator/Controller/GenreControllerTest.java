/**
 * 
 */
package com.SS.Administrator.Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;

/**
 * @author acorb
 *
 */
@SpringBootTest
class GenreControllerTest {
	@Autowired
	private GenreController controller;

	@Test
	void testReaAll() {
		ResponseEntity<List<Genre>> response = controller.getgenres();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testFailMessages() {
		Genre genre = new Genre();
		genre.setGenre_Id(292922);
		ResponseEntity<Genre> response = controller.addgenre(genre);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
		ResponseEntity<Genre> responseTwo = controller.updategenre(genre, 2);
		assertEquals(HttpStatus.BAD_REQUEST, responseTwo.getStatusCode());
	}
	
	@Test
	void testAddUpdateDelete() {
		//Test add
		Genre genre = new Genre();
		genre.setGenreName("test Genre");
		ResponseEntity<Genre> addResponse = controller.addgenre(genre);
		assertEquals(HttpStatus.CREATED, addResponse.getStatusCode());
		
		//test update
		Genre addedGenre = addResponse.getBody();
		addedGenre.setGenreName("second name");
		ResponseEntity<Genre> updatedResponse = controller.updategenre(addedGenre, addedGenre.getGenre_Id());
		assertEquals(HttpStatus.ACCEPTED, updatedResponse.getStatusCode() );
		
		ResponseEntity<Genre> getResponse = controller.getgenre(addedGenre.getGenre_Id());
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
		
		ResponseEntity<List<Book>> getBookResponseEntity = controller.readBooksBygenre(genre.getGenre_Id());
		assertEquals(HttpStatus.OK, getBookResponseEntity.getStatusCode());
		
		ResponseEntity<Genre> deletedResponse = controller.deletegenre(addedGenre.getGenre_Id());
		assertEquals(HttpStatus.OK, deletedResponse.getStatusCode());
	}
	
	
	
	

}
