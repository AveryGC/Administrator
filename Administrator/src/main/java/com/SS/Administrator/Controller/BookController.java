package com.SS.Administrator.Controller;

import java.util.List;

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

import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Entity.Publisher;
import com.SS.Administrator.Service.BookService;

@RestController
@RequestMapping(path = "/admin/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>>getBooks(){
			List <Book> books = bookService.readAllBooks();
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{bookId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> getBook(@PathVariable @NotNull int bookId){
		Book book = bookService.findBookById(bookId);
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.POST, consumes ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> addBook(@RequestBody@Valid Book book){
		try {
			bookService.addBook(book);
			return new ResponseEntity<Book>(book,HttpStatus.CREATED);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Book>(book,HttpStatus.UNPROCESSABLE_ENTITY);
		} 
	}
	
	@RequestMapping(path = "/{bookId}", method = RequestMethod.PUT, consumes ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> updateBook(@RequestBody@Valid  Book book, @PathVariable@NotNull int bookId){
		if(bookId != book.getBookId())
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		bookService.updateBook(book);
		return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
	}
	@RequestMapping(path = "/{bookId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book> deleteBook(@PathVariable@NotNull int bookId){
		Book deletedBook = bookService.deleteBook(bookId);
		return new ResponseEntity<Book>(deletedBook, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{bookId}/genres", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Genre>> readGenresByBook(@PathVariable @NotNull int bookId){
			List<Genre> genres = bookService.findGenresByBook(bookId);
			return new ResponseEntity<List<Genre>>(genres,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{bookId}/genres/{genreId}" , method = RequestMethod.PUT,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> addGenreToBook(@PathVariable@NotNull int bookId, @PathVariable@NotNull int genreId){
		try {
			bookService.addGenreToBook(bookId, genreId);
			return new ResponseEntity<Genre>(HttpStatus.ACCEPTED);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path = "/{bookId}/genres/{genreId}" , method = {RequestMethod.POST},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> postGenreToBook(@PathVariable@NotNull int bookId, @PathVariable@NotNull int genreId){
			Genre genre = bookService.postGenreToBook(bookId, genreId);
			if(genre==null)
				return new ResponseEntity<Genre>(HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path = "/{bookId}/genres/{genreId}" , method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> deleteGenreFromBook(@PathVariable@NotNull int bookId, @PathVariable@NotNull int genreId){
		Genre genre = bookService.deleteGenreFromBook(bookId, genreId);
		return new ResponseEntity<Genre>(genre, HttpStatus.OK);
	}

	@RequestMapping(path = "/{bookId}/authors", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Author>> readAuthorByBook(@PathVariable@NotNull int bookId){
		List<Author> authors = bookService.findAuthorsByBook(bookId);
		return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{bookId}/authors/{authorId}" , method = {RequestMethod.PUT},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> addAuthorToBook(@PathVariable@NotNull int bookId, @PathVariable@NotNull int authorId){
		bookService.addAuthorFromBook(bookId, authorId);
		return new ResponseEntity<Author>(HttpStatus.ACCEPTED); 
	}
	@RequestMapping(path = "/{bookId}/authors/{authorId}" , method = {RequestMethod.POST},produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> postAuthorToBook(@PathVariable@NotNull int bookId, @PathVariable@NotNull int authorId){
		Author author = bookService.postAuthorFromBook(bookId, authorId);
		if(author==null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Author>(author,HttpStatus.ACCEPTED);
	}	
	@RequestMapping(path = "/{bookId}/authors/{authorId}" , method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Author> deleteAuthorFromBook(@PathVariable@NotNull int bookId, @PathVariable@NotNull int authorId){
		Author author = bookService.deleteAuthorFromBook(bookId, authorId);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
	}
	
	@RequestMapping(path = {"/{bookId}/publisher", "/admin/books/{bookId}/publisher/{publisherId"},
			method = RequestMethod.GET,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> getPublisherFromBook(@PathVariable@NotNull int bookId){
		Publisher publisher = bookService.findPublisherofBook(bookId);
		return new ResponseEntity<Publisher>(publisher , HttpStatus.OK);
	}
//	
	@RequestMapping(path = {"/{bookId}/publisher/{publisherId"}, method = {RequestMethod.PUT},
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Publisher> updatePublisher(@PathVariable@NotNull int bookId, @PathVariable@NotNull int publisherId){
		bookService.updatePublisherOfBook(bookId, publisherId);
		return new ResponseEntity<Publisher>(HttpStatus.ACCEPTED);
	}
	
}
