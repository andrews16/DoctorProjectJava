package com.revature.tests.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Prescription;
import com.revature.models.PrescriptionArchive;
import com.revature.repos.RxRepo;
import com.revature.services.RxService;

public class RxServicesTest {
	
	RxRepo rxRepo = mock(RxRepo.class);
	RxService rxService =  new RxService(rxRepo);
	
	/*
	 * Get custom BadRequest exception if there are no results
	 */
	@Test
	public void getListsNoResults() throws BadRequestException{
		int patientId = 20;
		when(rxRepo.getListById(patientId)).thenReturn(new ArrayList<Prescription>());
		assertEquals(rxService.getList(patientId), new ArrayList<Prescription>());
	}
	
	@Test
	public void getListsResults() throws BadRequestException {
		int patientId = 20;
		List<Prescription> fakeList = new ArrayList<>();
		fakeList.add(new Prescription());
		fakeList.add(new Prescription());
		when(rxRepo.getListById(patientId)).thenReturn(fakeList);
		assertNotNull(rxService.getList(patientId));
	}
	
	@Test
	public void getListsIdZero() throws BadRequestException {
		int patientId = 0;
		when(rxRepo.getListById(patientId)).thenReturn(new ArrayList<>());
		assertNotNull(rxService.getList(patientId));
	}
	
	@Test(expected=BadRequestException.class)
	public void getListsBadInput() throws BadRequestException {
		int patientId = -1;
		rxService.getList(patientId);
	}
	
	@Test(expected=BadRequestException.class)
	public void addRxMissingPatient() throws Exception {
		Prescription rx = new Prescription();
		rx.setDose("Dose");
		rx.setFrequency("Frequency");
		rx.setName("Name");
		rxService.addRx(rx);	
	}
	
	@Test(expected=BadRequestException.class)
	public void addRxMissingDose() throws Exception {
		Prescription rx = new Prescription();
		rx.setFrequency("Frequency");
		rx.setName("Name");
		rx.setPatientId(5);
		rxService.addRx(rx);	
	}
	
	@Test(expected=BadRequestException.class)
	public void addRxMissingName() throws Exception {
		Prescription rx = new Prescription();
		rx.setDose("Dose");
		rx.setFrequency("Frequency");
		rx.setPatientId(5);
		rxService.addRx(rx);	
	}
	
	@Test(expected=BadRequestException.class)
	public void addRxMissingFrequency() throws Exception {
		Prescription rx = new Prescription();
		rx.setDose("Dose");
		rx.setName("Name");
		rx.setPatientId(5);
		rxService.addRx(rx);	
	}
	
	@Test(expected=BadRequestException.class)
	public void addRxEmptyDose() throws Exception {
		Prescription rx = new Prescription();
		rx.setDose("");
		rx.setFrequency("Frequency");
		rx.setName("Name");
		rx.setId(5);
		rxService.addRx(rx);	
	}
	@Test(expected=BadRequestException.class)
	public void addRxEmptyFrequency() throws Exception {
		Prescription rx = new Prescription();
		rx.setDose("Dose");
		rx.setFrequency("");
		rx.setName("Name");
		rx.setId(5);
		rxService.addRx(rx);	
	}
	@Test(expected=BadRequestException.class)
	public void addRxEmptyName() throws Exception {
		Prescription rx = new Prescription();
		rx.setDose("Dose");
		rx.setFrequency("Frequency");
		rx.setName("");
		rx.setId(5);
		rxService.addRx(rx);	
	}
	
	@Test(expected=BadRequestException.class)
	public void removeRxBadId() throws BadRequestException {
		rxService.removeRx(0);
	}

	@Test
	public void getArchiveNoResults() throws BadRequestException{
		int patientId = 20;
		when(rxRepo.getArchive(patientId)).thenReturn(new ArrayList<PrescriptionArchive>());
		assertEquals(rxService.getArchive(patientId), new ArrayList<PrescriptionArchive>());
	}
	
	@Test
	public void getArchiveResults() throws Exception {
		int patientId = 20;
		List<PrescriptionArchive> fakeList = new ArrayList<>();
		fakeList.add(new PrescriptionArchive());
		fakeList.add(new PrescriptionArchive());
		when(rxRepo.getArchive(patientId)).thenReturn(fakeList);
		assertNotNull(rxService.getList(patientId));
	}
	
	@Test
	public void getArchiveIdZero() throws Exception {
		int patientId = 0;
		when(rxRepo.getListById(patientId)).thenReturn(new ArrayList<>());
		assertNotNull(rxService.getList(patientId));
	}
	
	
}
