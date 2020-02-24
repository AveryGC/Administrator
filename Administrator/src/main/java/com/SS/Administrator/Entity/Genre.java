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
@Table(name = "tbl_genre")
public class Genre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 794379213453797431L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genre_id;
	
	@Column(name = "genre_name")
    private String genreName;
	
	@ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
	@JsonBackReference
	List<Book> books;
 
    public Integer getGenreId() {
        return genre_id;
    }

    public void setGenreID(Integer genreId) {
        this.genre_id = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
    
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Genre [genreID=" + genre_id + ", genreName=" + genreName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
		result = prime * result + ((genre_id == null) ? 0 : genre_id.hashCode());
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
		Genre other = (Genre) obj;
		if (genreName == null) {
			if (other.genreName != null)
				return false;
		} else if (!genreName.equals(other.genreName))
			return false;
		if (genre_id == null) {
			if (other.genre_id != null)
				return false;
		} else if (!genre_id.equals(other.genre_id))
			return false;
		return true;
	}
	
	
    
}
