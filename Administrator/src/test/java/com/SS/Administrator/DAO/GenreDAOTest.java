/**
 * 
 */
package com.SS.Administrator.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Genre;

/**
 * @author acorb
 *
 */
@SpringBootTest
class GenreDAOTest {
	
	@Autowired private GenreDAO gDao;

	@Test
	void test1() {
		assertNotNull(gDao);
		assertNotEquals(0, gDao.count());
		assertNotNull(gDao.findAll());
	}
	
	@Test
	void test2() {
		Genre testGenre = new Genre();
		testGenre.setGenreName("Test Genre");
		Genre returned = gDao.save(testGenre);
		if(!returned.getGenreName().equalsIgnoreCase(testGenre.getGenreName()))
			fail();
		gDao.delete(returned);
	}

}
