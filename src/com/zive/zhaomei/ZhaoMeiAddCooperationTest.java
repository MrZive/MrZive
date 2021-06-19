package com.zive.zhaomei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiAddCooperationTest extends BaseKangWangDao{
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	final static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws IOException, ParseException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\项目资产.xls");
		
		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        //第一行为表头
		ExcelRow excelRow = null;
		List<ExcelCell> excelCells = null;
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		Map<String, NameToSystemName> zhaoMeiName = getZhaoMeiName();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			
			String index = excelCells.get(0).getValue().toString();
			String shopName = excelCells.get(1).getValue().toString();
			String phone = excelCells.get(5).getValue().toString();
			String name = excelCells.get(6).getValue().toString();
			String secondName = "";
			
			String isSendStr = excelCells.get(7).getValue().toString();
			Integer isSend = isSendStr.equals("赠送")?1:0;
			
			int buyNumber = Double.valueOf(excelCells.get(8).getValue().toString()).intValue();
			Double payment = Double.valueOf(excelCells.get(9).getValue().toString());
			Double realPayment = Double.valueOf(excelCells.get(10).getValue().toString());
			Double owe = payment - realPayment;
			Double leftPayment = Double.valueOf(excelCells.get(13).getValue().toString());
			
			Integer leftNumber = Double.valueOf(excelCells.get(17).getValue().toString()).intValue();
			Date createDate = sdf.parse(excelCells.get(18).getValue().toString());
			Date buyDate = sdf.parse(excelCells.get(19).getValue().toString());
			
			String createUser = excelCells.get(20).getValue().toString();
			String remark = excelCells.get(21).getValue()==null?"":excelCells.get(21).getValue().toString();
			
			
			double price = payment / buyNumber;
			price = setDoubleScale(price);
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				continue;
//				throw new RuntimeException("手机号不存在"+phone);
			}
			
			checkAndAddPCooperationInfo(zhaoMeiName, index, shopName, name, secondName, isSend, buyNumber, realPayment, owe,
					leftPayment, leftNumber, createDate, buyDate, createUser, remark, price, memberCard, "coo");
			
		}
		getSession().commit();
		getSession().close();
	}
	
	

	public static boolean checkAndAddPCooperationInfo(Map<String, NameToSystemName> zhaoMeiName, String index,
			String shopName, String name, String secondName, Integer isSend, int buyNumber, Double realPayment,
			Double owe, Double leftPayment, Integer leftNumber, Date createDate, Date buyDate, String createUser,
			String remark, double price, MemberCard memberCard,String type) {
		CooperationProject cooInfo = new CooperationProject();
		cooInfo.setName(name);
		List<CooperationProject> cooList = getCooperationProject(cooInfo);
		if(cooList.size() == 0){
			//查询对应的名称
			if(zhaoMeiName.containsKey(name) && zhaoMeiName.get(name).getType().equals("coo")){
				NameToSystemName nameToSystemName = zhaoMeiName.get(name);
				if(nameToSystemName.getType().equals("coo")){
					cooInfo.setName(nameToSystemName.getNewName());
					cooList = getCooperationProject(cooInfo);
				}
				if(zhaoMeiName.get(name).getType().equals("pass")){
					return true;
				}
			}
		}
		
		if(cooList.size() > 0){//项目处理逻辑
			CooperationProject info = cooList.get(0);
			Shop shop = getShop(new Shop(){{setName(shopName);}}).get(0);
		
			
			addZhaoMeiCooperationConsumption(index, memberCard.getId(), shop.getId(), info.getId(), secondName, price, buyNumber, leftNumber, 0,0D,realPayment,0D,owe,isSend,remark, createUser,buyDate,createDate,type);
			
		
			return true;
		}else{
//			throw new RuntimeException("找不到对应的信息:" + name);
			if(type.equals("coo")){
				System.out.println("找不到对应的信息:" + name);
			}
			return false;
		}
	}



	static public int addZhaoMeiCooperationConsumption(String index,String memberCardId,String shopId,String projectId,String secondName,Double price,Integer buyNumber,Integer number,Integer serviceTime,Double storePay,Double bankcardPay,Double cashPay,Double owe,Integer isSend,String remark,String createUser,Date buyTime,Date createDate,String type){
		Date date = new Date();
		String consumptionId = "ZhaoMei"+date.getTime();
		ProjectCooperationConsumption detail = new ProjectCooperationConsumption();
		detail.setActivityId("");
		detail.setAdviser("");
		detail.setBankCardPay(bankcardPay);
		detail.setBuyOwe(owe);
		detail.setBuyType("ZhaoMei");
		detail.setCashPay(cashPay);
		detail.setConsumptionId(consumptionId);
		detail.setCreateDate(buyTime);
		detail.setEffectiveEarn(0D);
		detail.setId(consumptionId);
		detail.setIsBook(owe>0?1:0);
		detail.setIsFail(0);
		detail.setMemberCardId(memberCardId);
		detail.setOwe(owe);
		detail.setPayment(setDoubleScale(buyNumber * price));
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setRemark("找美网数据录入系统，类型："+type+"，序号："+index+"，单号创建人："+createUser+"，购买时间："+sdf.format(buyTime)+"，备注："+remark);
		detail.setShopid(shopId);
		detail.setStatus(owe>0?1:0);
		detail.setStorePay(storePay);
		
		int addProjectCooperationDetailConsumption = ProjectCooperationSellDao.addProjectCooperationConsumption(detail);
		if(addProjectCooperationDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		
		int addCooperationDetail = addZhaoMeiCooperationDetail(memberCardId, projectId, consumptionId, price, buyNumber, number, buyTime);
		if(addCooperationDetail==0){
			throw new RuntimeException("新增项目错误");
		}
		
		return addCooperationDetail;
	}
	
	
	
	static public int addZhaoMeiCooperationDetail(String memberCardId,String cooperationId,String consumptionId,Double price,Integer buyNumber,Integer leftNumber, Date buyTime){
		ProjectCooperationDetailConsumption detail = new ProjectCooperationDetailConsumption();
		detail.setActivityId("");
		detail.setBuyNumber(buyNumber);
		detail.setBuyType("ZhaoMei");
		detail.setChannelId(null);
		detail.setConsumptionCooperationId(consumptionId);
		detail.setConsumptionDate(buyTime);
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
}
