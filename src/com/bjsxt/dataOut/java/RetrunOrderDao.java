package com.bjsxt.dataOut.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bjsxt.dataOut.entity.MyReturn;
import com.bjsxt.dataOut.entity.ReturnDetail;

public class RetrunOrderDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		List<MyReturn> MyReturnList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		
		ReturnDetail detail = new ReturnDetail();
		detail.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
		List<ReturnDetail> returnList = getRetuanDetail(detail);
		
		for (ReturnDetail returnDetail : returnList) {
			MyReturn my = new MyReturn();
			my.fromSuper(returnDetail);
			
			jsonArray.add(JSON.parseObject(JSON.toJSONString(my)));
		}
		
		
		ExcelUtilForDO.toFile(jsonArray, MyReturn.class);
		closeSession();
	}
	
	
	static List<ReturnDetail> getRetuanDetail(ReturnDetail detail){
		List<ReturnDetail> list = getSession().selectList("com.bjsxt.dataOut.return.getReturnDetail", detail);
		return list;
	}
}