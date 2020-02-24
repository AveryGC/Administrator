package com.SS.Administrator.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
@SpringBootTest
class BookDAOTest {
	
	@Autowired private BookDAO bDao;

	@Test
	void test() {
		assertNotNull(bDao);
		assertNotEquals(0, bDao.count());
		assertNotNull(bDao.findAll());
	}
	
	@Test
	void test2() {
		Book testBook = new Book();
		testBook.setTitle("test Book title");
		Book returned = bDao.save(testBook);
		if(!returned.getTitle().equalsIgnoreCase(testBook.getTitle()))
			fail();
		bDao.delete(returned);

	}

}
