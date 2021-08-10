package com.zive.updateMemberAssets;

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

import org.apache.commons.lang.StringUtils;

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

public class UpdateMemberAssetsAddProductTest extends BaseKangWangDao{
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	final static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
	
	
	static public boolean checkAndAddProductInfo(
			String shopId, String name, int buyNumber, Integer leftNumber, Date createDate,	String remark, double price, String memberCardId, String type){
		ProductInfo productInfo = new ProductInfo();
		productInfo.setName(name);
		List<ProductInfo> productList = getProductInfo(productInfo);
		String unit = null;
		
		if(productList.size() > 0){//项目处理逻辑
			ProductInfo info = productList.get(0);
			if(info.getBoxesUnit().equals(info.getBulkUnit())){
				unit = info.getBoxesUnit();
			}else{
				if(StringUtils.isBlank(unit)){
					throw new RuntimeException(name+"：excel产品没有单位");
				}
				if(!info.getBoxesUnit().equals(unit) && !info.getBulkUnit().equals(unit)){
					throw new RuntimeException(name+"：excel产品单位与系统的不匹配");
				}
			}
			
			addZhaoMeiProductDetail(memberCardId, shopId, info.getId(), price, buyNumber, leftNumber,unit, remark,createDate,type);
		
//			MemberCard change = new MemberCard();
//			change.setId(memberCard.getId());
//			change.setStockBalance(memberCard.getStockBalance()+realPayment);
//			change.setOweBalance(memberCard.getOweBalance()+owe);
//			updateMemberCard(change);
			return true;
		}else{
//			throw new RuntimeException("找不到对应的信息:" + name);
			if(type.equals("product")){
				System.out.println("找不到对应的信息:" + name);
			}
			return false;
		}
	}

	
	static public int addZhaoMeiProductDetail(String memberCardId,String shopId,String productId,Double price,Integer buyNumber,Integer leftNumber,String unit,String remark,Date createDate,String type){
		Date date = new Date();
		String consumptionId = "Assets"+date.getTime();
		ProductDetailConsumption detail = new ProductDetailConsumption();
		detail.setActivityId("");
		detail.setActualNumber(leftNumber);
		detail.setActualPrice(price);
		detail.setActualUnit(unit);
		detail.setBankCardPay(0D);
		detail.setBuyNumber(buyNumber);
		detail.setBuyOwe(0D);
		detail.setBuyType("Assets");
		detail.setBuyUnit(unit);
		detail.setCashPay(0D);
		detail.setChannelId(0);
		detail.setConsumptionId(consumptionId);
		detail.setConsumptionProductId(consumptionId);
		detail.setConsumptionSetId(null);
		detail.setCoupon("");
		detail.setCreateDate(createDate);
		double effectiveEarn = setDoubleScale(leftNumber * price);
		detail.setEffectiveEarn(effectiveEarn);
		detail.setExperiencePrice(0D);
		detail.setExpressType(null);
		detail.setId(UUID.randomUUID().toString());
		detail.setIntroducer(null);
		detail.setIsBook(0);
		detail.setIsFail(0);
		detail.setIsIntroduce(0);
		detail.setRemark("资产盘点，数量：0==》"+buyNumber+"，修改时间："+sdf.format(date)+"，备注："+remark);
		detail.setIsPay(1);
		detail.setIsSend(0);
		detail.setLeftNumber(leftNumber);
		detail.setLeftUnit(unit);
		detail.setMarketPrice(0D);
		detail.setMemberCardId(memberCardId);
		detail.setOwe(0D);
		
		double payment = setDoubleScale(price * buyNumber);
		detail.setPayment(payment);
		
		detail.setPrice(price);
		detail.setProductId(productId);
		detail.setPromotionPrice(0D);
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setShopId(shopId);
		detail.setStorePay(0D);
		int addProductDetailConsumption = ProductSellDao.addProductDetailConsumption(detail);
		if(addProductDetailConsumption==0){
			throw new RuntimeException("新增产品错误");
		}
		
		addConsumption(shopId, null, memberCardId, consumptionId, date, shopId, 0);
		
		return addProductDetailConsumption;
	}
	
}
