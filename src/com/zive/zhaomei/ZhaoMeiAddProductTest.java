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
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiAddProductTest extends BaseKangWangDao{

public static void main(String[] args) throws IOException, ParseException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\产品资产.xls");
		
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
		
		Map<String, NameToSystemName> zhaoMeiName = getZhaoMeiName();
		
		for(int i = 2; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			
			String index = excelCells.get(1).getValue().toString();
			String shopName = excelCells.get(1).getValue().toString();
			String phone = excelCells.get(5).getValue().toString();
			String name = excelCells.get(6).getValue().toString();
			String secondName = "";
			
			String isSendStr = excelCells.get(7).getValue().toString();
			Integer isSend = isSendStr.equals("赠送")?1:0;
			
			int buyNumber = Double.valueOf(excelCells.get(8).getValue().toString()).intValue();
			Double payment = Double.valueOf(excelCells.get(9).getValue().toString());
			Double realPayment = Double.valueOf(excelCells.get(10).getValue().toString());
			Double refundPayment = Double.valueOf(excelCells.get(11).getValue().toString());
			realPayment = realPayment - refundPayment;
			
			Double owe = Double.valueOf(excelCells.get(12).getValue().toString());
			int refundNumber = Double.valueOf(excelCells.get(13).getValue().toString()).intValue();
			
			Integer leftNumber = buyNumber - refundNumber;
			Date createDate = sdf.parse(excelCells.get(14).getValue().toString());
			Date buyDate = sdf.parse(excelCells.get(15).getValue().toString());
			
			String createUser = excelCells.get(16).getValue().toString();
			
			String remark = excelCells.get(17).getValue().toString();
			
			
			double price = payment / buyNumber;
			price = new BigDecimal(price).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				continue;
//				throw new RuntimeException("手机号不存在"+phone);
			}
			
			if(true){
				ProductInfo productInfo = new ProductInfo();
				productInfo.setName(name);
				List<ProductInfo> productList = getProductInfo(productInfo);
				if(productList.size() == 0){
					//查询对应的名称
					if(zhaoMeiName.containsKey(name) && zhaoMeiName.get(name).getType().equals("product")){
						productInfo.setName(zhaoMeiName.get(name).getNewName());
						productList = getProductInfo(productInfo);
						if(productList.size() == 0){
							continue;
						}
					}
				}
				
				if(productList.size() > 0){//项目处理逻辑
					ProductInfo info = productList.get(0);
					Shop shop = getShop(new Shop(){{setName(shopName);}}).get(0);
					
					
					addZhaoMeiProductDetail(index, memberCard.getId(), shop.getId(), info.getId(), secondName, price, buyNumber, leftNumber, unit,0D,realPayment,0D,owe,"", createUser,buyDate,createDate);
					
				
					MemberCard change = new MemberCard();
					change.setId(memberCard.getId());
					change.setStoreBalance(memberCard.getStoreBalance()+realPayment);
					change.setOweBalance(memberCard.getOweBalance()+owe);
					updateMemberCard(change);
				}else{
//					throw new RuntimeException("找不到对应的信息:" + name);
					System.out.println("找不到对应的信息:" + name);
				}
			}
			
			
			
//			if(type.equals("产品")){
//				ProductInfo productInfo = new ProductInfo();
//				productInfo.setName(name);
//				List<ProductInfo> productList = getProductInfo(productInfo);
//				if(productList.size() == 0){
//					//查询对应的名称
//					if(kangWangName.containsKey(name) && kangWangName.get(name).getType().equals("product")){
//						productInfo.setName(kangWangName.get(name).getNewName());
//						productList = getProductInfo(productInfo);
//						if(productList.size() == 0){
//							throw new RuntimeException("找不到产品名称信息");
//						}
//					}
//				}
//				
//				String secondName = "";
//				if(productList.size() > 0){//项目处理逻辑
//					ProductInfo info = productList.get(0);
//					Integer number = Integer.valueOf(numberStr.substring(0, numberStr.length()-1));
//					String unit = numberStr.substring(numberStr.length()-1);
//					if(StringUtils.isBlank(unit)){
//						throw new RuntimeException("excel产品没有单位");
//					}
//					if(!info.getBoxesUnit().equals(unit) && !info.getBulkUnit().equals(unit)){
//						throw new RuntimeException("excel产品单位与系统的不匹配");
//					}
//					
//					if(operation.equals("新增")){
//						addProductDetail(memberCard.getId(), info.getId(), number, number, price, unit, remark);
//					}else if(operation.equals("作废")){
//						failProductDetail(detailId);
//					}
//					
//				}else{
//					throw new RuntimeException("找不到对应的信息:" + name);
//				}
//			}
		}
		getSession().commit();
		getSession().close();
	}

	
	static public int addZhaoMeiProductDetail(String index,String memberCardId,String shopId,String productId,String secondName,Double price,Integer buyNumber,Integer leftNumber,String unit,Double storePay,Double bankcardPay,Double cashPay,Double owe,Integer isSend,String remark,String createUser,Date buyTime,Date createDate){
		Date date = new Date();
		String consumptionId = "ZhaoMei"+date.getTime();
		ProductDetailConsumption detail = new ProductDetailConsumption();
		detail.setActivityId("");
		detail.setActualNumber(leftNumber);
		detail.setActualPrice(price);
		detail.setActualUnit(unit);
		detail.setBankCardPay(bankcardPay);
		detail.setBuyNumber(buyNumber);
		detail.setBuyOwe(owe);
		detail.setBuyType("ZhaoMei");
		detail.setBuyUnit(unit);
		detail.setCashPay(cashPay);
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
		detail.setIsBook(owe>0?1:0);
		detail.setIsFail(0);
		detail.setIsIntroduce(0);
		detail.setRemark("找美网数据录入系统，序号："+index+"单号创建人："+createUser+"，购买时间："+buyTime);
		detail.setIsPay(1);
		detail.setIsSend(isSend);
		detail.setLeftNumber(leftNumber);
		detail.setLeftUnit(unit);
		detail.setMarketPrice(0D);
		detail.setMemberCardId(memberCardId);
		detail.setOwe(owe);
		detail.setPayment(price * buyNumber);
		detail.setPrice(price);
		detail.setProductId(productId);
		detail.setPromotionPrice(0D);
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setShopId(shopId);
		detail.setStorePay(storePay);
		int addProductDetailConsumption = ProductSellDao.addProductDetailConsumption(detail);
		if(addProductDetailConsumption==0){
			throw new RuntimeException("新增产品错误");
		}
		return addProductDetailConsumption;
	}
	
}
