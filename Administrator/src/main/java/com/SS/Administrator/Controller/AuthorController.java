package com.SS.Administrator.Controller;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
@RequestMapping(path = "/admin/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Author>> getAuthors(){
		List <Author> authors = authorService.readAllAuthors();
		return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{authorId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> getAuthor(@PathVariable@NotBlank int authorId){
		Author author = authorService.findAuthorById(authorId);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> addAuthor(@RequestBody Author author){
		try {
			authorService.addAuthor(author);
			return new ResponseEntity<Author>(author,HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Author>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path = "/{authorId}", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> updateAuthor(@RequestBody @NotEmpty Author author, @PathVariable@NotBlank int authorId){
		if(author.getAuthorId()!= authorId)
			return new ResponseEntity<Author>(HttpStatus.BAD_REQUEST);
		try {	
			authorService.updateAuthor(author);
			return new ResponseEntity<Author>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Author>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(path = "/{authorId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> deleteAuthor(@PathVariable@NotBlank int authorId){
		Author deletedAuthor = authorService.deleteAuthor(authorId);
		return new ResponseEntity<Author>(deletedAuthor,HttpStatus.OK);
	}
	
	@RequestMapping(path="/{authorId}/books", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>>readBooksByAuthor(@PathVariable@NotBlank int authorId){
		List <Book> books = authorService.readBooksByAuthor(authorId);
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	@RequestMapping(path="/{authorId}/books/{bookId}", method = {RequestMethod.PUT},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>addToBookAuthor(@PathVariable@NotBlank int authorId, @PathVariable@NotBlank int bookId){
		authorService.addAuthorFromBook(bookId, authorId);
		return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
	}
	@RequestMapping(path="/{authorId}/books/{bookId}", method = {RequestMethod.POST},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>postToBookAuthor(@PathVariable@NotBlank int authorId, @PathVariable@NotBlank int bookId){
		Book book = authorService.postAuthorFromBook(bookId, authorId);
		if(book==null)
			return new ResponseEntity<Book>(HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Book>(book, HttpStatus.ACCEPTED);
	}
	@RequestMapping(path="/{authorId}/books/{bookId}", method = {RequestMethod.DELETE},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>deleteBookAuthor(@PathVariable@NotBlank int authorId, @PathVariable@NotBlank int bookId){
		Book book = authorService.deleteAuthorFromBook(bookId, authorId);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

}
