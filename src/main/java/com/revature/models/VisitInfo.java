package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="visit_information")
public class VisitInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visit_id")
	private int vistId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	@Column(name = "visit_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-dd-MM")
	private Date date;
	
	@Column(name = "blood_pressure", length = 8)
	private String bloodpressure;
	@Column(name = "weight")
	private int weight;
	@Column(name = "doc_description")
	private String doctorDescription;
	@Column(name = "pat_notes")
	private String patientNote;
	public VisitInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VisitInfo(int vistId, Patient patient, Doctor doctor, Date date, String bloodpressure, int weight,
			String doctor_description, String patient_note) {
		super();
		this.vistId = vistId;
		this.patient = patient;
		this.doctor = doctor;
		this.date = date;
		this.bloodpressure = bloodpressure;
		this.weight = weight;
		this.doctorDescription = doctor_description;
		this.patientNote = patient_note;
	}
	@Override
	public String toString() {
		return "VisitInfo [vistId=" + vistId + ", patient=" + patient + ", doctor=" + doctor + ", date=" + date
				+ ", bloodpressure=" + bloodpressure + ", weight=" + weight + ", doctor_description="
				+ doctorDescription + ", patient_note=" + patientNote + "]";
	}
	public int getVistId() {
		return vistId;
	}
	public void setVistId(int vistId) {
		this.vistId = vistId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBloodpressure() {
		return bloodpressure;
	}
	public void setBloodpressure(String bloodpressure) {
		this.bloodpressure = bloodpressure;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDoctor_description() {
		return doctorDescription;
	}
	public void setDoctorDescription(String doctor_description) {
		this.doctorDescription = doctor_description;
	}
	public String getPatientNote() {
		return patientNote;
	}
	public void setPatientNote(String patient_note) {
		this.patientNote = patient_note;
	}
	
	
	
}
