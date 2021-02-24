package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 匹配系统没有的项目名称
 * @author Administrator
 *
 */
public class BaseKangWangDao extends BaseDao{
	
	public static Map<String,NameToSystemName> getKangWangName() {
		File file = new File("F:\\公司数据\\操作数据\\康王店\\没对应的项目名称当前日期20210203.xlsx");
		
		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        //第一行为表头
		ExcelRow excelRow = null;
		List<ExcelCell> excelCells = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		Map<String,NameToSystemName> map = new HashMap<>();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String oldName = excelCells.get(0).getValue().toString();
			String newName = excelCells.get(2).getValue() == null ? null : excelCells.get(2).getValue().toString();
			String timeStr = excelCells.get(3).getValue() == null ? null : excelCells.get(3).getValue().toString();
			
			Double timeD = timeStr == null ? 0D : Double.valueOf(timeStr);
			
			if(!map.containsKey(oldName)){
				NameToSystemName entity = new NameToSystemName();
				entity.setOldName(oldName);
				entity.setNewName(newName);
				entity.setTime(timeD == null ? null : timeD.intValue());
				
				
				if(newName!=null && newName.length()>0){
					boolean flag = false;
					
					ProductInfo productInfo = new ProductInfo();
					productInfo.setName(newName);
					List<ProductInfo> productList = getProductInfo(productInfo);
					if(productList.size()>0){
						flag = true;
						entity.setType("product");
					}
					
					if(!flag){
						ProjectInfo projectInfo = new ProjectInfo();
						projectInfo.setName(newName);
						List<ProjectInfo> projectList = getProjectInfo(projectInfo);
						
						if(projectList.size()>0){
							flag = true;
							entity.setType("project");
						}
					}
					
					if(!flag){
						CooperationProject cooInfo = new CooperationProject();
						cooInfo.setName(newName);
						List<CooperationProject> cooList = getCooperationProject(cooInfo);
						
						if(cooList.size()>0){
							flag = true;
							entity.setType("coo");
						}
					}
					
					if(!flag){
//						System.out.println(entity.getNewName());
					}
				}
				
				map.put(oldName, entity);
			}
			
		}
		return map;
	}
	
	static public void addProjectDetail(){
		ProjectDetailConsumption detail = new ProjectDetailConsumption();
		detail.setActivityId(null);
		detail.setAliPay(0D);
		detail.setBankcardPay(bankcardPay);
		detail.setBuyNumber(buyNumber);
		detail.setBuyOwe(buyOwe);
		detail.setBuyType(buyType);
		detail.setCashPay(cashPay);
		detail.setChannelId(channelId);
		detail.setConsumptionId(consumptionId);
		detail.setConsumptionProjectId(consumptionProjectId);
		detail.setConsumptionSetId(consumptionSetId);
		detail.setCoupon(coupon);
		detail.setCreateDate(createDate);
		detail.setEffectiveEarn(effectiveEarn);
		detail.setEndDate(endDate);
		detail.setExperiencePrice(experiencePrice);
		detail.setId(id);
		detail.setInvalidNumber(invalidNumber);
		detail.setIsBook(isBook);
		detail.setIsCashCoupon(isCashCoupon);
		detail.setIsCount(isCount);
		detail.setIsFail(isFail);
		detail.setIsPay(isPay);
		detail.setIsSend(isSend);
		detail.setIsTuoke(isTuoke);
		detail.setMarketPrice(marketPrice);
		detail.setMemberCardId(memberCardId);
		detail.setNumber(number);
		detail.setOwe(owe);
		detail.setPayment(payment);
		detail.setPointPay(pointPay);
		detail.setPrice(price);
		detail.setProjectId(projectId);
		detail.setPromotionPrice(promotionPrice);
		detail.setRealPayment(realPayment);
		detail.setRemark(remark);
		detail.setSecondName(secondName);
		detail.setServiceTime(serviceTime);
		detail.setServiceType(serviceType);
		detail.setShopId(shopId);
		detail.setStorePay(storePay);
		detail.setWechatPay(wechatPay);
	}
	
	
	static public List<Map<String,Object>> getProjectDetailLeft(String memberCardId){
		Map<String,Object> map = new HashMap<>();
		map.put("memberCardId", memberCardId);
		List<Map<String,Object>> selectList = getSession().selectList("com.zive.kangwang.getProjectDetailLeft", map);
		return selectList;
	}
	
	static public List<Map<String,Object>> getProductDetailLeft(String memberCardId){
		Map<String,Object> map = new HashMap<>();
		map.put("memberCardId", memberCardId);
		List<Map<String,Object>> selectList = getSession().selectList("com.zive.kangwang.getProductDetailLeft", map);
		return selectList;
	}
}

class NameToSystemName {

	private String OldName;
	
	private String NewName;
	
	private Integer time;
	
	private String type;

	public String getOldName() {
		return OldName;
	}

	public void setOldName(String oldName) {
		OldName = oldName;
	}

	public String getNewName() {
		return NewName;
	}

	public void setNewName(String newName) {
		NewName = newName;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
