package com.zive.updateMemberAssets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProjectConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;
import com.zive.util.CommonUtil;

/**
 * 匹配系统没有的项目名称
 * @author Administrator
 *
 */
public class UpdateMemberAssets extends BaseDao{
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat sdf_short = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\资产核对\\会员资产表2021.8.9-140万科城店助.xlsx");
		
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
		
		String sheetDate = excelSheet.getName();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			
			String orderId = excelCells.get(0).getValue().toString();
			String detailId = excelCells.get(1).getValue().toString();
			String phone = excelCells.get(2).getValue().toString();
			
			String type = excelCells.get(4).getValue().toString();
			String itemName = excelCells.get(6).getValue().toString();
			String serviceTime = excelCells.get(7).getValue().toString();
			
			Double price = Double.valueOf(excelCells.get(8).getValue().toString());
			
			Integer leftNumber = Integer.valueOf(excelCells.get(10).getValue().toString());
			Integer autalNumber = Integer.valueOf(excelCells.get(11).getValue().toString());
			
			String owe = excelCells.get(14).getValue().toString();
			String buyDate = excelCells.get(15).getValue().toString();
			String shopName = excelCells.get(16).getValue().toString();
			
			String remark = excelCells.get(17).getValue().toString();
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				throw new RuntimeException("手机号不存在");
			}
			Shop shop = getShop(new Shop(){{setName(shopName);}}).get(0);
			
			
			//添加
			if(StringUtils.isBlank(detailId)){
				addAssets(shop.getId(), phone, type, itemName, serviceTime, price, leftNumber, autalNumber, memberCard.getId(), remark);
				continue;
			}
			
			
			
			ProjectDetailConsumption projectDetailConsumption = ProjectSellDao.getProjectDetailConsumptionById(detailId);
			
			ProductDetailConsumption productDetailConsumption = ProductSellDao.getProductDetailConsumptionById(detailId);
			
			ProjectCooperationDetailConsumption cooperationDetailConsumption = ProjectCooperationSellDao.getProjectCooperationDetailConsumptionById(detailId);
			
			
			if(projectDetailConsumption != null){
				
			}
			
			if(productDetailConsumption != null){
				
			}
			
			if(cooperationDetailConsumption != null){
				
			}
			
		}
	}
	
	private static boolean checkProjectCanChange(String orderId,String buyId,String setId,String detailId,String unitTimeNow,String unitTime,double priceNow,double price,int buyNumberNow,int buyNumber,int leftNumberNow,int leftNumber,Double autalNumber,Double oweNow,Double owe,Double realPaymentNow,Double realPayment,String sheetDate) throws ParseException{
		if(!unitTimeNow.equals(unitTime)){
			throw new RuntimeException(detailId+":单位不一样");
		}
		if(priceNow != price){
			throw new RuntimeException(detailId+":单价不一样");
		}
		if(buyNumberNow != buyNumber){
			throw new RuntimeException(detailId+":购买价格不一样");
		}
		
		//定金单过滤
		if((owe!=null && owe>0) || (oweNow!=null && oweNow>0)){
			return false;
		}
		
		//不知道是否定金单情况下，查找父单
		if(owe==null && oweNow==null){
			if(StringUtils.isNotBlank(buyId)){
				ProjectConsumption detailCon = new ProjectConsumption();
				detailCon.setId(buyId);
				List<ProjectConsumption> projectConsumptionList = ProjectSellDao.getProjectConsumption(detailCon);
				for (ProjectConsumption projectConsumption : projectConsumptionList) {
					if(projectConsumption.getOwe() > 0){
						return false;
					}
				}
			}
			if(StringUtils.isNotBlank(setId)){
				ProjectConsumption detailCon = new ProjectConsumption();
				detailCon.setId(buyId);
				List<ProjectConsumption> projectConsumptionList = ProjectSellDao.getProjectConsumption(detailCon);
				for (ProjectConsumption projectConsumption : projectConsumptionList) {
					if(projectConsumption.getOwe() > 0){
						return false;
					}
				}
			}
		}
		
		
		boolean isLeft = false;
		boolean isAutal = false;
		
		if(leftNumberNow != leftNumber){
			//查找这几天有没有手工
			String startDate = sheetDate + " 00:00:00";
			String endDate = sheetDate + " 23:59:59";
			
			Date sheetEndDate = sdf.parse(startDate);
			
			ProjectDoneDetail doneDetail = new ProjectDoneDetail();
			doneDetail.setProjectDetailId(detailId);
			doneDetail.setIsFail(0);
			List<ProjectDoneDetail> projectDoneDetailList = ProjectDoneDao.getProjectDoneDetail(doneDetail);
			for (ProjectDoneDetail projectDoneDetail : projectDoneDetailList) {
				if(projectDoneDetail.getCreateDate().getTime() > sheetEndDate.getTime()){
					throw new RuntimeException(detailId+":最近有做过手工，系统："+leftNumberNow+"次，报表："+leftNumber+"次");
				}
			}
			isLeft = true;
		}
		
		Double autalLeftNumberNow = calculateAutalLeftNumber(oweNow, buyNumberNow, leftNumberNow, priceNow, realPaymentNow);
		if(autalLeftNumberNow != null && autalNumber != null){
			double bigDecimalNow = setDoubleScale(autalLeftNumberNow, 1);
			double bigDecimal = setDoubleScale(autalNumber, 1);
			if(bigDecimalNow != bigDecimal){
				isAutal = true;
			}
		}
		
		return false;
	}

	private static void addAssets(String shopId,String phone, String type, String itemName,String serviceTime, double price, int leftNumber,Integer autalNumber,String memberCardId,String remark) {
		// TODO Auto-generated method stub
		if(type.equals("项目")){
			UpdateMemberAssetsAddProjectTest.checkAndAddProjdectInfo(shopId, itemName, leftNumber, leftNumber, new Date(), remark, price, memberCardId, type);
		}else if(type.equals("产品")){
			UpdateMemberAssetsAddProductTest.checkAndAddProductInfo(shopId, itemName, leftNumber, leftNumber, new Date(), remark, price, memberCardId, type);
		}else if(type.equals("合作项目")){
			UpdateMemberAssetsAddCooperationTest.checkAndAddPCooperationInfo(shopId, itemName, leftNumber, leftNumber, new Date(), remark, price, memberCardId, type);
		}
	}
	
	
	
	private static Double calculateAutalLeftNumber(Double owe,int buyNumber,int leftNumber,Double priceDouble,Double realPaymentDouble) {
		// TODO Auto-generated method stub
		int doneNumebr = buyNumber - leftNumber;
		BigDecimal price = priceDouble == null ? null : new BigDecimal(priceDouble);
		BigDecimal realPayment = realPaymentDouble == null ? null : new BigDecimal(realPaymentDouble);
		
		if(owe == null || realPayment == null){
			return null;
		}
		
		if(owe.doubleValue()>0 && price.doubleValue()>0D){
			BigDecimal leftDonePrice = realPayment.subtract(price.multiply(new BigDecimal(doneNumebr)));
			return leftDonePrice.divide(price, 4, RoundingMode.HALF_UP).doubleValue();
		}else{
			return Double.valueOf(leftNumber);
		}
	}
	
	
}