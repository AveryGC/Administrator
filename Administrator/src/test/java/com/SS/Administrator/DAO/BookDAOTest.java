package com.SS.Administrator.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookDAOTest {
	
	@Autowired private BookDAO bDao;

	@Test
	void test() {
		assertNotNull(bDao);
		assertNotEquals(0, bDao.count());
		assertNotNull(bDao.findAll());
	}

}
