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

import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Entity.Publisher;
import com.SS.Administrator.Service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(path = "/admin/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>>getBooks(){
			List <Book> books = bookService.readAllBooks();
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/admin/books", method = {RequestMethod.DELETE, RequestMethod.PUT})
	public ResponseEntity<String> authorMethodNotAllowed() {
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@RequestMapping(path = "/admin/books/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable int bookId){
		try {
			Book book = bookService.findBookById(bookId);
			return new ResponseEntity<Book>(book,HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);}
	}
	
	@RequestMapping(path = {"/admin/books"}, method = RequestMethod.POST)
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		try {
			bookService.addBook(book);
			return new ResponseEntity<Book>(book,HttpStatus.OK);
		}catch(SQLException e) {
			return new ResponseEntity<Book>(book,HttpStatus.CONFLICT);
		} 
	}
	
	@RequestMapping(path = "/admin/books/{bookId}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
			bookService.updateBook(book);
			return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
	}
	@RequestMapping(path = "/admin/books/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBook(@PathVariable int bookId){
		try {
			Book deletedBook = bookService.deleteBook(bookId);
			return new ResponseEntity<Book>(deletedBook, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
			}
	}
	
	@RequestMapping(path = "/admin/books/{bookId}/genres", method = RequestMethod.GET)
	public ResponseEntity<List<Genre>> readGenresByBook(@PathVariable int bookId){
		try {
			List<Genre> genres = bookService.findGenresByBook(bookId);
			return new ResponseEntity<List<Genre>>(genres,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<List<Genre>>(HttpStatus.NOT_FOUND);
			}
	}
	
	@RequestMapping(path = "/admin/books/{bookId}/genres/{genreId}" , method = RequestMethod.PUT)
	public ResponseEntity<Genre> addGenreToBook(@PathVariable int bookId, @PathVariable int genreId){
		try {
			Genre genre = bookService.addGenreToBook(bookId, genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		} 
	}
	@RequestMapping(path = "/admin/books/{bookId}/genres/{genreId}" , method = {RequestMethod.POST})
	public ResponseEntity<Genre> postGenreToBook(@PathVariable int bookId, @PathVariable int genreId){
		try {
			Genre genre = bookService.postGenreToBook(bookId, genreId);
			if(genre==null)
				return new ResponseEntity<Genre>(HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path = "/admin/books/{bookId}/genres/{genreId}" , method = RequestMethod.DELETE)
	public ResponseEntity<Genre> deleteGenreFromBook(@PathVariable int bookId, @PathVariable int genreId){
		try {
			Genre genre = bookService.deleteGenreFromBook(bookId, genreId);
			return new ResponseEntity<Genre>(genre, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path = "/admin/books/{bookId}/authors", method = RequestMethod.GET)
	public ResponseEntity<List<Author>> readAuthorByBook(@PathVariable int bookId){
		try {
			List<Author> authors = bookService.findAuthorsByBook(bookId);
			return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<List<Author>>(HttpStatus.NOT_FOUND);}
	}
	
	@RequestMapping(path = "/admin/books/{bookId}/authors/{authorId}" , method = {RequestMethod.PUT})
	public ResponseEntity<Author> addAuthorToBook(@PathVariable int bookId, @PathVariable int authorId){
		try {
			Author author = bookService.addAuthorFromBook(bookId, authorId);
			return new ResponseEntity<Author>(author,HttpStatus.ACCEPTED);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} 
	}
	@RequestMapping(path = "/admin/books/{bookId}/authors/{authorId}" , method = {RequestMethod.POST})
	public ResponseEntity<Author> postAuthorToBook(@PathVariable int bookId, @PathVariable int authorId){
		try {
			Author author = bookService.postAuthorFromBook(bookId, authorId);
			if(author==null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			else
				return new ResponseEntity<Author>(author,HttpStatus.ACCEPTED);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} 
	}	
	@RequestMapping(path = "/admin/books/{bookId}/authors/{authorId}" , method = RequestMethod.DELETE)
	public ResponseEntity<Author> deleteAuthorFromBook(@PathVariable int bookId, @PathVariable int authorId){
		try {
			Author author = bookService.deleteAuthorFromBook(bookId, authorId);
			return new ResponseEntity<Author>(author,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path = {"/admin/books/{bookId}/publisher", "/admin/books/{bookId}/publisher/{publisherId"}, method = RequestMethod.GET)
	public ResponseEntity<Publisher> getPublisherFromBook(@PathVariable int bookId){
		try {
			Publisher publisher = bookService.findPublisherofBook(bookId);
			return new ResponseEntity<Publisher>(publisher , HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);}
	}
//	
	@RequestMapping(path = {"/admin/books/{bookId}/publisher/{publisherId"}, method = {RequestMethod.PUT, RequestMethod.POST})
	public ResponseEntity<Publisher> updatePublisher(@PathVariable int bookId, @PathVariable int publisherId){
		try {
			Publisher pub = bookService.updatePublisherOfBook(bookId, publisherId);
			return new ResponseEntity<Publisher>(pub, HttpStatus.ACCEPTED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path= {"/Admin/books/{bookId}/publisher", "/Admin/books/{bookId}/publisher/{publisherId"}, method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePublisher(){
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}
}
