package com.zive.dataOut.java;

import java.io.IOException;
import java.util.List;

import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;

public class ProjectCooperationSellDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProjectCooperationConsumption con = new ProjectCooperationConsumption();
//		con.setConsumptionId("A1281584877988596");
//		List<ProjectCooperationConsumption> projectConsumption = getProjectCooperationConsumption(con);
//		System.out.println(JSONArray.toJSONString(projectConsumption));
//		
//		ProjectCooperationDetailConsumption detail = new ProjectCooperationDetailConsumption();
//		detail.setConsumptionProjectId(projectConsumption.get(0).getId());
//		List<ProjectCooperationDetailConsumption> projectDetailConsumption = getProjectCooperationDetailConsumption(detail);
//		System.out.println(JSONArray.toJSONString(projectDetailConsumption));
		
		
//		List<MyProjectCooperationConsumption> MyProjectCooperationConsumptionList = new ArrayList<>();
//		JSONArray jsonArray = new JSONArray();
//		
//		ProjectCooperationConsumption get = new ProjectCooperationConsumption();
//		get.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
//		List<ProjectCooperationConsumption> getList = getProjectCooperationConsumption(get);
//		
//		for (ProjectCooperationConsumption projectConsumption : getList) {
//			
//			Consumption consumption = getConsumptionById(projectConsumption.getConsumptionId());
//			
//			ProjectCooperationDetailConsumption getDetail = new ProjectCooperationDetailConsumption();
////			getDetail.setOrderId(ProjectCooperationConsumption.getId());
//			getDetail.setConsumptionId(projectConsumption.getConsumptionId());
//			List<ProjectCooperationDetailConsumption> getDetailList = getProjectCooperationDetailConsumption(getDetail);
//			if(getDetailList.size() == 0){
//				System.out.println(projectConsumption.getId());
//			}
//			for (ProjectCooperationDetailConsumption projectDetailConsumption : getDetailList) {
//				MyProjectCooperationConsumption myConsumption = new MyProjectCooperationConsumption();
//				
//				myConsumption.fromSuper(consumption,projectConsumption,projectDetailConsumption);
//				
//				MyProjectCooperationConsumptionList.add(myConsumption);
//				
//				System.out.println(myConsumption.getBuyPrice());
//				jsonArray.add(JSON.parseObject(JSON.toJSONString(myConsumption)));
//			}
//		}
//		
//		ExcelUtilForDO.toFile(jsonArray, MyProjectCooperationConsumption.class);
//		closeSession();
	}
	
	static public List<ProjectCooperationConsumption> getProjectCooperationConsumption(ProjectCooperationConsumption detail){
		List<ProjectCooperationConsumption> list = getSession().selectList("com.zive.dataOut.project.getProjectCooperationConsumption", detail);
		return list;
	}
	
	static public List<ProjectCooperationDetailConsumption> getProjectCooperationDetailConsumption(ProjectCooperationDetailConsumption detail){
		List<ProjectCooperationDetailConsumption> list = getSession().selectList("com.zive.dataOut.project.getProjectCooperationDetailConsumption", detail);
		return list;
	}
	
	static public ProjectCooperationDetailConsumption getProjectCooperationDetailConsumptionById(String id){
		ProjectCooperationDetailConsumption detail = new ProjectCooperationDetailConsumption();
		detail.setId(id);
		ProjectCooperationDetailConsumption one = getSession().selectOne("com.zive.dataOut.project.getProjectCooperationDetailConsumption", detail);
		return one;
	}
	
	static public int addProjectCooperationConsumption(ProjectCooperationConsumption info){
		int add = getSession().insert("com.zive.dataOut.project.addProjectCooperationConsumption", info);
		return add;
	}
	
	static public int addProjectCooperationDetailConsumption(ProjectCooperationDetailConsumption detail){
		int add = getSession().insert("com.zive.dataOut.project.addProjectCooperationDetailConsumption", detail);
		return add;
	}
	
	static public int updateProjectCooperationConsumption(ProjectCooperationConsumption info){
		int add = getSession().update("com.zive.dataOut.project.updateProjectCooperationConsumption", info);
		return add;
	}
	
	static public int updateProjectCooperationDetailConsumption(ProjectCooperationDetailConsumption detail){
		int add = getSession().update("com.zive.dataOut.project.updateProjectCooperationDetailConsumption", detail);
		return add;
	}
}