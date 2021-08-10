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

import org.apache.commons.lang.StringUtils;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProjectConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.SetConsumption;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.dataOut.java.SetSellDao;
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
public class UpdateMemberAssets extends BaseDao{
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat sdf_short = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File("E:\\公司数据\\操作数据\\资产核对\\会员资产表2021.8.9-140万科城店助.xls");
		
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
			String unitTime = excelCells.get(7).getValue()==null?null:excelCells.get(7).getValue().toString();
			
			Double price = Double.valueOf(excelCells.get(8).getValue().toString());
			Integer buyNumber = Double.valueOf(excelCells.get(9).getValue().toString()).intValue();
			
			Integer leftNumber = Double.valueOf(excelCells.get(10).getValue().toString()).intValue();
			Double autalNumber = excelCells.get(11).getValue()==null ? null : Double.valueOf(excelCells.get(11).getValue().toString());
			
			Double realPayment = excelCells.get(13).getValue()==null ? null : Double.valueOf(excelCells.get(13).getValue().toString());
			
			Double owe = excelCells.get(14).getValue()==null ? null : Double.valueOf(excelCells.get(14).getValue().toString());
			String buyDate = excelCells.get(15).getValue()==null ? null : excelCells.get(15).getValue().toString();
			String shopName = excelCells.get(16).getValue()==null ? "140万科城店" : excelCells.get(16).getValue().toString();
			
			String remark = excelCells.get(17).getValue()==null ? null : excelCells.get(17).getValue().toString();
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				throw new RuntimeException("手机号不存在");
			}
			Shop shop = getShop(new Shop(){{setName(shopName);}}).get(0);
			
			
			//添加
			if(StringUtils.isBlank(detailId)){
				addAssets(shop.getId(), phone, type, itemName, unitTime, price, leftNumber, autalNumber, memberCard.getId(), remark);
				continue;
			}
			
			
			
			ProjectDetailConsumption projectDetailConsumption = ProjectSellDao.getProjectDetailConsumptionById(detailId);
			
			ProductDetailConsumption productDetailConsumption = ProductSellDao.getProductDetailConsumptionById(detailId);
			
			ProjectCooperationDetailConsumption cooperationDetailConsumption = ProjectCooperationSellDao.getProjectCooperationDetailConsumptionById(detailId);
			
			
			if(projectDetailConsumption != null){
				UpdateMemberAssetsAddProjectTest.checkProjectCanChange(memberCard, projectDetailConsumption, unitTime, price, buyNumber, leftNumber, autalNumber, owe, realPayment, sheetDate, remark);
			}
			
			if(productDetailConsumption != null){
				
			}
			
			if(cooperationDetailConsumption != null){
				
			}
			
		}
		
//		getSession().commit();
		getSession().close();
	}
	

	private static void addAssets(String shopId,String phone, String type, String itemName,String serviceTime, double price, int leftNumber,Double autalNumber,String memberCardId,String remark) {
		// TODO Auto-generated method stub
		if(type.equals("项目")){
			UpdateMemberAssetsAddProjectTest.checkAndAddProjdectInfo(shopId, itemName, leftNumber, leftNumber, new Date(), remark, price, memberCardId, type);
		}else if(type.equals("产品")){
			UpdateMemberAssetsAddProductTest.checkAndAddProductInfo(shopId, itemName, leftNumber, leftNumber, new Date(), remark, price, memberCardId, type);
		}else if(type.equals("合作项目")){
			UpdateMemberAssetsAddCooperationTest.checkAndAddPCooperationInfo(shopId, itemName, leftNumber, leftNumber, new Date(), remark, price, memberCardId, type);
		}
	}
	
	
	
	public static Double calculateAutalLeftNumber(Double owe,int buyNumber,int leftNumber,Double priceDouble,Double realPaymentDouble) {
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
	
	public static boolean isInt(double a){
		double b = a;
		int b1 = (int)a;
		if(b % b1 == 0)
			return true;
		else
			return false;
	}
	
}