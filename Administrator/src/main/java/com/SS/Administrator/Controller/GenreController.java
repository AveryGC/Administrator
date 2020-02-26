/**
 * 
 */
package com.SS.Administrator.Controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Service.GenreService;


/**
 * @author acorb
 *
 */
@RestController
@RequestMapping(path = "/admin/genres")
public class GenreController {
	@Autowired
	private GenreService genreService;
	
	@RequestMapping(method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Genre>> getgenres(){
			List <Genre> genres = genreService.readAllGenres();
			return new ResponseEntity<List<Genre>>(genres,HttpStatus.OK);
	}
		
	@RequestMapping(path = "/{genreId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> getgenre(@PathVariable @NotBlank int genreId){
		try {
			Genre genre = genreService.findGenreById(genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);}
	}
	@RequestMapping( method = RequestMethod.POST, consumes ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> addgenre(@RequestBody Genre genre){
		try {
			genreService.addGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path = "/{genreId}", method = RequestMethod.PUT, consumes ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },produces ={
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> updategenre(@RequestBody@NotEmpty  Genre genre, @PathVariable@NotBlank int genreId){
		if(genreId != genre.getGenre_Id())
			return new ResponseEntity<Genre>(HttpStatus.BAD_REQUEST);
		genreService.updateGenre(genre);
		return new ResponseEntity<Genre>(HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{genreId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Genre> deletegenre(@PathVariable@NotBlank int genreId){
		Genre genre =genreService.deleteGenre(genreId);
		return new ResponseEntity<Genre>(genre,HttpStatus.OK); 
	}
	
	@RequestMapping(path="/{genreId}/books",produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Book>>readBooksBygenre(@PathVariable @NotBlank int genreId){
		List <Book> books = genreService.readBooksInGenre(genreId);
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@RequestMapping(path="/{genreId}/books/{bookId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>deleteBookFromGenre(@PathVariable @NotBlank int genreId, @PathVariable@NotBlank int bookId){
		Book book = genreService.deleteGenreFromBook(bookId, genreId);
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	@RequestMapping(path="/{genreId}/books/{bookId}", method = RequestMethod.PUT,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>putBookFromGenre(@PathVariable@NotBlank int genreId, @PathVariable@NotBlank int bookId){
		genreService.addGenreToBook(bookId, genreId);
		return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
	}
	@RequestMapping(path="/{genreId}/books/{bookId}", method = RequestMethod.POST,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Book>postBookFromGenre(@PathVariable@NotBlank int genreId, @PathVariable@NotBlank int bookId){
		Book book = genreService.postGenreToBook(bookId, genreId);
		if(book==null)
			return new ResponseEntity<Book>(HttpStatus.CONFLICT);
		else
			return new ResponseEntity<Book>(book,HttpStatus.ACCEPTED);
	}
}
