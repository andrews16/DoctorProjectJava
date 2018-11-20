package com.revature.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.revature.enums.UserRole;

@Entity
@PrimaryKeyJoinColumn(name = "doctor_id")
@Table(name = "doctors")
public class Doctor extends User {
//	
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "doc_specialty")
	private String specialty;
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor",
			cascade = {CascadeType.DETACH, CascadeType.MERGE})
	private Set<Patient> patients = new HashSet<>();

	public Doctor() {
		super();
		this.setRole(UserRole.DOCTOR);
	}
	
	public Doctor(int id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);
		this.setRole(UserRole.DOCTOR);

		// TODO Auto-generated constructor stub
	}

	public Doctor(Set<Patient> patients) {
		super();
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Doctor [patients=" + patients + "]";
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		return true;
	}
	
	
	
}
