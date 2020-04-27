package com.zive.pojo;

public class SatisfactionRatio {
	
	private Integer count;

	private String good;
	
	private String general;
	
	private String bad;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getBad() {
		return bad;
	}

	public void setBad(String bad) {
		this.bad = bad;
	}

	@Override
	public String toString() {
		return "SatisfactionRatio [count=" + count + ", good=" + good + ", general=" + general + ", bad=" + bad + "]";
	}
	
	
}
