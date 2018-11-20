package com.revature.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.enums.Gender;
import com.revature.enums.UserRole;

@Entity
@PrimaryKeyJoinColumn(name = "patient_id")
@Table(name = "patients")
public class Patient extends User{
//	private static UserRole role = UserRole.PATIENT; 
	
	@Column(name="patient_birthday", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
	private Date birthday;
	
	@Column(name="patient_gender")
	private Gender gender;
	@Column(name="patient_address")
	private String address;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", nullable = false)
	@JsonIgnore
	private Doctor doctor;
	@Transient
	private int doctorId;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<Prescription> prescriptions = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<Insurance> insurances = new HashSet<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
	private List<VisitInfo> visits = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
	private List<Bill> bills = new ArrayList<>();
	
	public Patient() {
		super();
		this.setRole(UserRole.PATIENT);
	}
	public Patient(int id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);
		this.setRole(UserRole.PATIENT);
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
		this.doctorId = doctor.getId();
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		if(this.doctor != null) {
			doctorId = doctor.getId();
		}
	}
	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	public Set<Insurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(Set<Insurance> insurances) {
		this.insurances = insurances;
	}
	public List<VisitInfo> getVisits() {
		return visits;
	}
	public void setVisits(List<VisitInfo> visits) {
		this.visits = visits;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	
}
