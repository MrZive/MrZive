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

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiAddProjectTest extends BaseKangWangDao{
	
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
//				continue;
				throw new RuntimeException("手机号不存在"+phone);
			}
			
			checkAndAddProjdectInfo(zhaoMeiName, index, shopName, name, secondName, isSend, buyNumber, realPayment, owe,
					leftPayment, leftNumber, createDate, buyDate, createUser, remark, price, memberCard, "project");
			
		}
		getSession().commit();
		getSession().close();
	}



	public static boolean checkAndAddProjdectInfo(Map<String, NameToSystemName> zhaoMeiName, String index,
			String shopName, String name, String secondName, Integer isSend, int buyNumber, Double realPayment,
			Double owe, Double leftPayment, Integer leftNumber, Date createDate, Date buyDate, String createUser,
			String remark, double price, MemberCard memberCard,String type) {
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setName(name);
		List<ProjectInfo> projectList = getProjectInfo(projectInfo);
		if(projectList.size() == 0){
			//查询对应的名称
			if(zhaoMeiName.containsKey(name) && zhaoMeiName.get(name).getType().equals("project")){
				projectInfo.setName(zhaoMeiName.get(name).getNewName());
				projectList = getProjectInfo(projectInfo);
			}
			if(zhaoMeiName.get(name).getType().equals("pass")){
				return true;
			}
		}
		
		if(projectList.size() > 0){//项目处理逻辑
			ProjectInfo info = projectList.get(0);
			Shop shop = getShop(new Shop(){{setName(shopName);}}).get(0);
			
			Integer serviceTime = getProjectServiceTime(info.getId(),shop.getId());
			
			addZhaoMeiProjectDetail(index, memberCard.getId(), shop.getId(), info.getId(), secondName, price, buyNumber, leftNumber, serviceTime,0D,realPayment,0D,owe,isSend,remark, createUser,buyDate,createDate,type);
			
		
			MemberCard change = new MemberCard();
			change.setId(memberCard.getId());
			change.setTreatmentBalance(memberCard.getTreatmentBalance()+leftPayment);
			change.setOweBalance(memberCard.getOweBalance()+owe);
			updateMemberCard(change);
			return true;
		}else{
//			throw new RuntimeException("找不到对应的信息:" + name);
			if(type.equals("project")){
				System.out.println("找不到对应的信息:" + name);
			}
			return false;
		}
	}



	static public int addZhaoMeiProjectDetail(String index,String memberCardId,String shopId,String projectId,String secondName,Double price,Integer buyNumber,Integer number,Integer serviceTime,Double storePay,Double bankcardPay,Double cashPay,Double owe,Integer isSend,String remark,String createUser,Date buyTime,Date createDate,String type){
		ProjectDetailConsumption detail = new ProjectDetailConsumption();
		Date date = new Date();
		detail.setActivityId("");
		detail.setAliPay(0D);
		detail.setBuyType("ZhaoMei");
		detail.setChannelId(0);
		detail.setConsumptionId("ZhaoMei"+date.getTime());
		detail.setConsumptionProjectId("ZhaoMei"+date.getTime());
		detail.setConsumptionSetId("");
		detail.setCoupon("");
		detail.setCreateDate(buyTime);
		detail.setEndDate(null);
		detail.setId(UUID.randomUUID().toString());
		detail.setInvalidNumber(number);
		detail.setIsBook(owe>0?1:0);
		detail.setIsCashCoupon(null);
		detail.setIsCount(1);
		detail.setIsFail(0);
		detail.setIsPay(1);
		detail.setIsSend(isSend);
		detail.setIsTuoke(0);
		detail.setMarketPrice(0D);
		detail.setPointPay(0D);
		detail.setRemark("找美网数据录入系统，类型："+type+"，序号："+index+"，单号创建人："+createUser+"，购买时间："+sdf.format(buyTime)+"，备注："+remark);
		detail.setServiceType(0);
		detail.setShopId(shopId);
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
		
		double payment = setDoubleScale(price * buyNumber);
		detail.setPayment(payment);
		
		detail.setPrice(price);
		detail.setProjectId(projectId);
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setEffectiveEarn(detail.getRealPayment());
		detail.setSecondName(secondName);
		detail.setServiceTime(serviceTime);
		
		int addProjectDetailConsumption = ProjectSellDao.addProjectDetailConsumption(detail);
		if(addProjectDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		return addProjectDetailConsumption;
	}
}
