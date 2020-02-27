package com.SS.Administrator.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.SS.Administrator.Entity.Publisher;

@SpringBootTest
class PublisherServiceTest {
	@Autowired
	private PublisherService service;

	@Test
	void testReadAll() {
		assertNotEquals(0, service.readAllPublishers().size());
	}
	
	@Test
	void testAddFailure(){
		try {
			Publisher pub = new Publisher();
			pub.setPublisherName("not null");
			pub.setPublisherId(100);
			service.addPublisher(pub);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	
	@Test
	void testUpdateFailure() {
		try {
			Publisher publisher = new Publisher();
			publisher.setPublisherId(-11);
			service.updatePublisher(publisher);
			fail();
		}catch(NoSuchElementException e) {
			//passed
		}
	}
	
	@Test
	void testAddThenDelete() {
		//add test
		Publisher publisher = new Publisher();
		publisher.setPublisherAddress("fake");
		publisher.setPublisherName("fake name");
		publisher.setPublisherId(null);
		service.addPublisher(publisher);
		assertNotNull(publisher.getPublisherId());
		
		//testUpdateSuccesful
		publisher.setPublisherPhone("phonenumber");
		service.updatePublisher(publisher);
		
		//testfoundByandCreation
		Publisher foundBy = service.findPublisherById(publisher.getPublisherId());
		assertEquals(foundBy, publisher);

		
		//deletetestsubject
		service.deletePublisher(publisher.getPublisherId());
	}

}
