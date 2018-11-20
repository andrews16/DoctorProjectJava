package com.revature.tests.services;

import org.junit.Test;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.revature.daos.ReimbDao;
import com.revature.models.Prescription;
import com.revature.models.Reimbursement;
import com.revature.services.RxService;
import com.revature.util.exceptions.BadRequestException;

public class RxServicesTest {

	RxRepo rxRepo = mock(RxRepo.class);

	private RxService rxService = new RxService(rxRepo);

	@Test(expected=BadRequestException.class)
	public void getLists() throws Exception {
		when(rxRepo.getListById(patientId)).thenReturn(Arrays.asList({new Prescription()}));
		rxService.getList(20);
	}
	
}
