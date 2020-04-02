package com.bjsxt.dataOut.entity;

import java.util.Date;

/**
 * 还款单
 * @author Lsenrong
 * @date Oct 31, 2017 2:24:26 PM
 * @Description: TODO(描述)
 */
public class ReturnOrderDetail {

	private String id;
	/**
	 * 还款单号
	 */
	private String returnOrderId;
	
	private String projectDetailId;
	 /**
     * 购买单
     */
    private String buyOrderId;
    /**
     * 业务id
     */
    private String businessId;
	
	/**
	 * 收款门店类型，0是消费门店，1是星和
	 */
	private int receiptShopType;
	/**
	 * 咨询人
	 */
	private String adviser;
	/**
	 * 光电美容师
	 */
	private String cosmetologist;
	private String cosmetologist2;
	private String cosmetologist3;
	
	/**
	 * 储值
	 */
    private Double store;
    /**
     * 刷卡
     */
    private Double pos;
    /**
     * 现金
     */
    private Double cash;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 是否作废
     */
    private Integer isFail;
    /**
     * 还款单类型：cooperation_project,product,project,set
     */
    private String type;
    /**
     * 还款单创建日期
     */
    private Date createDate;
    
    private Date returnDate;
    private Date beginReturnDate;
    private Date endReturnDate;
    /**
     * 购买单状态
     */
    private Integer status;
    /**
     * 会员卡号
     */
    private String cardNo;
    private String cardShop;
    /**
     * 会员姓名
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
     *  购买项编号
     */
    private String itemNo;
    /**
     *  购买项名称
     */
    private String itemName;
    /**
     * 项类型
     */
    private String itemType;
    /**
     * 购买数量
     */
    private Double itemNumber;
    private Double percent;
    
	
}
