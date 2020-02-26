/**
 * 
 */
package com.SS.Administrator.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SS.Administrator.Entity.Branch;

/**
 * @author acorb
 *
 */
@SpringBootTest
class BranchServiceTest {
	@Autowired
	private BranchService service;

	@Test
	void addFailTest() {
		try {
			Branch branch = new Branch();
			branch.setBranchId(null);
			service.addBranch(branch);
			fail();
		}catch(IllegalArgumentException e) {}
		try {
			Branch branch = new Branch();
			branch.setBranchAddress("fun");
			branch.setBranchId(null);
			service.addBranch(branch);
			fail();
		}catch(IllegalArgumentException e) {}
		try {
			Branch branch = new Branch();
			branch.setBranchName("not fun");
			branch.setBranchId(null);
			service.addBranch(branch);
			fail();
		}catch(IllegalArgumentException e) {}
		try {
			Branch branch = new Branch();
			branch.setBranchAddress("fake address");
			branch.setBranchName("test address");
			branch.setBranchId(10000);
			service.addBranch(branch);
			fail();
		}catch(IllegalArgumentException e) {}
	}
	@Test
	void updateFailTest() {
		try {
			Branch branch = new Branch();
			branch.setBranchId(-1);
			service.updateBranch(branch);
			fail();
		}catch(NoSuchElementException e) {}
		try {
			service.findBranchById(-10);
			fail();
		}catch(NoSuchElementException e) {}
	}
	
	@Test
	void testAddThenDelete() {
		Branch branch = new Branch();
		branch.setBranchAddress("fake address");
		branch.setBranchName("fake name");
		branch.setBranchId(null);
		service.addBranch(branch);
		assertNotNull(branch.getBranchId());
		
		//testfindById
		Branch foundById = service.findBranchById(branch.getBranchId());
		assertEquals(branch, foundById);
		
		//testUpdateSuccesful
		branch.setBranchName("legal name change");
		service.updateBranch(branch);
		
		//delete test subject
		service.deleteBranch(branch.getBranchId());
	}
	
	@Test
	void testReadAll() {
		assertNotEquals(0, service.readAllBranchs().size());
	}

}
