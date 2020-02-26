package com.SS.Administrator.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.SS.Administrator.DAO.BorrowerDAO;
import com.SS.Administrator.Entity.Borrower;

@Component
@Transactional
public class BorrowerService {
	@Autowired
	private BorrowerDAO bDao;
	
	public List<Borrower> readAllBorrowers(){
		return bDao.findAll();
	}
	
	public void addBorrower(Borrower borrower)throws IllegalArgumentException{
		if(borrower.getAddress()!=null&&borrower.getName()!=null&&borrower.getPhone()!=null) {
			if(borrower.getCardNo()==null) {
				Borrower returned = bDao.save(borrower);
				bDao.flush();
				borrower = returned;
			}else
				throw new IllegalArgumentException();
		}else
			throw new IllegalArgumentException();
	}
	
	public void updateBorrower(Borrower borrower) throws NoSuchElementException{
		if(!bDao.existsById(borrower.getCardNo()))
			throw new NoSuchElementException();
		Borrower returned = bDao.save(borrower);
		bDao.flush();
		borrower = returned;
	}
	
	public Borrower deleteBorrower(int borrowerId)throws NoSuchElementException{
		Borrower deleBorrower = bDao.findById(borrowerId).get();
		bDao.deleteById(borrowerId);
		bDao.flush();
		return deleBorrower;
	}
	
	public Borrower findBorrowerById(int borrowerId) {
		return bDao.findById(borrowerId).get();
	}
	

}