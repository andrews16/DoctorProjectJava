package com.revature.tests.services;

import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Prescription;
import com.revature.repos.RxRepo;
import com.revature.services.RxService;

public class RxServicesTest {
	
	RxRepo rxRepo = mock(RxRepo.class);
	RxService rxService =  new RxService(rxRepo);
	
	/*
	 * Get custom BadRequest exception if there are no results
	 */
	@Test(expected=BadRequestException.class)
	public void getListsNoResults() throws Exception {
		//Mockito.
		int patientId = 20;
		when(rxRepo.getListById(patientId)).thenReturn(null);
		rxService.getList(patientId);
	}
	
	@Test
	public void getListsResults() throws BadRequestException {
		//Mockito.
		int patientId = 20;
		List<Prescription> fakeList = new ArrayList<>();
		fakeList.add(new Prescription());
		fakeList.add(new Prescription());
		when(rxRepo.getListById(patientId)).thenReturn(fakeList);
		rxService.getList(patientId);
	}
	
	
}
