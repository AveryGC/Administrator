/**
 * 
 */
package com.SS.Administrator.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Genre;

/**
 * @author acorb
 *
 */
@SpringBootTest
class GenreServiceTest {
	@Autowired
	private GenreService service;

	@Test
	void testReadAll() {
		assertNotEquals(0, service.readAllGenres());
	}
	
	@Test
	void testAndFailure() {
//		try {
//			Genre genre = new Genre();
//			genre.setGenreName(null);
//			service.addGenre(genre);
//			fail();
//		}catch(IllegalArgumentException e) {}
		try {
			Genre genre = new Genre();
			genre.setGenreName("fakest");
			genre.setGenre_Id(10);
			service.addGenre(genre);
		}catch(IllegalArgumentException e) {}
	}
	
	@Test 
	void testUpdateFail() {
		try {
			Genre genre = new Genre();
			genre.setGenre_Id(21);
			service.updateGenre(genre);
			fail();
		}catch(NoSuchElementException e) {}
	}
	
	@Test
	void testAndThenUpdate() {
			Genre genre = new Genre();
			genre.setGenreName("fake name");
			service.addGenre(genre);
			assertNotNull(genre.getGenre_Id());
			
			//testUpdateSuccessful
			genre.setGenreName("new name");
			service.updateGenre(genre);
			
			Genre foundBy = service.findGenreById(genre.getGenre_Id());
			assertEquals(genre, foundBy);
			
			//delete
			service.deleteGenre(genre.getGenre_Id());
	}

}
