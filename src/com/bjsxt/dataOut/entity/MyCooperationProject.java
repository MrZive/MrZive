package com.bjsxt.dataOut.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;

/**
 * 合作项目实体类
 */
@TableName("合作项目")
public class MyCooperationProject {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
	private String id;

	@FieldName("项目编号")
	private String no;

	@FieldName("项目名称")
	private String name;

	@FieldName("项目单位")
	private String unit;

	@FieldName("项目单价")
	private Double price;

	@FieldName("业绩比例")
	private Double percent;

	@FieldName("有效周期/年")
	private Double effectiveCycle;

	@FieldName("不限价格 选中此项不限单价，可随便填写")
	private Integer noLimitPrice;

	@FieldName("状态：0停用1正常，-1删除")
	private Integer status;

	@FieldName("是否是纹绣")
	private Integer isWenxiu;

	@FieldName("首次提成")
	private Double firstEarn;

	@FieldName("是否计入合作消耗：1是，0否")
	private Integer isCountComsume;

	@FieldName("是否计算工资：0不 1是")
	private Integer isCalculateSal;

	@FieldName("是否收款：0不 1是")
	private Integer isCome;

	@FieldName("是否计算消耗：0不 1是")
	private Integer isConsume;
	
	@FieldName("创建人ID")
	private String createUserId;

	@FieldName("创建时间")
	private String createDateTime;
	
	
	public void fromSuper(CooperationProject info){
		this.createUserId = info.getCreateUserId();
		this.effectiveCycle = info.getEffectiveCycle();
		this.firstEarn = info.getFirstEarn();
		this.id = info.getId();
		this.isCalculateSal = info.getIsCalculateSal();
		this.isCome = info.getIsCome();
		this.isConsume = info.getIsConsume();
		this.isCountComsume = info.getIsCountComsume();
		this.isWenxiu = info.getIsWenxiu();
		this.name = info.getName();
		this.no = info.getNo();
		this.noLimitPrice = info.getNoLimitPrice();
		this.percent = info.getPercent();
		this.price = info.getPrice();
		this.status = info.getStatus();
		this.unit = info.getUnit();
		
		if(info.getCreateDateTime()!=null){
			this.createDateTime = sf.format(info.getCreateDateTime());
		}
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getEffectiveCycle() {
		return effectiveCycle;
	}

	public void setEffectiveCycle(Double effectiveCycle) {
		this.effectiveCycle = effectiveCycle;
	}

	public Integer getNoLimitPrice() {
		return noLimitPrice;
	}

	public void setNoLimitPrice(Integer noLimitPrice) {
		this.noLimitPrice = noLimitPrice;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsWenxiu() {
		return isWenxiu;
	}

	public void setIsWenxiu(Integer isWenxiu) {
		this.isWenxiu = isWenxiu;
	}

	public Double getFirstEarn() {
		return firstEarn;
	}

	public void setFirstEarn(Double firstEarn) {
		this.firstEarn = firstEarn;
	}

	public Integer getIsCountComsume() {
		return isCountComsume;
	}

	public void setIsCountComsume(Integer isCountComsume) {
		this.isCountComsume = isCountComsume;
	}

	public Integer getIsCalculateSal() {
		return isCalculateSal;
	}

	public void setIsCalculateSal(Integer isCalculateSal) {
		this.isCalculateSal = isCalculateSal;
	}

	public Integer getIsCome() {
		return isCome;
	}

	public void setIsCome(Integer isCome) {
		this.isCome = isCome;
	}

	public Integer getIsConsume() {
		return isConsume;
	}

	public void setIsConsume(Integer isConsume) {
		this.isConsume = isConsume;
	}

}
