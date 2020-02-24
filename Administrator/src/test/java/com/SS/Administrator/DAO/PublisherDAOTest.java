/**
 * 
 */
package com.SS.Administrator.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Publisher;

/**
 * @author acorb
 *
 */
@SpringBootTest
class PublisherDAOTest {
	
	@Autowired private PublisherDAO pDao;

	@Test
	void test1() {
		assertNotNull(pDao);
		assertNotEquals(0, pDao.count());
		assertNotNull(pDao.findAll());
	}
	
	@Test
	void test2() {
		Publisher testPublisher = new Publisher();
		testPublisher.setPublisherName("test Name");
		testPublisher.setPublisherPhone("fake phone number");
		testPublisher.setPublisherAddress("the white house");
		Publisher returned = pDao.save(testPublisher);
		if(!returned.getPublisherAddress().equalsIgnoreCase(testPublisher.getPublisherAddress()))
			fail();
		if(!returned.getPublisherName().equalsIgnoreCase(testPublisher.getPublisherName()))
			fail();
		if(!returned.getPublisherPhone().equalsIgnoreCase(testPublisher.getPublisherPhone()))
			fail();
		pDao.delete(returned);
	}

}
