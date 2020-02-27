package com.SS.Administrator.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void updateBook(Book book)throws NoSuchElementException{
		if(!bDao.existsById(book.getBookId()))
			throw new NoSuchElementException();
		Book returned = bDao.save(book);
		book=returned;
	}
	@Transactional
	public void addBook(Book book) throws IllegalArgumentException {
		if(book.getBookId()==null) {
			Book returned = bDao.save(book);
			book=returned;
		}else {
			throw new IllegalArgumentException();
		}
	}
	@Transactional
	public Book deleteBook(int bookId) throws NoSuchElementException{
		Book deletedBook = bDao.findById(bookId).get();
		bDao.deleteById(bookId);
		return deletedBook;
	}
	public Book findBookById(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get();
	}
	public List<Author> findAuthorsByBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getAuthors();
	}
	@Transactional
	public Author deleteAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().remove(author)) {
			bDao.save(book);
			return author;
		}
		else
			throw new NoSuchElementException();
	}
	@Transactional
	public Author addAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().contains(author)) {
			return author;
		}
		else {
			book.getAuthors().add(author);
			bDao.save(book);
			return author;
		}
	}
	@Transactional
	public Author postAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().contains(author)) {
			return null;
		}
		else {
			book.getAuthors().add(author);
			bDao.save(book);
			return author;
		}
	}
	public List<Genre> findGenresByBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getGenres();
	}
	@Transactional
	public Genre deleteGenreFromBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre =gDao.findById(genreId).get();
		if(book.getGenres().remove(genre)) {
			bDao.save(book);
			return genre;
		}else
			throw new NoSuchElementException();
	}
	@Transactional
	public Genre addGenreToBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre = gDao.findById(genreId).get();
		if(book.getGenres().contains(genre))
			return genre;
		else {
			book.getGenres().add(genre);
			bDao.save(book);
			return genre;
		}
	}
	@Transactional
	public Genre postGenreToBook(int bookId, int genreId)throws NoSuchElementException{
		Book book = bDao.findById(bookId).get();
		Genre genre = gDao.findById(genreId).get();
		if(book.getGenres().contains(genre))
			return null;
		else {
			book.getGenres().add(genre);
			bDao.save(book);
			return genre;
		}
	}
	public Publisher findPublisherofBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getPublisher();
	}
	@Transactional
	public Publisher updatePublisherOfBook(int bookId, int publisherId)throws NoSuchElementException{
		Publisher publisher = pDao.findById(publisherId).get();
		Book book = bDao.findById(bookId).get();
		if(book.getPublisher().equals(publisher))
			return publisher;
		else {
			book.setPublisher(publisher);
			bDao.save(book);
			return publisher;
		}
	}

}
