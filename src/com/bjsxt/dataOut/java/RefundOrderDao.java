package com.bjsxt.dataOut.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjsxt.dataOut.entity.MyRefund;
import com.bjsxt.dataOut.entity.MyRefundDetailOfCooperation;
import com.bjsxt.dataOut.entity.MyRefundDetailOfCooperationWx;
import com.bjsxt.dataOut.entity.MyRefundDetailOfProduct;
import com.bjsxt.dataOut.entity.MyRefundDetailOfProject;
import com.bjsxt.dataOut.entity.MyReturn;
import com.bjsxt.dataOut.entity.ProductDetailConsumption;
import com.bjsxt.dataOut.entity.ProjectCooperationDetailConsumption;
import com.bjsxt.dataOut.entity.ProjectCooperationWxDetailConsumption;
import com.bjsxt.dataOut.entity.ProjectDetailConsumption;
import com.bjsxt.dataOut.entity.Refund;
import com.bjsxt.dataOut.entity.RefundDetail;

public class RefundOrderDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		List<MyReturn> MyReturnList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrayOfProduct = new JSONArray();
		JSONArray jsonArrayOfProject = new JSONArray();
		JSONArray jsonArrayOfCoo = new JSONArray();
		JSONArray jsonArrayOfCooWx = new JSONArray();
		
		Refund refundCon = new Refund();
//		refundCon.setType(2);
//		refundCon.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
		refundCon.setShopId("110101");
		
		List<Refund> refundList = getRefund(refundCon);
		
		for (Refund refund : refundList) {
			
			MyRefund myRefund = new MyRefund();
			myRefund.fromSuper(refund);
			jsonArray.add(JSONObject.parseObject(JSON.toJSONString(myRefund)));
			
			RefundDetail detail = new RefundDetail();
//			detail.setConsumptionId(consumptionId);
//			detail.setDetailId(detailId);
			detail.setReturnTransferId(refund.getId());
			
			List<RefundDetail> refundDetailList = getRefundDetail(detail);
			
			for (RefundDetail refundDetail : refundDetailList) {
				
				if(refundDetail.getType().equals(1)){//项目
					ProjectDetailConsumption projectDetailConsumptionById = ProjectSellDao.getProjectDetailConsumptionById(refundDetail.getDetailId());
					MyRefundDetailOfProject myProject = new MyRefundDetailOfProject();
					myProject.fromSuper(projectDetailConsumptionById,refundDetail);
					
					jsonArrayOfProject.add(JSONObject.parseObject(JSON.toJSONString(myProject)));
				}else if(refundDetail.getType().equals(2)){//2产品
					ProductDetailConsumption productDetailConsumptionById = ProductSellDao.getProductDetailConsumptionById(refundDetail.getDetailId());
					MyRefundDetailOfProduct myProduct = new MyRefundDetailOfProduct();
					myProduct.fromSuper(productDetailConsumptionById,refundDetail);
					
					jsonArrayOfProduct.add(JSONObject.parseObject(JSON.toJSONString(myProduct)));
				}else if(refundDetail.getType().equals(3)){//3合作项目
					ProjectCooperationDetailConsumption projectCooperationDetailConsumptionById = ProjectCooperationSellDao.getProjectCooperationDetailConsumptionById(refundDetail.getDetailId());
					MyRefundDetailOfCooperation myCoo = new MyRefundDetailOfCooperation();
					myCoo.fromSuper(projectCooperationDetailConsumptionById, refundDetail);
					
					jsonArrayOfCoo.add(JSONObject.parseObject(JSON.toJSONString(myCoo)));
				}else if(refundDetail.getType().equals(4)){//纹绣退款单
					ProjectCooperationWxDetailConsumption projectCooperationWxDetailConsumptionById = ProjectCooperationWxSellDao.getProjectCooperationWxDetailConsumptionById(refundDetail.getDetailId());
					MyRefundDetailOfCooperationWx myCooWx = new MyRefundDetailOfCooperationWx();
					myCooWx.fromSuper(projectCooperationWxDetailConsumptionById, refundDetail);
					
					jsonArrayOfCooWx.add(JSONObject.parseObject(JSON.toJSONString(myCooWx)));
				}else{
					System.out.println(refundDetail.getReturnTransferId());
					throw new RuntimeException();
				}
			}
			
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyRefund.class);
		ExcelUtilForDO.toFile(jsonArrayOfProduct, MyRefundDetailOfProduct.class);
		ExcelUtilForDO.toFile(jsonArrayOfProject, MyRefundDetailOfProject.class);
		ExcelUtilForDO.toFile(jsonArrayOfCoo, MyRefundDetailOfCooperation.class);
		ExcelUtilForDO.toFile(jsonArrayOfCooWx, MyRefundDetailOfCooperationWx.class);
		closeSession();
	}
	
	static List<Refund> getRefund(Refund refund){
		List<Refund> list = getSession().selectList("com.bjsxt.dataOut.refund.getRefund", refund);
		return list;
	}
	
	static List<RefundDetail> getRefundDetail(RefundDetail detail){ 
		List<RefundDetail> list = getSession().selectList("com.bjsxt.dataOut.refund.getRefundDetail", detail);
		return list;
	}
	
}