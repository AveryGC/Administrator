package com.SS.Administrator.Controller;


import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Service.AuthorService;


@RestController
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@RequestMapping(path = "/admin/authors", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Author>> getAuthors(){
		List <Author> authors = authorService.readAllAuthors();
		return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> getAuthor(@PathVariable int authorId){
		try {
			Author author = authorService.findAuthorById(authorId);
			return new ResponseEntity<Author>(author,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = {"/admin/authors"} , method = RequestMethod.POST, consumes ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> addAuthor(@RequestBody Author author){
		try {
			authorService.addAuthor(author);
			return new ResponseEntity<Author>(author,HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Author>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable int authorId){
		if(author.getAuthorId()!= authorId)
			return new ResponseEntity<Author>(HttpStatus.BAD_REQUEST);
		try {	
			authorService.updateAuthor(author);
			return new ResponseEntity<Author>(author,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Author>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Optional<Author>> deleteAuthor(@PathVariable int authorId){
		try {
			Optional<Author> deletedAuthor = authorService.deleteAuthor(authorId);
			return new ResponseEntity<Optional<Author>>(deletedAuthor,HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<Optional<Author>>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path="/admin/authors/{authorId}/books", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>>readBooksByAuthor(@PathVariable int authorId){
		try {
			List <Book> books = authorService.readBooksByAuthor(authorId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(path="/admin/authors/{authorId}/books/{bookId}", method = {RequestMethod.PUT},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>addToBookAuthor(@PathVariable int authorId, @PathVariable int bookId){
		try {
			Book book = authorService.addAuthorFromBook(bookId, authorId);
			return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(path="/admin/authors/{authorId}/books/{bookId}", method = {RequestMethod.POST},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>postToBookAuthor(@PathVariable int authorId, @PathVariable int bookId){
		try {
			Book book = authorService.postAuthorFromBook(bookId, authorId);
			if(book==null)
				return new ResponseEntity<Book>(HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(path="/admin/authors/{authorId}/books/{bookId}", method = {RequestMethod.DELETE},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>deleteBookAuthor(@PathVariable int authorId, @PathVariable int bookId){
		try {
			Book book = authorService.deleteAuthorFromBook(bookId, authorId);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		} 
	}

}
