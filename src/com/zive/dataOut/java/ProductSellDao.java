package com.zive.dataOut.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MyProductConsumption;
import com.zive.dataOut.entity.ProductConsumption;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProductConsumption;
import com.zive.dataOut.entity.ProductDetailConsumption;

public class ProductSellDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProductConsumption con = new ProductConsumption();
//		con.setConsumptionId("A1281584877988596");
//		List<ProductConsumption> productConsumption = getProductConsumption(con);
//		System.out.println(JSONArray.toJSONString(productConsumption));
//		
//		ProductDetailConsumption detail = new ProductDetailConsumption();
//		detail.setConsumptionProductId(productConsumption.get(0).getId());
//		List<ProductDetailConsumption> productDetailConsumption = getProductDetailConsumption(detail);
//		System.out.println(JSONArray.toJSONString(productDetailConsumption));
		
		
		List<MyProductConsumption> MyProductConsumptionList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		
		ProductConsumption get = new ProductConsumption();
		get.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
		List<ProductConsumption> getList = getProductConsumption(get);
		
		for (ProductConsumption productConsumption : getList) {
			
			Consumption consumption = getConsumptionById(productConsumption.getConsumptionId());
			
			ProductDetailConsumption getDetail = new ProductDetailConsumption();
//			getDetail.setOrderId(ProductConsumption.getId());
			getDetail.setConsumptionId(productConsumption.getConsumptionId());
			List<ProductDetailConsumption> getDetailList = getProductDetailConsumption(getDetail);
			if(getDetailList.size() == 0){
				System.out.println(productConsumption.getId());
			}
			for (ProductDetailConsumption productDetailConsumption : getDetailList) {
				MyProductConsumption myConsumption = new MyProductConsumption();
				
				myConsumption.fromSuper(consumption,productConsumption,productDetailConsumption);
				
				MyProductConsumptionList.add(myConsumption);
				
				jsonArray.add(JSON.parseObject(JSON.toJSONString(myConsumption)));
			}
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProductConsumption.class);
		closeSession();
	}
	
	static public List<ProductConsumption> getProductConsumption(ProductConsumption detail){
		List<ProductConsumption> list = getSession().selectList("com.zive.dataOut.product.getProductConsumption", detail);
		return list;
	}
	
	static public List<ProductDetailConsumption> getProductDetailConsumption(ProductDetailConsumption detail){
		List<ProductDetailConsumption> list = getSession().selectList("com.zive.dataOut.product.getProductDetailConsumption", detail);
		return list;
	}
	
	static public ProductDetailConsumption getProductDetailConsumptionById(String id){
		ProductDetailConsumption detail = new ProductDetailConsumption();
		detail.setId(id);
		ProductDetailConsumption one = getSession().selectOne("com.zive.dataOut.product.getProductDetailConsumption", detail);
		return one;
	}
	
	static public int addProductDetailConsumption(ProductDetailConsumption detail){
		int add = getSession().update("com.zive.dataOut.product.addProductDetailConsumption", detail);
		return add;
	}
	
	static public int updateProductDetailConsumption(ProductDetailConsumption detail){
		int update = getSession().update("com.zive.dataOut.product.updateProductDetailConsumption", detail);
		return update;
	}
}