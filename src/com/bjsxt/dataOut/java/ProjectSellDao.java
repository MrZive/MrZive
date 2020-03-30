package com.bjsxt.dataOut.java;

import java.io.IOException;
import java.util.List;

import com.bjsxt.dataOut.entity.ProjectConsumption;
import com.bjsxt.dataOut.entity.ProjectDetailConsumption;

public class ProjectSellDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProjectConsumption con = new ProjectConsumption();
//		con.setConsumptionId("A1281584877988596");
//		List<ProjectConsumption> projectConsumption = getProjectConsumption(con);
//		System.out.println(JSONArray.toJSONString(projectConsumption));
//		
//		ProjectDetailConsumption detail = new ProjectDetailConsumption();
//		detail.setConsumptionProjectId(projectConsumption.get(0).getId());
//		List<ProjectDetailConsumption> projectDetailConsumption = getProjectDetailConsumption(detail);
//		System.out.println(JSONArray.toJSONString(projectDetailConsumption));
		
		
//		List<MyProjectConsumption> MyProjectConsumptionList = new ArrayList<>();
//		JSONArray jsonArray = new JSONArray();
//		
//		ProjectConsumption get = new ProjectConsumption();
//		get.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
//		List<ProjectConsumption> getList = getProjectConsumption(get);
//		
//		for (ProjectConsumption projectConsumption : getList) {
//			
//			Consumption consumption = getConsumptionById(projectConsumption.getConsumptionId());
//			
//			ProjectDetailConsumption getDetail = new ProjectDetailConsumption();
////			getDetail.setOrderId(ProjectConsumption.getId());
//			getDetail.setConsumptionId(projectConsumption.getConsumptionId());
//			List<ProjectDetailConsumption> getDetailList = getProjectDetailConsumption(getDetail);
//			if(getDetailList.size() == 0){
//				System.out.println(projectConsumption.getId());
//			}
//			for (ProjectDetailConsumption projectDetailConsumption : getDetailList) {
//				MyProjectConsumption myConsumption = new MyProjectConsumption();
//				
//				myConsumption.fromSuper(consumption,projectConsumption,projectDetailConsumption);
//				
//				MyProjectConsumptionList.add(myConsumption);
//				
//				System.out.println(myConsumption.getBuyPrice());
//				jsonArray.add(JSON.parseObject(JSON.toJSONString(myConsumption)));
//			}
//		}
//		
//		ExcelUtilForDO.toFile(jsonArray, MyProjectConsumption.class);
//		closeSession();
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