package com.zive.dataOut.java;

import java.io.IOException;
import java.util.List;

import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationWxDetailConsumption;

public class ProjectCooperationWxSellDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProjectCooperationConsumption con = new ProjectCooperationConsumption();
//		con.setConsumptionId("A1281584877988596");
//		List<ProjectCooperationConsumption> projectConsumption = getProjectCooperationConsumption(con);
//		System.out.println(JSONArray.toJSONString(projectConsumption));
//		
//		ProjectCooperationWxDetailConsumption detail = new ProjectCooperationWxDetailConsumption();
//		detail.setConsumptionProjectId(projectConsumption.get(0).getId());
//		List<ProjectCooperationWxDetailConsumption> projectDetailConsumption = getProjectCooperationWxDetailConsumption(detail);
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
//			ProjectCooperationWxDetailConsumption getDetail = new ProjectCooperationWxDetailConsumption();
////			getDetail.setOrderId(ProjectCooperationConsumption.getId());
//			getDetail.setConsumptionId(projectConsumption.getConsumptionId());
//			List<ProjectCooperationWxDetailConsumption> getDetailList = getProjectCooperationWxDetailConsumption(getDetail);
//			if(getDetailList.size() == 0){
//				System.out.println(projectConsumption.getId());
//			}
//			for (ProjectCooperationWxDetailConsumption projectDetailConsumption : getDetailList) {
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
	
//	static List<ProjectCooperationConsumption> getProjectCooperationConsumption(ProjectCooperationConsumption detail){
//		List<ProjectCooperationConsumption> list = getSession().selectList("com.zive.dataOut.project.getProjectCooperationConsumption", detail);
//		return list;
//	}
	
	static List<ProjectCooperationWxDetailConsumption> getProjectCooperationWxDetailConsumption(ProjectCooperationWxDetailConsumption detail){
		List<ProjectCooperationWxDetailConsumption> list = getSession().selectList("com.zive.dataOut.project.getProjectCooperationWxDetailConsumption", detail);
		return list;
	}
	
	static ProjectCooperationWxDetailConsumption getProjectCooperationWxDetailConsumptionById(String id){
		ProjectCooperationWxDetailConsumption detail = new ProjectCooperationWxDetailConsumption();
		detail.setId(id);
		ProjectCooperationWxDetailConsumption one = getSession().selectOne("com.zive.dataOut.project.getProjectCooperationWxDetailConsumption", detail);
		return one;
	}
}