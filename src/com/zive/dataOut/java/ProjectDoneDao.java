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
import com.bjsxt.dataOut.entity.MyProjectDone;
import com.bjsxt.dataOut.entity.ProductDetailConsumption;
import com.bjsxt.dataOut.entity.ProjectDoneDetail;
import com.bjsxt.dataOut.entity.ProductInfo;
import com.bjsxt.dataOut.entity.ProjectDetailConsumption;
import com.bjsxt.dataOut.entity.ProjectDone;
import com.bjsxt.dataOut.entity.ProjectInfo;



public class ProjectDoneDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProjectDone get = new ProjectDone();
//		get.setId("G1909257520130066");
//		List<ProjectDone> getList = getProjectDone(get);
//		System.out.println(JSONArray.toJSONString(getList));
//		
//		ProjectDoneDetail getDetail = new ProjectDoneDetail();
//		getDetail.setOrderId("G1909257520130066");
//		List<ProjectDoneDetail> getDetailList = getProjectDoneDetail(getDetail);
//		System.out.println(JSONArray.toJSONString(getDetailList));
		
		List<MyProjectDone> myProjectDoneList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		
		ProjectDone get = new ProjectDone();
		get.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
		List<ProjectDone> getList = getProjectDone(get);
		
		for (ProjectDone projectDone : getList) {
			
			Consumption consumption = getConsumptionById(projectDone.getConsumptionId());
			
			ProjectDoneDetail getDetail = new ProjectDoneDetail();
//			getDetail.setOrderId(projectGet.getId());
			getDetail.setConsumptionId(projectDone.getConsumptionId());
			List<ProjectDoneDetail> getDetailList = getProjectDoneDetail(getDetail);
			if(getDetailList.size() == 0){
				System.out.println(projectDone.getId());
			}
			for (ProjectDoneDetail projectDoneDetail : getDetailList) {
				MyProjectDone myDone = new MyProjectDone();
				
				ProjectDetailConsumption projectDetailConsumption = ProjectSellDao.getProjectDetailConsumptionById(projectDoneDetail.getProjectDetailId());
				
				myDone.fromSuper(consumption,projectDetailConsumption,projectDone,projectDoneDetail);
				
				myProjectDoneList.add(myDone);
				
				jsonArray.add(JSON.parseObject(JSON.toJSONString(myDone)));
			}
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProjectDone.class);
		closeSession();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProjectDone getProjectDoneById(String id){
		ProjectDone ProjectDone = new ProjectDone();
		ProjectDone.setId(id);
		ProjectDone one = getSession().selectOne("com.bjsxt.dataOut.project.getProjectDone", ProjectDone);
		return one;
	}
	
	static public List<ProjectDone> getProjectDone(ProjectDone ProjectDone){
		List<ProjectDone> list = getSession().selectList("com.bjsxt.dataOut.project.getProjectDone", ProjectDone);
		return list;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public ProjectDoneDetail getProjectDoneDetailById(String id){
		ProjectDoneDetail ProjectDoneDetail = new ProjectDoneDetail();
		ProjectDoneDetail.setId(id);
		ProjectDoneDetail one = getSession().selectOne("com.bjsxt.dataOut.project.getProjectDoneDetail", ProjectDoneDetail);
		return one;
	}
	
	static public List<ProjectDoneDetail> getProjectDoneDetail(ProjectDoneDetail ProjectDoneDetail){
		List<ProjectDoneDetail> list = getSession().selectList("com.bjsxt.dataOut.project.getProjectDoneDetail", ProjectDoneDetail);
		return list;
	}
}