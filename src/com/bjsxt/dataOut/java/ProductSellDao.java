package com.bjsxt.dataOut.java;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.bjsxt.dataOut.entity.ProductConsumption;
import com.bjsxt.dataOut.entity.ProductDetailConsumption;

public class ProductSellDao extends BaseDao{

	public static void main(String[] args) {
		ProductConsumption con = new ProductConsumption();
		con.setConsumptionId("A1281584877988596");
		List<ProductConsumption> productConsumption = getProductConsumption(con);
		System.out.println(JSONArray.toJSONString(productConsumption));
		
		
		ProductDetailConsumption detail = new ProductDetailConsumption();
		detail.setConsumptionProductId(productConsumption.get(0).getId());
		List<ProductDetailConsumption> productDetailConsumption = getProductDetailConsumption(detail);
		System.out.println(JSONArray.toJSONString(productDetailConsumption));
	}
	
	static List<ProductConsumption> getProductConsumption(ProductConsumption detail){
		List<ProductConsumption> list = getSession().selectList("com.bjsxt.dataOut.product.getProductConsumption", detail);
		return list;
	}
	
	static List<ProductDetailConsumption> getProductDetailConsumption(ProductDetailConsumption detail){
		List<ProductDetailConsumption> list = getSession().selectList("com.bjsxt.dataOut.product.getProductDetailConsumption", detail);
		return list;
	}
	
	static ProductDetailConsumption getProductDetailConsumptionById(String id){
		ProductDetailConsumption detail = new ProductDetailConsumption();
		detail.setId(id);
		ProductDetailConsumption one = getSession().selectOne("com.bjsxt.dataOut.product.getProductDetailConsumption", detail);
		return one;
	}
}