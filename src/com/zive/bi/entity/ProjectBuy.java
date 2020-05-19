package com.zive.bi.entity;

import java.util.Date;
import java.util.List;

import com.zive.pojo.NewEarn;
/**
 * 购买项目
 * @author Lsenrong
 * @date Sep 14, 2017 4:03:53 PM
 * @Description: TODO(描述)
 */
public class ProjectBuy extends ProjectPayment{
	private String id;
	/**
	 * 业务id
	 */
	private String businessId;
	
	private List<NewEarn> earnList;
	
	/**
	 * 单号id
	 */
	private String orderId;
	/**
	 * 用于标记会员此单是否为有效业绩分享
	 */
	private Integer failEarn;
	/**
	 * 门店id
	 */
	private String shopId;
	/**
	 * 门店名称
	 */
	private String shopName;
	/**
	 * 活动id
	 */
	private String activityId;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 卡id
	 */
	private String cardId;
	/**
	 * 卡编号
	 */
    private String cardNo;
    private String cardShop;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 是否有效会员
     */
    private Integer isPass;
    /**
     * 成为有效会员时间
     */
    private Date passTime;
	/**
	 * 项目id
	 */
    private String projectId;
    /**
     * 项目编号
     */
    private String projectNo;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 购买截个
     */
    private Double projectPrice;
    /**
     * 购买次数
     */
    private Integer buyNumber;
    /**
     * 购买单价
     */
    private Double buyPrice;
    /**
     * 是否计次
     */
    private Integer isCount;
    /**
     * 是否赠送
     */
    private Integer isSend;
    /**
     * 购买日期
     */
    private Date consumeDate;
    private Date createDate;
   
	/**
     * 作废日期
     */
    private Date failDate;
    /**
     * 区域
     */
    private String area;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 优惠码
     */
    private String coupon;
    /**
     * 备注
     */
    private String remark;
    /**
     * 生日护理
     */
    private Integer isBirthday;
    /**
     * 是否现金券
     */
    private Integer isCashCoupon;
    /**
     * 做单人
     */
    private String makerName;
    
    public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getCardId() {
		return cardId;
	}
	public Double getProjectPrice() {
		return projectPrice;
	}
	public void setProjectPrice(Double projectPrice) {
		this.projectPrice = projectPrice;
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
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Integer getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Integer getIsCount() {
		return isCount;
	}
	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}
	public Integer getIsSend() {
		return isSend;
	}
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	public Date getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}
	public Date getFailDate() {
		return failDate;
	}
	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsBirthday() {
		return isBirthday;
	}
	public void setIsBirthday(Integer isBirthday) {
		this.isBirthday = isBirthday;
	}
	public Integer getIsCashCoupon() {
		return isCashCoupon;
	}
	public void setIsCashCoupon(Integer isCashCoupon) {
		this.isCashCoupon = isCashCoupon;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public Integer getFailEarn() {
		return failEarn;
	}
	public void setFailEarn(Integer failEarn) {
		this.failEarn = failEarn;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<NewEarn> getEarnList() {
		return earnList;
	}
	public void setEarnList(List<NewEarn> earnList) {
		this.earnList = earnList;
	}
	public String getCardShop() {
		return cardShop;
	}
	public void setCardShop(String cardShop) {
		this.cardShop = cardShop;
	}
	
}
