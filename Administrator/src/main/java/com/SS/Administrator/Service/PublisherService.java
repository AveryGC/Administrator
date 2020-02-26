/**
 * 
 */
package com.SS.Administrator.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.SS.Administrator.DAO.BookDAO;
import com.SS.Administrator.DAO.PublisherDAO;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Publisher;

/**
 * @author acorb
 *
 */
@Component
@Transactional
public class PublisherService {
	@Autowired
	private PublisherDAO pDao;
	@Autowired
	private BookDAO bDao;
	
	public List<Publisher> readAllPublishers(){
		return pDao.findAll();
	}
	
	@Transactional
	public void addPublisher(Publisher publisher) throws IllegalArgumentException{
		if(publisher.getPublisherName()!=null) {
			if(publisher.getPublisherId()==null){
				Publisher returned = pDao.save(publisher);
				publisher = returned;
			}
			else
				throw new IllegalArgumentException();
		}else {
			throw new IllegalArgumentException();
		}
	}
	@Transactional
	public void updatePublisher(Publisher publisher) throws IllegalArgumentException {
		if(!pDao.existsById(publisher.getPublisherId()))
			throw new NoSuchElementException();
		Publisher returned = pDao.save(publisher);
		publisher = returned;
	}
	@Transactional
	public Publisher deletePublisher(int publisherId) throws NoSuchElementException {
		Publisher deletedPublisher = pDao.findById(publisherId).get();
		deletedPublisher.getBooks().forEach(b->{
			bDao.delete(b);
		});
		pDao.deleteById(publisherId);
		return deletedPublisher;
	}
	
	public Publisher findPublisherById(int publisherId) throws NoSuchElementException{
		return pDao.findById(publisherId).get();
	}
	
	public List<Book> readBooksByPublisher(int publisherId)throws NoSuchElementException{
		return pDao.findById(publisherId).get().getBooks();
	}
}
