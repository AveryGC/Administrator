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
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Service.GenreService;


/**
 * @author acorb
 *
 */
@RestController
public class GenreController {
	@Autowired
	GenreService genreService;
	
	@RequestMapping(path = "/admin/genres", method = RequestMethod.GET)
	public ResponseEntity<List<Genre>> getgenres(){
//		try {
			List <Genre> genres = genreService.readAllGenres();
			return new ResponseEntity<List<Genre>>(genres,HttpStatus.OK);
//		} catch (SQLException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<List<Genre>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	
//	@RequestMapping(path = "/admin/genres", method = {RequestMethod.DELETE, RequestMethod.PUT})
//	public ResponseEntity<String> genreMethodNotAllowed() {
//		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
//	}
//	
	@RequestMapping(path = "/admin/genres/{genreId}", method = RequestMethod.GET)
	public ResponseEntity<Genre> getgenre(@PathVariable int genreId){
		try {
			Genre genre = genreService.findGenreById(genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	@RequestMapping(path = {"/admin/genres"} , method = RequestMethod.POST)
	public ResponseEntity<Genre> addgenre(@RequestBody Genre genre){
		try {
			genreService.addGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.CONFLICT);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path = "/admin/genres/{genreId}", method = RequestMethod.PUT)
	public ResponseEntity<Genre> updategenre(@RequestBody Genre genre){
		try {
			genreService.updateGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
//			catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
//	
	@RequestMapping(path = "/admin/genres/{genreId}", method = RequestMethod.DELETE)
	public ResponseEntity<Genre> deletegenre(@PathVariable int genreId){
		try {
			Genre genre =genreService.deleteGenre(genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		} 
//	catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
//	
	@RequestMapping(path="/admin/genres/{genreId}/books")
	public ResponseEntity<List<Book>>readBooksBygenre(@PathVariable int genreId){
		try {
			List <Book> books = genreService.readBooksInGenre(genreId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		} 
//	catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
}
