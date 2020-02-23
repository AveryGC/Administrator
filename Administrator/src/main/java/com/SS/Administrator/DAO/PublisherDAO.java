package com.SS.Administrator.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.SS.Administrator.Entity.Publisher;

@Repository
@Component
public interface PublisherDAO extends JpaRepository<Publisher, Integer>{

}
