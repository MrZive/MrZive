package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.entity.NameToSystemName;
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
	
	public static String getSecondName(String oldName){
		if(oldName.contains("旧项目") || oldName.contains("免费项目")){
			if(oldName.contains("（")){
				String secondName = oldName.substring(oldName.indexOf("（")+1, oldName.lastIndexOf("）"));
				return secondName;
			}
		}
		return oldName;
	}
	
	public static Map<String,NameToSystemName> getKangWangName() {
		File file = new File("D:\\公司数据\\操作数据\\康王店\\没对应的项目名称当前日期20210203.xlsx");
		
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
						entity.setType("");
//						System.out.println(entity.getNewName());
					}
				}
				
				map.put(oldName, entity);
			}
			
		}
		return map;
	}
	
	public static Map<String,NameToSystemName> getZhaoMeiName() {
		File file = new File("D:\\公司数据\\操作数据\\找美网\\没对应的项目名称-找美网.xlsx");
		
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
			System.out.println(i);
			String newName = excelCells.get(1).getValue() == null ? null : excelCells.get(1).getValue().toString();
			
			if(newName==null || newName.length()==0 || newName.equals("0")){
				continue;
			}
			
			if(!map.containsKey(oldName)){
				NameToSystemName entity = new NameToSystemName();
				entity.setOldName(oldName);
				entity.setNewName(newName);
				
				
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
						List<String> list = Arrays.asList("储值补录(星和)","空白项目","哈曼尼脊柱","私密小精灵套盒","新项目上市免费体验","全脸胶原蛋白","皮肤检测","青花瓷浴袍(私人定制)","青花瓷水杯(私人定制)","艾条(私人定制)");
						if(list.contains(oldName)){
							flag = true;
							entity.setType("pass");
						}
					}
					
					if(!flag){
						entity.setType("");
//						System.out.println(entity.getNewName());
					}
				}
				
				map.put(oldName, entity);
			}
			
		}
		return map;
	}
	
	static public int failProjectDetail(String detailId){
		ProjectDetailConsumption info = ProjectSellDao.getProjectDetailConsumptionById(detailId);
		if(info == null){
			return 0;
		}
		String remark = "康王纸质档案录入系统，操作：作废";
		if(StringUtils.isNotBlank(info.getRemark())){
			remark = remark + "，原备注："+info.getRemark();
		}
		ProjectDetailConsumption change = new ProjectDetailConsumption();
		change.setId(info.getId());
		change.setIsFail(2);
		change.setRemark(remark);
		return ProjectSellDao.updateProjectDetailConsumption(change);
	}
	
	static public int addProjectDetail(String memberCardId,String projectId,String secondName,Double price,Integer buyNumber,Integer number,Integer serviceTime,String remark){
		 return addProjectDetail(memberCardId, projectId, secondName, price, buyNumber, number, serviceTime, 0D, 0D, 0D, 0D, remark);
	}
	
	static public int addProjectDetail(String memberCardId,String projectId,String secondName,Integer buyNumber,Integer number,Integer serviceTime,String remark){
		 return addProjectDetail(memberCardId, projectId, secondName, 0D, buyNumber, number, serviceTime, 0D, 0D, 0D, 0D, remark);
	}
	
	static public int addProjectDetail(String memberCardId,String projectId,String secondName,Double price,Integer buyNumber,Integer number,Integer serviceTime,Double storePay,Double bankcardPay,Double cashPay,Double owe,String remark){
		ProjectDetailConsumption detail = new ProjectDetailConsumption();
		Date date = new Date();
		detail.setActivityId("");
		detail.setAliPay(0D);
		detail.setBuyType("init");
		detail.setChannelId(0);
		detail.setConsumptionId("Init"+date.getTime());
		detail.setConsumptionProjectId("Init"+date.getTime());
		detail.setConsumptionSetId("");
		detail.setCoupon("");
		detail.setCreateDate(date);
		detail.setEffectiveEarn(null);
		detail.setEndDate(null);
		detail.setId(UUID.randomUUID().toString());
		detail.setInvalidNumber(null);
		detail.setIsBook(0);
		detail.setIsCashCoupon(null);
		detail.setIsCount(1);
		detail.setIsFail(0);
		detail.setIsPay(1);
		detail.setIsSend(0);
		detail.setIsTuoke(0);
		detail.setMarketPrice(0D);
		detail.setPointPay(0D);
		detail.setRemark("康王纸质档案录入系统，新方式，操作：新增，备注："+remark);
		detail.setServiceType(0);
		detail.setShopId("110103");
		detail.setWechatPay(0D);
		detail.setExperiencePrice(0D);
		detail.setPromotionPrice(0D);
		
		detail.setStorePay(storePay);
		detail.setBankcardPay(bankcardPay);
		detail.setBuyNumber(buyNumber);
		detail.setBuyOwe(owe);
		detail.setCashPay(cashPay);
		detail.setMemberCardId(memberCardId);
		detail.setNumber(number);
		detail.setOwe(owe);
		detail.setPayment(price * buyNumber);
		detail.setPrice(price);
		detail.setProjectId(projectId);
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setSecondName(secondName);
		detail.setServiceTime(serviceTime);
		
		int addProjectDetailConsumption = ProjectSellDao.addProjectDetailConsumption(detail);
		if(addProjectDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		return addProjectDetailConsumption;
	}
	
	
	static public int failProductDetail(String detailId){
		ProjectDetailConsumption info = ProjectSellDao.getProjectDetailConsumptionById(detailId);
		if(info == null){
			return 0;
		}
		String remark = "康王纸质档案录入系统，操作：作废";
		if(StringUtils.isNotBlank(info.getRemark())){
			remark = remark + "，原备注："+info.getRemark();
		}
		ProductDetailConsumption change = new ProductDetailConsumption();
		change.setId(info.getId());
		change.setIsFail(2);
		change.setRemark(info.getRemark());
		return ProductSellDao.updateProductDetailConsumption(change);
	}
	
	static public int addProductDetail(String memberCardId,String productId,Integer buyNumber,Integer leftNumber,Double price,String unit,String remark){
		Date date = new Date();
		String consumptionId = "Init"+date.getTime();
		ProductDetailConsumption detail = new ProductDetailConsumption();
		detail.setActivityId("");
		detail.setActualNumber(leftNumber);
		detail.setActualPrice(price);
		detail.setActualUnit(unit);
		detail.setBankCardPay(0D);
		detail.setBuyNumber(buyNumber);
		detail.setBuyOwe(0D);
		detail.setBuyType("Init");
		detail.setBuyUnit(unit);
		detail.setCashPay(0D);
		detail.setChannelId(0);
		detail.setConsumptionId(consumptionId);
		detail.setConsumptionProductId(consumptionId);
		detail.setConsumptionSetId(null);
		detail.setCoupon("");
		detail.setCreateDate(date);
		detail.setEffectiveEarn(leftNumber * price);
		detail.setExperiencePrice(0D);
		detail.setExpressType(null);
		detail.setId(UUID.randomUUID().toString());
		detail.setIntroducer(null);
		detail.setIsBook(0);
		detail.setIsFail(0);
		detail.setIsIntroduce(0);
		detail.setIsPay(1);
		detail.setIsSend(0);
		detail.setLeftNumber(leftNumber);
		detail.setLeftUnit(unit);
		detail.setMarketPrice(0D);
		detail.setMemberCardId(memberCardId);
		detail.setOwe(0D);
		detail.setPayment(price * buyNumber);
		detail.setPrice(price);
		detail.setProductId(productId);
		detail.setPromotionPrice(0D);
		detail.setRealPayment(0D);
		detail.setRemark(remark);
		detail.setShopId("110103");
		detail.setStorePay(0d);
		int addProductDetailConsumption = ProductSellDao.addProductDetailConsumption(detail);
		if(addProductDetailConsumption==0){
			throw new RuntimeException("新增产品错误");
		}
		return addProductDetailConsumption;
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
	
	static public List<Map<String,Object>> getCooProjectDetailLeft(String memberCardId){
		Map<String,Object> map = new HashMap<>();
		map.put("memberCardId", memberCardId);
		List<Map<String,Object>> selectList = getSession().selectList("com.zive.kangwang.getCooProjectDetailLeft", map);
		return selectList;
	}
	
	
	
	static public int addCooperationConsumption(String memberCardId,String cooperationId,Double price,Integer buyNumber,Integer leftNumber,Double storePay,Double bankCardPay,Double cashPay,Double owe,String remark){
		Date date = new Date();
		String consumptionId = "Init"+date.getTime();
		ProjectCooperationConsumption detail = new ProjectCooperationConsumption();
		detail.setActivityId("");
		detail.setAdviser("");
		detail.setBankCardPay(bankCardPay);
		detail.setBuyOwe(owe);
		detail.setBuyType("init");
		detail.setCashPay(cashPay);
		detail.setConsumptionId(consumptionId);
		detail.setCreateDate(date);
		detail.setEffectiveEarn(0D);
		detail.setId(consumptionId);
		detail.setIsBook(0);
		detail.setIsFail(0);
		detail.setMemberCardId(memberCardId);
		detail.setOwe(owe);
		detail.setPayment(buyNumber * price);
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setRemark("康王纸质档案录入系统，新方式，操作：新增，备注："+remark);
		detail.setShopid("110103");
		detail.setStatus(owe>0?1:0);
		detail.setStorePay(storePay);
		
		int addProjectCooperationDetailConsumption = ProjectCooperationSellDao.addProjectCooperationConsumption(detail);
		if(addProjectCooperationDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		
		int addCooperationDetail = addCooperationDetail(memberCardId, cooperationId, consumptionId, price, buyNumber, leftNumber);
		if(addCooperationDetail==0){
			throw new RuntimeException("新增项目错误");
		}
		
		
		addConsumption(memberCardId, consumptionId, date, 1);
		
		return addCooperationDetail;
	}
	
	static public int addConsumption(String memberCardId,String consumptionId,Date date,Integer isCooperation){
		Consumption detail = new Consumption();
		detail.setConsumptionDate(date);
		detail.setCreateDate(date);
		detail.setCreateUserId("100");
		detail.setFailDate(null);
		detail.setFailEarn(null);
		detail.setFailId(null);
		detail.setFailUserId(null);
		detail.setId(consumptionId);
		detail.setIsCooperation(isCooperation);
		detail.setIsDetailPay(1);
		detail.setIsLinkFail(0);
		detail.setIsOverFail(0);
		detail.setIsTuoke(0);
		detail.setMakerId("100");
		detail.setMemberCardId(memberCardId);
		detail.setOstype("");
		detail.setReceiptShopType(0);
		detail.setRegion("广州");
		detail.setShopId("110103");
		detail.setStatus(1);
		detail.setIsOnline(0);
		
		int addProjectCooperationDetailConsumption = addConsumption(detail);
		if(addProjectCooperationDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		return addProjectCooperationDetailConsumption;
	}
	
	static public int addCooperationDetail(String memberCardId,String cooperationId,String consumptionId,Double price,Integer buyNumber,Integer leftNumber){
		Date date = new Date();
		ProjectCooperationDetailConsumption detail = new ProjectCooperationDetailConsumption();
		detail.setActivityId("");
		detail.setBuyNumber(buyNumber);
		detail.setBuyType("init");
		detail.setChannelId(null);
		detail.setConsumptionCooperationId(consumptionId);
		detail.setConsumptionDate(date);
		detail.setConsumptionId(consumptionId);
		detail.setConsumptionSetId("");
		detail.setCooperationId(cooperationId);
		detail.setCoupon("");
		detail.setId(UUID.randomUUID().toString());
		detail.setIsFail(0);
		detail.setLeftNumber(leftNumber);
		detail.setLeftStoreEarn(0D);
		detail.setMemberCardId(memberCardId);
		detail.setPrice(price);
		detail.setShopid(null);
		detail.setSort(null);
		detail.setStatus(1);
		detail.setUnit("次");
		detail.setLeftEarn(0D);
		
		int addProjectCooperationDetailConsumption = ProjectCooperationSellDao.addProjectCooperationDetailConsumption(detail);
		if(addProjectCooperationDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		return addProjectCooperationDetailConsumption;
	}
	
	
	public static int getProjectDoneNumber(String detailId){
		Map<String,Object> map = new HashMap<>();
		map.put("detailId", detailId);
		map.put("isFail", 0);
		int number = getSession().selectOne("com.zive.kangwang.getProjectDoneNumber", map);
		return number;
	}
	
	public static int getCooDoneNumber(String detailId){
		Map<String,Object> map = new HashMap<>();
		map.put("detailId", detailId);
		map.put("isFail", 0);
		int number = getSession().selectOne("com.zive.kangwang.getCooDoneNumber", map);
		return number;
	}
	
	
	public static Integer getProjectServiceTime(String projectId,String shopId){
		Map<String,Object> map = new HashMap<>();
		map.put("projectId", projectId);
		map.put("shopId", shopId);
		List<Integer> selectList = getSession().selectList("com.zive.kangwang.getProjectServiceTime", map);
		if(selectList.size()==0){
			map.remove("shopId");
			selectList = getSession().selectList("com.zive.kangwang.getProjectServiceTime", map);
		}
		
		Integer serviceTimeTemp = -1;
		if(selectList.size()>0){
			for (Integer serviceTime : selectList) {
				if(serviceTime == null){
					continue;
				}
				serviceTimeTemp = serviceTimeTemp==-1 ? serviceTime : serviceTimeTemp;
//				if(serviceTimeTemp != serviceTime){
//					throw new RuntimeException("找不到时长");
//				}
			}
		}
		if(serviceTimeTemp!=-1){
			return serviceTimeTemp;
		}
		return null;
	}
	
}