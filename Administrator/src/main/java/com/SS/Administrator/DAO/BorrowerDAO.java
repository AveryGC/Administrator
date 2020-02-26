package com.SS.Administrator.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.SS.Administrator.Entity.Borrower;

@Repository
@Component
public interface BorrowerDAO extends JpaRepository<Borrower, Integer>{

}
