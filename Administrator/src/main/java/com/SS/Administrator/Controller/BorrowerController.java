package com.SS.Administrator.Controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.Administrator.Entity.Borrower;
import com.SS.Administrator.Service.BorrowerService;

@RestController
@RequestMapping(path = "/admin/borrowers")
public class BorrowerController {
	@Autowired
	private BorrowerService borrowerService;
	
	@RequestMapping(method = RequestMethod.GET,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Borrower>> getBorrowers(){
		List<Borrower> borrowers = borrowerService.readAllBorrowers();
		return new ResponseEntity<List<Borrower>>(borrowers,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Borrower>addBorrower(@RequestBody Borrower borrower){
		try {
			borrowerService.addBorrower(borrower);
			return new ResponseEntity<Borrower>(borrower,HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Borrower> (HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@RequestMapping(path="/{borrowerId}", method = RequestMethod.PUT,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updateBorrower(@PathVariable@NotBlank int borrowerId,@RequestBody@NotEmpty  Borrower borrower){
		if(borrower.getCardNo()!=borrowerId)
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		borrowerService.updateBorrower(borrower);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path="/{borrowerId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Borrower> findById(@PathVariable@NotBlank int borrowerId){
		Borrower borrower= borrowerService.findBorrowerById(borrowerId);
		return new ResponseEntity<Borrower>(borrower,HttpStatus.OK);
	}
	
	@RequestMapping(path="/{borrowerId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable@NotBlank int borrowerId){
		borrowerService.deleteBorrower(borrowerId);
		return new ResponseEntity<Borrower>(HttpStatus.OK);
	}

}
