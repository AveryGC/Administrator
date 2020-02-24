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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorID == null) ? 0 : authorID.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (authorID == null) {
			if (other.authorID != null)
				return false;
		} else if (!authorID.equals(other.authorID))
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		return true;
	}
    
    
    
    

}
