package com.SS.Administrator.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * @author acorb
 *
 */
@Entity
@Table(name = "tbl_publisher")
public class Publisher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2495782306085481780L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer publisherId;
	@Column(name = "publisherPhone")
    private String publisherPhone;
	@Column(name = "publisherName")
    private String publisherName;
	@Column(name = "publisherAddress")
    private String publisherAddress;
	
	@OneToMany(mappedBy = "publisher")
	@JsonBackReference
	private List<Book> books;
    
    

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }
   
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherPhone=" + publisherPhone + ", publisherName="
				+ publisherName + ", publisherAddress=" + publisherAddress + "]";
	}
    
}
