package com.bjsxt.dataOut.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjsxt.dataOut.entity.Consumption;
import com.bjsxt.dataOut.entity.CooperationProject;
import com.bjsxt.dataOut.entity.MemberCard;
import com.bjsxt.dataOut.entity.MyProductGet;
import com.bjsxt.dataOut.entity.ProductDetailConsumption;
import com.bjsxt.dataOut.entity.ProductGet;
import com.bjsxt.dataOut.entity.ProductGetDetail;
import com.bjsxt.dataOut.entity.ProductInfo;
import com.bjsxt.dataOut.entity.ProjectInfo;



public class ProductGetDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProductGet get = new ProductGet();
//		get.setId("G1909257520130066");
//		List<ProductGet> getList = getProductGet(get);
//		System.out.println(JSONArray.toJSONString(getList));
//		
//		ProductGetDetail getDetail = new ProductGetDetail();
//		getDetail.setOrderId("G1909257520130066");
//		List<ProductGetDetail> getDetailList = getProductGetDetail(getDetail);
//		System.out.println(JSONArray.toJSONString(getDetailList));
		
		List<MyProductGet> myProductGetList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		
		ProductGet get = new ProductGet();
		get.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
		List<ProductGet> getList = getProductGet(get);
		
		for (ProductGet productGet : getList) {
			
			Consumption consumption = getConsumptionById(productGet.getConsumptionId());
			
			ProductGetDetail getDetail = new ProductGetDetail();
//			getDetail.setOrderId(productGet.getId());
			getDetail.setConsumptionId(productGet.getConsumptionId());
			List<ProductGetDetail> getDetailList = getProductGetDetail(getDetail);
			if(getDetailList.size() == 0){
				System.out.println(productGet.getId());
			}
			for (ProductGetDetail productGetDetail : getDetailList) {
				MyProductGet myGet = new MyProductGet();
				
				ProductDetailConsumption productDetailConsumption = ProductSellDao.getProductDetailConsumptionById(productGetDetail.getProductDetailId());
				
				myGet.fromSuper(consumption,productDetailConsumption,productGet,productGetDetail);
				
				myProductGetList.add(myGet);
				
				jsonArray.add(JSON.parseObject(JSON.toJSONString(myGet)));
			}
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProductGet.class);
		closeSession();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProductGet getProductGetById(String id){
		ProductGet ProductGet = new ProductGet();
		ProductGet.setId(id);
		ProductGet one = getSession().selectOne("com.bjsxt.dataOut.product.getProductGet", ProductGet);
		return one;
	}
	
	static public List<ProductGet> getProductGet(ProductGet ProductGet){
		List<ProductGet> list = getSession().selectList("com.bjsxt.dataOut.product.getProductGet", ProductGet);
		return list;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProductGetDetail getProductGetDetailById(String id){
		ProductGetDetail ProductGetDetail = new ProductGetDetail();
		ProductGetDetail.setId(id);
		ProductGetDetail one = getSession().selectOne("com.bjsxt.dataOut.product.getProductGetDetail", ProductGetDetail);
		return one;
	}
	
	static public List<ProductGetDetail> getProductGetDetail(ProductGetDetail ProductGetDetail){
		List<ProductGetDetail> list = getSession().selectList("com.bjsxt.dataOut.product.getProductGetDetail", ProductGetDetail);
		return list;
	}
}