package com.SS.Administrator.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.SS.Administrator.Entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Component
public interface AuthorDAO extends JpaRepository<Author,Integer> {

}
