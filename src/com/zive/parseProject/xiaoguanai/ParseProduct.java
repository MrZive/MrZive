package com.zive.parseProject.xiaoguanai;

import java.io.IOException;
import java.util.List;

import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProductSellDao;

public class ParseProduct extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		//小罐艾单粒
		ProductDetailConsumption detailCon = new ProductDetailConsumption();
		detailCon.setIsFail(0);
		detailCon.setProductId("71bcd5b6-3781-4cab-8a60-77d649b58827");
		List<ProductDetailConsumption> list = ProductSellDao.getProductDetailConsumption(detailCon);
		int small = 0;
		for (ProductDetailConsumption detail : list) {
			if(detail.getLeftNumber()!=null && detail.getLeftNumber() > 0){
				small++;
				String remark = detail.getRemark()==null?"":detail.getRemark();
				ProductDetailConsumption change = new ProductDetailConsumption();
				change.setId(detail.getId());
//				change.setProductId("c38329be-ee39-4b30-b35f-b7f6b034cc2d");//测试环境
				change.setProductId("87cf7cfa-b823-464d-b13c-f1d51b0b340c");//正式系统
				change.setRemark(remark+"--旧版单粒装转换新版小罐艾（单粒装）");
				ProductSellDao.updateProductDetailConsumption(change);
			}
		}
		System.out.println(small);
		
		
		//盒装
		detailCon.setProductId("1aecb78b-9a0a-49eb-8ade-b38a03d69488");
		list = ProductSellDao.getProductDetailConsumption(detailCon);
		int big = 0;
		for (ProductDetailConsumption detail : list) {
			if(detail.getLeftNumber()!=null && detail.getLeftNumber() > 0){
				big++;
				String remark = detail.getRemark()==null?"":detail.getRemark();
				Integer actualNumber = detail.getActualNumber();
				Double actualPrice = detail.getActualPrice();
				String actualUnit = detail.getActualUnit();
				Integer buyNumber = detail.getBuyNumber();
				String buyUnit = detail.getBuyUnit();
				Integer leftNumber = detail.getLeftNumber();
				String leftUnit = detail.getLeftUnit();
				Double price = detail.getPrice();
				
				actualNumber = actualNumber==null ? leftNumber : actualNumber;
				actualPrice = actualPrice==null ? price : actualPrice;

						
				ProductDetailConsumption change = new ProductDetailConsumption();
				change.setId(detail.getId());
//				change.setProductId("c38329be-ee39-4b30-b35f-b7f6b034cc2d");//测试环境
				change.setProductId("87cf7cfa-b823-464d-b13c-f1d51b0b340c");//正式系统
				change.setActualNumber(actualNumber * 24);
				double setDoubleScale = setDoubleScale(actualPrice / 24);
				change.setActualPrice(setDoubleScale);
				change.setActualUnit("个");
				change.setBuyNumber(buyNumber * 24);
				change.setBuyUnit("个");
				change.setLeftNumber(leftNumber * 24);
				change.setLeftUnit("个");
				double setDoubleScale2 = setDoubleScale(price / 24);
				change.setPrice(setDoubleScale2);
				change.setRemark(remark+detail.getRemark()+"--旧版盒装转换新版小罐艾（单粒装）");
				ProductSellDao.updateProductDetailConsumption(change);
			}
		}
		System.out.println(big);
		
		
		
		getSession().commit();
		getSession().close();
	}
}


