/**
 * 
 */
package com.SS.Administrator.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.Administrator.DAO.PublisherDAO;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Publisher;

/**
 * @author acorb
 *
 */
@Component
public class PublisherService {
	@Autowired
	PublisherDAO pDao;
	
	public List<Publisher> readAllPublishers(){
		return pDao.findAll();
	}
	
	public void addPublisher(Publisher publisher) throws IllegalArgumentException, SQLException {
		if(publisher.getPublisherName()!=null) {
			if(publisher.getPublisherId()!=null)
				if(pDao.existsById(publisher.getPublisherId()))
					throw new SQLException();
				else{
					Publisher returned = pDao.save(publisher);
					pDao.flush();
					publisher = returned;
				}
		}else {
			throw new IllegalArgumentException();
		}
	}
	public void updatePublisher(Publisher publisher) throws IllegalArgumentException {
		if(publisher.getPublisherName()!=null&&publisher.getPublisherId()!=null) {
			Publisher returned = pDao.save(publisher);
			pDao.flush();
			publisher = returned;
		}else
			throw new IllegalArgumentException();
	}
	public Publisher deletePublisher(int publisherId) throws NoSuchElementException {
		Publisher deletedPublisher = pDao.findById(publisherId).get();
		pDao.deleteById(publisherId);
		pDao.flush();
		return deletedPublisher;
	}
	public Publisher findPublisherById(int publisherId) throws NoSuchElementException{
		return pDao.findById(publisherId).get();
	}
	public List<Book> readBooksByPublisher(int publisherId)throws NoSuchElementException{
		return pDao.findById(publisherId).get().getBooks();
	}
}
