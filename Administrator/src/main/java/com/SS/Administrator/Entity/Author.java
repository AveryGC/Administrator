package com.SS.Administrator.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author acorb
 *
 */
@Entity
@Table(name = "tbl_author")
public class Author implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 8060541498424290774L;
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 5069100490580210474L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorID;
	@Column(name = "authorName")
    private String authorName;
	@ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
	@JsonBackReference
	List<Book> books;


    @Override
	public String toString() {
		return "Author [authorID=" + authorID + ", authorName=" + authorName + "]";
	}

	public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
    
    
    
    

}
