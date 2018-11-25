package com.revature.models;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Since many prescriptions may be loaded at a time, I decided to use the 
 * table per concrete class inheritance type to maximize the speed of data access;
 * the old prescriptions will be stored here, so that they do not need to be 
 * either pulled nor filtered through unless requested specifically.
 * @author Clay
 *
 */
@Entity
@Table(name = "prescriptions_archive")
public class PrescriptionArchive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Access(AccessType.PROPERTY)
	@Column(name = "rx_id")
	private int id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="start_date")
	private Date dateStarted;
	
	@Column(name="rx_name")
	private String name;
	@Column(name="rx_dose")
	private String dose;
	@Column(name="rx_freq")
	private String frequency;
	@Column(name="patient_id")
	private int patientId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="end_date")
	private Date dateEnded;

	/**
	 * Specific constructor used to create a new prescription archive.
	 * @param rx
	 * @param dateEnded
	 */
	public PrescriptionArchive(Prescription rx, Date dateEnded) {
		this.setId(rx.getId());
		this.setName(rx.getName());
		this.setDateStarted(rx.getDateStarted());
		this.setDose(rx.getDose());
		this.setFrequency(rx.getFrequency());
		this.setPatientId(rx.getPatientId());
		this.dateEnded = dateEnded;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(Date dateEnded) {
		this.dateEnded = dateEnded;
	}

	public PrescriptionArchive(int id, Date dateStarted, String name, String dose, String frequency, int patientId,
			Date dateEnded) {
		super();
		this.id = id;
		this.dateStarted = dateStarted;
		this.name = name;
		this.dose = dose;
		this.frequency = frequency;
		this.patientId = patientId;
		this.dateEnded = dateEnded;
	}

	public PrescriptionArchive() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PrescriptionArchive [id=" + id + ", dateStarted=" + dateStarted + ", name=" + name + ", dose=" + dose
				+ ", frequency=" + frequency + ", patientId=" + patientId + ", dateEnded=" + dateEnded + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateEnded == null) ? 0 : dateEnded.hashCode());
		result = prime * result + ((dateStarted == null) ? 0 : dateStarted.hashCode());
		result = prime * result + ((dose == null) ? 0 : dose.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + patientId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrescriptionArchive other = (PrescriptionArchive) obj;
		if (dateEnded == null) {
			if (other.dateEnded != null)
				return false;
		} else if (!dateEnded.equals(other.dateEnded))
			return false;
		if (dateStarted == null) {
			if (other.dateStarted != null)
				return false;
		} else if (!dateStarted.equals(other.dateStarted))
			return false;
		if (dose == null) {
			if (other.dose != null)
				return false;
		} else if (!dose.equals(other.dose))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patientId != other.patientId)
			return false;
		return true;
	}
}
