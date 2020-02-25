package com.SS.Administrator.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.Administrator.DAO.AuthorDAO;
import com.SS.Administrator.DAO.BookDAO;
import com.SS.Administrator.DAO.GenreDAO;
import com.SS.Administrator.DAO.PublisherDAO;
import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Entity.Publisher;

@Component
public class BookService {

	@Autowired
	private BookDAO bDao;
	@Autowired
	private AuthorDAO aDao;
	@Autowired
	private GenreDAO gDao;
	@Autowired
	private PublisherDAO pDao;
	
	public List<Book> readAllBooks(){
		return bDao.findAll();
	}
	public void updateBook(Book book)throws IllegalArgumentException, NoSuchElementException{
		if(book.getBookId()!=null&&book.getPublisher()!=null&&book.getTitle()!=null) {
			if(!bDao.existsById(book.getBookId()))
				throw new NoSuchElementException();
			Book returned = bDao.save(book);
			bDao.flush();
			book=returned;
		}else
			throw new IllegalArgumentException();
	}
	public void addBook(Book book) throws IllegalArgumentException {
		if(book.getPublisher()!=null&&book.getTitle()!=null) {
			if(book.getBookId()==null) {
				Book returned = bDao.save(book);
				bDao.flush();
				book=returned;
			}else {
				throw new IllegalArgumentException();
			}
		}else
			throw new IllegalArgumentException();
	}
	public Book deleteBook(int bookId) throws NoSuchElementException{
		Book deletedBook = bDao.findById(bookId).get();
		bDao.deleteById(bookId);
		bDao.flush();
		return deletedBook;
	}
	public Book findBookById(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get();
	}
	public List<Author> findAuthorsByBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getAuthors();
	}
	public Author deleteAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().remove(author)) {
			bDao.save(book);
			bDao.flush();
			return author;
		}
		else
			throw new NoSuchElementException();
	}
	public Author addAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().contains(author)) {
			return author;
		}
		else {
			book.getAuthors().add(author);
			bDao.save(book);
			bDao.flush();
			return author;
		}
	}
	public Author postAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().contains(author)) {
			return null;
		}
		else {
			book.getAuthors().add(author);
			bDao.save(book);
			bDao.flush();
			return author;
		}
	}
	public List<Genre> findGenresByBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getGenres();
	}
	public Genre deleteGenreFromBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre =gDao.findById(genreId).get();
		if(book.getGenres().remove(genre)) {
			bDao.save(book);
			bDao.flush();
			return genre;
		}else
			throw new NoSuchElementException();
	}
	public Genre addGenreToBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre = gDao.findById(genreId).get();
		if(book.getGenres().contains(genre))
			return genre;
		else {
			book.getGenres().add(genre);
			bDao.save(book);
			bDao.flush();
			return genre;
		}
	}
	public Genre postGenreToBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre = gDao.findById(genreId).get();
		if(book.getGenres().contains(genre))
			return null;
		else {
			book.getGenres().add(genre);
			bDao.save(book);
			bDao.flush();
			return genre;
		}
	}
	public Publisher findPublisherofBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getPublisher();
	}
	public Publisher updatePublisherOfBook(int bookId, int publisherId)throws NoSuchElementException{
		Publisher publisher = pDao.findById(publisherId).get();
		Book book = bDao.findById(bookId).get();
		if(book.getPublisher().equals(publisher))
			return publisher;
		else {
			book.setPublisher(publisher);
			bDao.save(book);
			bDao.flush();
			return publisher;
		}
	}

}
