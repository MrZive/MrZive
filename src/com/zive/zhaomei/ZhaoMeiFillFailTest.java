package com.zive.zhaomei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zive.bi.entity.CooperationBuy;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiFillFailTest extends BaseKangWangDao{

public static void main(String[] args) throws IOException, ParseException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\套餐合并 - 修复作废.xlsx");
		
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
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
//		Map<String, NameToSystemName> zhaoMeiName = getZhaoMeiName();
	//	excelSheet.getRows().size()
		for(int i = 1; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			
			String index = excelCells.get(0).getValue().toString();
			String phone = excelCells.get(3).getValue().toString();
			Double leftNumber = Double.valueOf(excelCells.get(5).getValue().toString());
			
			Double realPayment = Double.valueOf(excelCells.get(9).getValue().toString());
			realPayment = setDoubleScale(realPayment);
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			
			//项目
			ProjectDetailConsumption projectDetailCon = new ProjectDetailConsumption();
			projectDetailCon.setMemberCardId(memberCard.getId());
			projectDetailCon.setRemark("序号："+index+"，单号创建人");
			List<ProjectDetailConsumption> projectDetailConsumption = ProjectSellDao.getProjectDetailConsumption(projectDetailCon);
			if(projectDetailConsumption.size() > 0){
				ProjectDetailConsumption detail = projectDetailConsumption.get(0);
				if(detail.getNumber() != leftNumber.intValue()){
					throw new RuntimeException(index + "：projectprojectprojectprojectprojectprojectproject："+memberCard.getId());
				}
				
				if(!detail.getBankcardPay().equals(realPayment)){
					throw new RuntimeException(index + "：projectprojectprojectprojectprojectprojectproject："+memberCard.getId());
				}
				
				
				ProjectDetailConsumption changeDetail = new ProjectDetailConsumption();
				changeDetail.setId(detail.getId());
				changeDetail.setIsFail(1);
				changeDetail.setRemark(detail.getRemark() + "；找美网提供的数据包含了作废的单却没给个标识，所以系统需要再次修复数据");
				ProjectSellDao.updateProjectDetailConsumption(changeDetail);
				
				Consumption changeTop = new Consumption();
				changeTop.setId(detail.getConsumptionId());
				changeTop.setStatus(2);
				updateConsumption(changeTop);
				
				MemberCard change = new MemberCard();
				change.setId(memberCard.getId());
				change.setTreatmentBalance(memberCard.getTreatmentBalance() - detail.getBankcardPay());
				change.setOweBalance(memberCard.getOweBalance() - detail.getOwe());
				updateMemberCard(change);
				continue;
			}
			
			
			//产品
			ProductDetailConsumption productDetailCon = new ProductDetailConsumption();
			productDetailCon.setMemberCardId(memberCard.getId());
			productDetailCon.setRemark("序号："+index+"，单号创建人");
			List<ProductDetailConsumption> productDetailConsumption = ProductSellDao.getProductDetailConsumption(productDetailCon);
			if(productDetailConsumption.size() > 0){
				ProductDetailConsumption detail = productDetailConsumption.get(0);
				if(detail.getLeftNumber() != leftNumber.intValue()){
					throw new RuntimeException(index + "：productproductproductproductproductproductproduct："+memberCard.getId());
				}
				
				if(!detail.getBankCardPay().equals(realPayment)){
					throw new RuntimeException(index + "：projectprojectprojectprojectprojectprojectproject："+memberCard.getId());
				}
				
				ProductDetailConsumption changeDetail = new ProductDetailConsumption();
				changeDetail.setId(detail.getId());
				changeDetail.setIsFail(1);
				changeDetail.setRemark(detail.getRemark() + "；找美网提供的数据包含了作废的单却没给个标识，所以系统需要再次修复数据");
				ProductSellDao.updateProductDetailConsumption(changeDetail);
				
				Consumption changeTop = new Consumption();
				changeTop.setId(detail.getConsumptionId());
				changeTop.setStatus(2);
				updateConsumption(changeTop);
				
				MemberCard change = new MemberCard();
				change.setId(memberCard.getId());
				change.setStockBalance(memberCard.getStockBalance() - detail.getBankCardPay());
				change.setOweBalance(memberCard.getOweBalance() - detail.getOwe());
				updateMemberCard(change);
				
				continue;
			}
			
			
			
			//合作
			ProjectCooperationConsumption cooDetailCon = new ProjectCooperationConsumption();
			cooDetailCon.setMemberCardId(memberCard.getId());
			cooDetailCon.setRemark("序号："+index+"，单号创建人");
			List<ProjectCooperationConsumption> cooConsumption = ProjectCooperationSellDao.getProjectCooperationConsumption(cooDetailCon);
			if(cooConsumption.size() > 0){
				ProjectCooperationConsumption detailTop = cooConsumption.get(0);
				ProjectCooperationDetailConsumption detailTopCon = new ProjectCooperationDetailConsumption();
				detailTopCon.setConsumptionId(detailTop.getId());
				List<ProjectCooperationDetailConsumption> detailList = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(detailTopCon);
				ProjectCooperationDetailConsumption detail = detailList.get(0);
				if(detail.getLeftNumber() != leftNumber.intValue()){
					throw new RuntimeException(index + "：setsetsetsetsetsetsetsetsetsetsetsetsetsetset："+memberCard.getId());
				}
				if(!detailTop.getBankCardPay().equals(realPayment)){
					throw new RuntimeException(index + "：projectprojectprojectprojectprojectprojectproject："+memberCard.getId());
				}
				
				ProjectCooperationConsumption changeDetail = new ProjectCooperationConsumption();
				changeDetail.setId(detail.getId());
				changeDetail.setIsFail(1);
				changeDetail.setRemark(detailTop.getRemark() + "；找美网提供的数据包含了作废的单却没给个标识，所以系统需要再次修复数据");
				ProjectCooperationSellDao.updateProjectCooperationConsumption(changeDetail);
				
				ProjectCooperationDetailConsumption changeDetailChile = new ProjectCooperationDetailConsumption();
				changeDetailChile.setId(detail.getId());
				changeDetailChile.setIsFail(1);
				ProjectCooperationSellDao.updateProjectCooperationDetailConsumption(changeDetailChile);
				
				Consumption changeTop = new Consumption();
				changeTop.setId(detail.getConsumptionId());
				changeTop.setStatus(2);
				updateConsumption(changeTop);
				
				continue;
			}
			
			
			
//			if(projectDetailConsumption.size() > 0 || productDetailConsumption.size() > 0 || cooConsumption.size() > 0){
//				System.out.println(1);
//			}else{
//				System.out.println("----------");
//			}
			
		}
		
		getSession().commit();
		getSession().close();
	}


}
