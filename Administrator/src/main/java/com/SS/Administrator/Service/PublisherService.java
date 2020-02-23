/**
 * 
 */
package com.SS.Administrator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.Administrator.DAO.PublisherDAO;
import com.SS.Administrator.Entity.Publisher;

/**
 * @author acorb
 *
 */
@Component
public class PublisherService {
	@Autowired
	PublisherDAO pDao;
	
	public List<Publisher> readAllPublishers(){
		return pDao.findAll();
	}
}
