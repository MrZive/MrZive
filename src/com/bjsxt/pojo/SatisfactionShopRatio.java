package com.bjsxt.pojo;

import java.util.Date;

public class SatisfactionShopRatio implements Comparable<SatisfactionShopRatio>{

	//店面id
	private String shopId;
	
	private String no;

	private String shopName;
	
	private Integer count;
	
	private Integer good;
	
	private Integer general;
	
	private Integer bad;
	
	private String ratio;
	
	private Date startTime;
	
	private Date endTime;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getGood() {
		return good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	public Integer getGeneral() {
		return general;
	}

	public void setGeneral(Integer general) {
		this.general = general;
	}

	public Integer getBad() {
		return bad;
	}

	public void setBad(Integer bad) {
		this.bad = bad;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "SatisfactionShopRatio [shopId=" + shopId + ", no=" + no + ", shopName=" + shopName + ", count=" + count
				+ ", good=" + good + ", general=" + general + ", bad=" + bad + ", ratio=" + ratio + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

	@Override
	public int compareTo(SatisfactionShopRatio other) {
		String now=this.ratio==null?"0":this.ratio.length()==0?"0":this.ratio.substring(0,this.ratio.length()-1);
		String oth=other.getRatio()==null?"0":other.getRatio().length()==0?"0":other.getRatio().substring(0,other.getRatio().length()-1);
		if(Double.valueOf(now)>Double.valueOf(oth)){
			return 1;
		}else if(Double.valueOf(now)<Double.valueOf(oth)){
			return -1;
		}
		return 0;
	}

}
