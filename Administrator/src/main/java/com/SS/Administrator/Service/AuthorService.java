package com.SS.Administrator.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.bouncycastle.asn1.DLExternal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.SS.Administrator.DAO.AuthorDAO;
import com.SS.Administrator.DAO.BookDAO;
import com.SS.Administrator.Entity.Author;
import com.SS.Administrator.Entity.Book;


@Component
@Transactional
public class AuthorService {
	@Autowired
	private AuthorDAO aDao;
	@Autowired
	private BookDAO bDao;
	
	public List<Author> readAllAuthors(){
		return aDao.findAll();
	}
	
	public void addAuthor(Author author) throws IllegalArgumentException{
		if(author.getAuthorName()!=null) {
			if(author.getAuthorId()==null){
				Author returned =aDao.save(author);
				aDao.flush();
				author = returned;
				}
			else 
				throw new IllegalArgumentException();
		}else
			throw new IllegalArgumentException();
	}
	
	public void updateAuthor(Author author) throws IllegalArgumentException {
		if(!aDao.existsById(author.getAuthorId()))
			throw new NoSuchElementException();
		Author returned = aDao.save(author);
		aDao.flush();
		author = returned;
	}
	public Author deleteAuthor(int authorId) {
		Author deletedAuthor = aDao.findById( authorId).get();
		deletedAuthor.getBooks().forEach(b->{
			b.getAuthors().remove(deletedAuthor);
			if(b.getAuthors().size()==0)
				bDao.delete(b);
			else
				bDao.save(b);
		});
		aDao.deleteById(authorId);
		bDao.flush();
		aDao.flush();
		return deletedAuthor;
	}
	
	public Author findAuthorById(int authorId) throws NoSuchElementException{
		return aDao.findById(authorId).get();
	}
	
	public List<Book> readBooksByAuthor(int authorId) throws NoSuchElementException{
		return aDao.findById(authorId).get().getBooks();
	}
	
	public Book deleteAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().remove(author)) {
			bDao.save(book);
			bDao.flush();
			return book;
		}
		else
			throw new NoSuchElementException();
	}
	public Book addAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().contains(author)) {
			return book;
		}
		else {
			book.getAuthors().add(author);
			bDao.save(book);
			bDao.flush();
			return book;
		}
	}
	public Book postAuthorFromBook(int bookId, int authorId)throws NoSuchElementException {
		Book book = bDao.findById(bookId).get();
		Author author = aDao.findById(authorId).get();
		if(book.getAuthors().contains(author)) {
			return null;
		}
		else {
			book.getAuthors().add(author);
			bDao.save(book);
			bDao.flush();
			return book;
		}
	}
		
}
