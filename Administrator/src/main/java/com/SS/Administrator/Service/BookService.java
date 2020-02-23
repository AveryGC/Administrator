package com.SS.Administrator.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.Administrator.DAO.BookDAO;
import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;
import com.SS.Administrator.Entity.Genre;
import com.SS.Administrator.Entity.Publisher;

@Component
public class BookService {

	@Autowired
	private BookDAO bDao;
	
	public List<Book> readAllBooks(){
		return bDao.findAll();
	}
	public void updateBook(Book book){
		if(book.getBookId()!=null&&book.getPublisher()!=null&&book.getTitle()!=null) {
				Book returned = bDao.save(book);
				bDao.flush();
				book=returned;
		}
	}
	public void addBook(Book book) throws SQLException {
		if(book.getPublisher()!=null&&book.getTitle()!=null) {
			if(book.getBookId()!=null) {
				if(bDao.existsById(book.getBookId()))
					throw new SQLException();
			}else {
				Book returned = bDao.save(book);
				bDao.flush();
				book=returned;
			}
		}
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
	public List<Genre> findGenresByBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getGenres();
	}
	public Publisher findPublisherofBook(int bookId) throws NoSuchElementException{
		return bDao.findById(bookId).get().getPublisher();
	}

}
