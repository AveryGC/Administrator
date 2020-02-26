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
import com.SS.Administrator.DAO.GenreDAO;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;

/**
 * @author acorb
 *
 */
@Component
@Transactional
public class GenreService {
	@Autowired
	private GenreDAO gDao;
	@Autowired
	private BookDAO bDao;
	
	public List<Genre> readAllGenres(){
		return gDao.findAll();
	}
	
	public void addGenre(Genre genre) throws IllegalArgumentException {
		if(genre.getGenreName()!=null) {
			if(genre.getGenre_Id()==null){
				Genre returned = gDao.save(genre);
				gDao.flush();
				genre= returned;
			}
			else 
				throw new IllegalArgumentException();
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public void updateGenre(Genre genre) throws  NoSuchElementException{
		if(!gDao.existsById(genre.getGenre_Id()))
			throw new NoSuchElementException();
		Genre returned = gDao.save(genre);
		gDao.flush();
		genre= returned;
	}
	
	public Genre deleteGenre(int genreId)throws NoSuchElementException {
		Genre deletedGenre = gDao.findById(genreId).get();
		deletedGenre.getBooks().forEach(b->{
			b.getGenres().remove(deletedGenre);
			if(b.getGenres().size()==0)
				bDao.delete(b);
			else
				bDao.save(b);
		});
		gDao.deleteById(genreId);
		bDao.flush();
		gDao.flush();
		return deletedGenre;
	}
	public Genre findGenreById(int genreId)throws NoSuchElementException{
		return gDao.findById(genreId).get();
	}
	public List<Book> readBooksInGenre(int genreId)throws NoSuchElementException{
		return gDao.findById(genreId).get().getBooks();
	}
	
	public List<Genre> findGenresByBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getGenres();
	}
	public Book deleteGenreFromBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre =gDao.findById(genreId).get();
		if(book.getGenres().remove(genre)) {
			bDao.save(book);
			bDao.flush();
			return book;
		}else
			throw new NoSuchElementException();
	}
	public Book addGenreToBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre = gDao.findById(genreId).get();
		if(book.getGenres().contains(genre))
			return book;
		else {
			book.getGenres().add(genre);
			bDao.save(book);
			bDao.flush();
			return book;
		}
	}
	public Book postGenreToBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre = gDao.findById(genreId).get();
		if(book.getGenres().contains(genre))
			return null;
		else {
			book.getGenres().add(genre);
			bDao.save(book);
			bDao.flush();
			return book;
		}
	}
	
}
