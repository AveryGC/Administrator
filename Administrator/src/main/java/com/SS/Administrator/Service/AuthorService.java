package com.SS.Administrator.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.stereotype.Component;

import com.SS.Administrator.DAO.AuthorDAO;
import com.SS.Administrator.DAO.BookDAO;
import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;


@Component
public class AuthorService {
	@Autowired
	private AuthorDAO aDao;
	
	public List<Author> readAllAuthors(){
		return aDao.findAll();
	}
	
	public void addAuthor(Author author) throws IllegalArgumentException, SQLException{
		if(author.getAuthorName()!=null) {
			if(aDao.existsById(author.getAuthorID()))
				throw new SQLException();
			else {
				Author returned =aDao.save(author);
				aDao.flush();
				author = returned;
				}
		}else
			throw new IllegalArgumentException();
	}
	
	public void updateAuthor(Author author) throws IllegalArgumentException {
		if(author.getAuthorID()!=null && author.getAuthorName()!=null) {
			Author returned = aDao.save(author);
			aDao.flush();
			author = returned;
		}else
			throw new IllegalArgumentException();
	}
	public Optional<Author> deleteAuthor(int authorId) throws SQLException {
		if(aDao.existsById(authorId)) {
			Optional<Author> deletedAuthor = aDao.findById( authorId);
			aDao.deleteById(authorId);
			aDao.flush();
			return deletedAuthor;
		}else
			throw new SQLException();
	}
	
	public Author findAuthorById(int authorId) throws NoSuchElementException{
		return aDao.findById(authorId).get();
	}
	
	public List<Book> readBooksByAuthor(int authorId) throws NoSuchElementException{
		return aDao.findById(authorId).get().getBooks();
	}
	
		
}
