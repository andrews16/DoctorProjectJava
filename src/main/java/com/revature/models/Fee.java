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
@Table(name = "bill_fees")
public class Fee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fee_id", unique=true, nullable = false)
	private int feeId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bill_id", nullable=false)
	private Bill bill;
	@Column(name="fee_service")
	private String service;
	
	@Column(name="fee_price")
	private float price;

	public Fee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fee(String service, float price) {
		super();
		this.service = service;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Fee [feeId=" + feeId + ", bill=" + bill + ", service=" + service + ", price=" + price + "]";
	}

	public int getFeeId() {
		return feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
