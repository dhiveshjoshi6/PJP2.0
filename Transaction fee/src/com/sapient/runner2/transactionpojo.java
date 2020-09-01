package com.sapient.runner2;

import java.util.Date;

public class transactionpojo {

	private String ext_trans_id;
	private String client_id;
	private String security_id;
	private String trans_type;
	private Date trans_date;
	private double market_value;
	private String priority_flag;
	public String getExt_trans_id() {
		return ext_trans_id;
	}
	public void setExt_trans_id(String ext_trans_id) {
		this.ext_trans_id = ext_trans_id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getSecurity_id() {
		return security_id;
	}
	public void setSecurity_id(String security_id) {
		this.security_id = security_id;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public Date getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(Date trans_date) {
		this.trans_date = trans_date;
	}
	public double getMarket_value() {
		return market_value;
	}
	public void setMarket_value(double market_value) {
		this.market_value = market_value;
	}
	public String getPriority_flag() {
		return priority_flag;
	}
	public void setPriority_flag(String priority_flag) {
		this.priority_flag = priority_flag;
	}
	public transactionpojo(String ext_trans_id, String client_id, String security_id, String trans_type,
			Date trans_date, double market_value, String priority_flag) {
		super();
		this.ext_trans_id = ext_trans_id;
		this.client_id = client_id;
		this.security_id = security_id;
		this.trans_type = trans_type;
		this.trans_date = trans_date;
		this.market_value = market_value;
		this.priority_flag = priority_flag;
	}
	@Override
	public String toString() {
		return "transactionpojo [ext_trans_id=" + ext_trans_id + ", client_id=" + client_id + ", security_id="
				+ security_id + ", trans_type=" + trans_type + ", trans_date=" + trans_date + ", market_value="
				+ market_value + ", priority_flag=" + priority_flag + "]";
	}
	
	
}
