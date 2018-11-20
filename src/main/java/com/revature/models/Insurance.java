package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="patient_insurance")
public class Insurance {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "insurance_id")
	private int insuranceId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Patient patient;
	@Column(name = "insurance_company")
	private String company;
	@Column(name = "insurance_group")
	private String group;
	@Column(name = "insurance_member_num")
	private String memberNumber;
	
	public Insurance() {
		super();
	}
	public Insurance(int insuranceId, Patient patient, String company, String group, String memberNumber) {
		super();
		this.insuranceId = insuranceId;
		this.patient = patient;
		this.company = company;
		this.group = group;
		this.memberNumber = memberNumber;
	}
	@Override
	public String toString() {
		return "Insurance [insuranceId=" + insuranceId + ", patient=" + patient + ", company=" + company + ", group="
				+ group + ", memberNumber=" + memberNumber + "]";
	}
	public int getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	
}
