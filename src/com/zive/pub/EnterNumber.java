package com.bjsxt.pub;

/**
 * 进店人数
 * @author Lsenrong
 * @date Sep 15, 2017 10:06:26 AM
 * @Description: TODO(描述)
 */
public class EnterNumber {
	/** 
     * 会员id
     */
	 private String cardId;
	 /**
	  * 会员卡号
	  */
	 private String cardNo;
	 /**
	  * 会员姓名
	  */
	 private String memberName;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
    
}
