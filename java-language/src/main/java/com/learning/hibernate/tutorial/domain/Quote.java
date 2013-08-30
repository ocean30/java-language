package com.learning.hibernate.tutorial.domain;

/**
 *@author zhengzh
 *@create 2013-8-27
 **/
public class Quote {
	private Double bid ;
	private Double ask ;
	private String symbol ;
	// like 2013-05-10 11:12:13
	private String dateTime ;
	// from 1970-01-01 00:00:00.000
	private long millsecond ;
	
	public Quote(Double bid, Double ask, String symbol) {
		super();
		this.bid = bid;
		this.ask = ask;
		this.symbol = symbol;
	}
	public Double getBid() {
		return bid;
	}
	public void setBid(Double bid) {
		this.bid = bid;
	}
	public Double getAsk() {
		return ask;
	}
	public void setAsk(Double ask) {
		this.ask = ask;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public long getMillsecond() {
		return millsecond;
	}
	public void setMillsecond(long millsecond) {
		this.millsecond = millsecond;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote [bid=");
		builder.append(bid);
		builder.append(", ask=");
		builder.append(ask);
		builder.append(", symbol=");
		builder.append(symbol);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append(", millsecond=");
		builder.append(millsecond);
		builder.append("]");
		return builder.toString();
	}
	
}
