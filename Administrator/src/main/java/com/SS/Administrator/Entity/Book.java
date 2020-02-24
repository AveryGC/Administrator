package com.SS.Administrator.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author acorb
 *
 */
@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6338124170695094872L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	@Column(name = "title")
    private String title;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbl_book_authors",
	inverseJoinColumns = @JoinColumn(name = "authorId"),
	joinColumns = @JoinColumn(name = "bookId"))
	@JsonManagedReference
	private List<Author> authors;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@Lazy(value = false)
	@JoinTable(name = "tbl_book_genres",
	joinColumns = @JoinColumn (name = "bookId"),
	inverseJoinColumns =  @JoinColumn (name= "genre_id"))
	@JsonManagedReference
	private List<Genre> genres;
	
	@ManyToOne(cascade = CascadeType.PERSIST)//(fetch = FetchType.EAGER)
	@JoinColumn(name = "pubId", referencedColumnName = "publisherId")
	@JsonManagedReference
	private Publisher publisher;
//    private Publisher publisher;

    public Integer getBookId() {
        return bookId;
    }

	public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

//	@Override
//	public String toString() {
//		return "Book [bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", genres=" + genres
//				+ ", publisher=" + publisher + "]";
//	}
}
