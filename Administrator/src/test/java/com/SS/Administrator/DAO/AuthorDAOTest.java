/**
 * 
 */
package com.SS.Administrator.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Author;

/**
 * @author acorb
 *
 */
@SpringBootTest
class AuthorDAOTest {

	@Autowired private AuthorDAO aDao;
	

	@Test
	void test1() {
		assertNotNull(aDao);
		assertNotEquals(0, aDao.count());
		assertNotNull(aDao.findAll());
	}
	
	@Test
	void test2() {
		Author testAuthor = new Author();
		testAuthor.setAuthorName("Test Name");
		Author returned = aDao.save(testAuthor);
		if(!returned.getAuthorName().equalsIgnoreCase(testAuthor.getAuthorName()))
			fail();
		aDao.delete(returned);

	}

}
