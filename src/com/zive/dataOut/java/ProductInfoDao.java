package com.zive.dataOut.java;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.entity.MyProductInfo;
import com.zive.dataOut.entity.MyProductInfoMeasure;
import com.zive.dataOut.entity.MyProductInfoPrice;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProductInfoMeasure;
import com.zive.dataOut.entity.ProductInfoPrice;

public class ProductInfoDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		ProductInfo productInfo = new ProductInfo();
		List<ProductInfo> productInfoList = getProductInfo(productInfo);
		JSONArray jsonArray = new JSONArray();
		for (ProductInfo info : productInfoList) {
			MyProductInfo my = new MyProductInfo();
			my.fromSuper(info);
			jsonArray.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ProductInfoMeasure productInfoMeasure = new ProductInfoMeasure();
		List<ProductInfoMeasure> productInfoMeasureList = getProductInfoMeasure(null);
		JSONArray jsonArrayMeasure = new JSONArray();
		for (ProductInfoMeasure info : productInfoMeasureList) {
			MyProductInfoMeasure my = new MyProductInfoMeasure();
			my.fromSuper(info);
			jsonArrayMeasure.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ProductInfoPrice productInfoPrice = new ProductInfoPrice();
		List<ProductInfoPrice> productInfoPriceList = getProductInfoPrice(null);
		JSONArray jsonArrayPrice =  new JSONArray();
		for (ProductInfoPrice info : productInfoPriceList) {
			MyProductInfoPrice my = new MyProductInfoPrice();
			my.fromSuper(info);
			jsonArrayPrice.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProductInfo.class);
		ExcelUtilForDO.toFile(jsonArrayMeasure, MyProductInfoMeasure.class);
		ExcelUtilForDO.toFile(jsonArrayPrice, MyProductInfoPrice.class);
		closeSession();
	}
	
	static public List<ProductInfoMeasure> getProductInfoMeasure(String productId){
		List<ProductInfoMeasure> list = getSession().selectList("com.zive.dataOut.product.getProductInfoMeasure", productId);
		return list;
	}
	
	static public List<ProductInfoPrice> getProductInfoPrice(String productId){
		List<ProductInfoPrice> list = getSession().selectList("com.zive.dataOut.product.getProductInfoPrice", productId);
		return list;
	}
}