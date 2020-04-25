package com.bjsxt.dataOut.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bjsxt.dataOut.entity.Consumption;
import com.bjsxt.dataOut.entity.MyProjectConsumption;
import com.bjsxt.dataOut.entity.ProjectConsumption;
import com.bjsxt.dataOut.entity.ProjectDetailConsumption;

public class ProjectSellDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		List<MyProjectConsumption> MyProjectConsumptionList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		
		ProjectConsumption get = new ProjectConsumption();
		get.setMemberCardId("3e19c08b-4586-426f-b192-0bae47f93bdf");
		List<ProjectConsumption> getList = getProjectConsumption(get);
		
		for (ProjectConsumption projectConsumption : getList) {
			
			Consumption consumption = getConsumptionById(projectConsumption.getConsumptionId());
			if(consumption == null){
				System.out.println("consumption is null:"+projectConsumption.getId());
				continue;
			}
			
			if(consumption.getIsCooperation()!=null && consumption.getIsCooperation()!=0){
				continue;
			}
			
			ProjectDetailConsumption getDetail = new ProjectDetailConsumption();
			getDetail.setConsumptionId(projectConsumption.getConsumptionId());
			List<ProjectDetailConsumption> getDetailList = getProjectDetailConsumption(getDetail);
			if(getDetailList.size() == 0){
				System.out.println("getDetail is null:"+projectConsumption.getId());
				continue;
			}
			for (ProjectDetailConsumption projectDetailConsumption : getDetailList) {
				if(projectDetailConsumption.getConsumptionSetId()==null){
					MyProjectConsumption myConsumption = new MyProjectConsumption();
					
					myConsumption.fromSuper(consumption,projectConsumption,projectDetailConsumption);
					
					MyProjectConsumptionList.add(myConsumption);
					
					jsonArray.add(JSON.parseObject(JSON.toJSONString(myConsumption)));
				}
			}
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProjectConsumption.class);
		closeSession();
	}
	
	static List<ProjectConsumption> getProjectConsumption(ProjectConsumption detail){
		List<ProjectConsumption> list = getSession().selectList("com.bjsxt.dataOut.project.getProjectConsumption", detail);
		return list;
	}
	
	static List<ProjectDetailConsumption> getProjectDetailConsumption(ProjectDetailConsumption detail){
		List<ProjectDetailConsumption> list = getSession().selectList("com.bjsxt.dataOut.project.getProjectDetailConsumption", detail);
		return list;
	}
	
	static ProjectDetailConsumption getProjectDetailConsumptionById(String id){
		ProjectDetailConsumption detail = new ProjectDetailConsumption();
		detail.setId(id);
		ProjectDetailConsumption one = getSession().selectOne("com.bjsxt.dataOut.project.getProjectDetailConsumption", detail);
		return one;
	}
}