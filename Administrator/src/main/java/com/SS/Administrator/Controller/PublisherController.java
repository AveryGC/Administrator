/**
 * 
 */
package com.SS.Administrator.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Publisher;
import com.SS.Administrator.Service.PublisherService;


/**
 * @author acorb
 *
 */
@RestController
public class PublisherController {
	@Autowired
	PublisherService publisherService;
	
	@RequestMapping(path="/admin/publishers", method = RequestMethod.GET)
	public ResponseEntity<List<Publisher>> getPublishers(){
		List<Publisher> publishers = publisherService.readAllPublishers();
		return new ResponseEntity<List<Publisher>>(publishers,HttpStatus.OK);
	}
	
	@RequestMapping(path= {"/admin/publishers"}, method = RequestMethod.POST)
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher){
		try {
			publisherService.addPublisher(publisher);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.ACCEPTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.CONFLICT);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(path="/admin/publishers/{publisherId}", method = RequestMethod.PUT)
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher){
		try {
			publisherService.updatePublisher(publisher);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path="/admin/publishers/{publisherId}", method = RequestMethod.GET)
	public ResponseEntity<Publisher> readPublisher(@PathVariable int publisherId){
		try {
			Publisher publisher = publisherService.findPublisherById(publisherId);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path="/admin/publishers/{publisherId}", method = RequestMethod.DELETE)
	public ResponseEntity<Publisher> deletePublisher(@PathVariable int publisherId){
		try {
			publisherService.deletePublisher(publisherId);
			return new ResponseEntity<Publisher>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.NOT_FOUND);
		}
	}
//	
	@RequestMapping(path="/admin/publishers/{publisherId}/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> readBooksByPublisher(@PathVariable int publisherId){
		try {
			List<Book> books = publisherService.readBooksByPublisher(publisherId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
			}
	}

}
