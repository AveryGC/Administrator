/**
 * 
 */
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

import com.SS.Administrator.Entity.Branch;
import com.SS.Administrator.Service.BranchService;

/**
 * @author acorb
 *
 */
@RestController
@RequestMapping(path = "/admin/branches")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@RequestMapping(method = RequestMethod.GET,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Branch>> getBranches(){
		List<Branch> branches = branchService.readAllBranchs();
		return new ResponseEntity<List<Branch>>(branches,HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST,produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Branch>addBranch(@RequestBody Branch branch){
		try {
			branchService.addBranch(branch);
			return new ResponseEntity<Branch>(branch,HttpStatus.CREATED);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<Branch> (HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(path="/{branchId}", method = RequestMethod.PUT,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Branch>updateBranch(@RequestBody@NotEmpty Branch branch,@NotBlank @PathVariable int branchId){
		if(branchId!=branch.getBranchId())
			return new ResponseEntity<Branch>(HttpStatus.BAD_REQUEST);
		branchService.updateBranch(branch);
		return new ResponseEntity<Branch>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path="/{branchId}", method = RequestMethod.GET,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Branch> findById(@PathVariable @NotBlank int branchId){
		Branch branch = branchService.findBranchById(branchId);
		return new ResponseEntity<Branch>(branch,HttpStatus.OK);
	}
	
	@RequestMapping(path="/{branchId}", method = RequestMethod.DELETE,produces ={
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Branch> deleteBranch(@PathVariable @NotBlank int branchId){
		branchService.deleteBranch(branchId);
		return new ResponseEntity<Branch>(HttpStatus.OK);
	}
	
	
	
	
	
}
