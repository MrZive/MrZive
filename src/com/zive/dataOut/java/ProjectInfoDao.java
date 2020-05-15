package com.zive.dataOut.java;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.entity.MyProjectInfo;
import com.zive.dataOut.entity.MyProjectInfoMeasure;
import com.zive.dataOut.entity.MyProjectInfoPrice;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.ProjectInfoMeasure;
import com.zive.dataOut.entity.ProjectInfoPrice;

public class ProjectInfoDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		ProjectInfo projectInfo = new ProjectInfo();
		List<ProjectInfo> projectInfoList = getProjectInfo(projectInfo);
		JSONArray jsonArray = new JSONArray();
		for (ProjectInfo info : projectInfoList) {
			MyProjectInfo my = new MyProjectInfo();
			my.fromSuper(info);
			jsonArray.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ProjectInfoMeasure projectInfoMeasure = new ProjectInfoMeasure();
		List<ProjectInfoMeasure> projectInfoMeasureList = getProjectInfoMeasure(null);
		JSONArray jsonArrayMeasure = new JSONArray();
		for (ProjectInfoMeasure info : projectInfoMeasureList) {
			MyProjectInfoMeasure my = new MyProjectInfoMeasure();
			my.fromSuper(info);
			jsonArrayMeasure.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ProjectInfoPrice projectInfoPrice = new ProjectInfoPrice();
		List<ProjectInfoPrice> projectInfoPriceList = getProjectInfoPrice(null);
		JSONArray jsonArrayPrice =  new JSONArray();
		for (ProjectInfoPrice info : projectInfoPriceList) {
			MyProjectInfoPrice my = new MyProjectInfoPrice();
			my.fromSuper(info);
			jsonArrayPrice.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProjectInfo.class);
		ExcelUtilForDO.toFile(jsonArrayMeasure, MyProjectInfoMeasure.class);
		ExcelUtilForDO.toFile(jsonArrayPrice, MyProjectInfoPrice.class);
		closeSession();
	}
	
	static public List<ProjectInfoMeasure> getProjectInfoMeasure(String projectId){
		List<ProjectInfoMeasure> list = getSession().selectList("com.zive.dataOut.project.getProjectInfoMeasure", projectId);
		return list;
	}
	
	static public List<ProjectInfoPrice> getProjectInfoPrice(String projectId){
		List<ProjectInfoPrice> list = getSession().selectList("com.zive.dataOut.project.getProjectInfoPrice", projectId);
		return list;
	}
}