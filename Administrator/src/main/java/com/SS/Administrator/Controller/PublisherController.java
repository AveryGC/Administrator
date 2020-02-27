/**
 * 
 */
package com.SS.Administrator.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(path = "/admin/publishers")
public class PublisherController {
	@Autowired
	private PublisherService publisherService;
	
	@RequestMapping(method = RequestMethod.GET,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Publisher>> getPublishers(){
		List<Publisher> publishers = publisherService.readAllPublishers();
		return new ResponseEntity<List<Publisher>>(publishers,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> addPublisher(@RequestBody@Valid Publisher publisher){
		try {
			publisherService.addPublisher(publisher);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.CREATED);
		}  catch (IllegalArgumentException e) {
			return new ResponseEntity<Publisher> (HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(path="/{publisherId}", method = RequestMethod.PUT,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> updatePublisher(@RequestBody@Valid  Publisher publisher, @PathVariable@NotNull int publisherId){
		if(publisherId!=publisher.getPublisherId())
			return new ResponseEntity<Publisher>(HttpStatus.BAD_REQUEST);
		publisherService.updatePublisher(publisher);
		return new ResponseEntity<Publisher>(HttpStatus.OK);
	}
	
	@RequestMapping(path="/{publisherId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> readPublisher(@PathVariable@NotNull int publisherId){
			Publisher publisher = publisherService.findPublisherById(publisherId);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
	}
	
	@RequestMapping(path="/{publisherId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> deletePublisher(@PathVariable@NotNull int publisherId){
			publisherService.deletePublisher(publisherId);
			return new ResponseEntity<Publisher>(HttpStatus.OK);
	}
//	
	@RequestMapping(path="/{publisherId}/books", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>> readBooksByPublisher(@PathVariable@NotNull int publisherId){
		try {
			List<Book> books = publisherService.readBooksByPublisher(publisherId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
			}
	}

}
