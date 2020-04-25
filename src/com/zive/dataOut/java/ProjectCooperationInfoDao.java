package com.bjsxt.dataOut.java;

import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjsxt.dataOut.entity.CooperationProject;
import com.bjsxt.dataOut.entity.MyCooperationProject;

public class ProjectCooperationInfoDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		CooperationProject projectInfo = new CooperationProject();
		List<CooperationProject> projectInfoList = getCooperationProject(projectInfo);
		JSONArray jsonArray = new JSONArray();
		for (CooperationProject info : projectInfoList) {
			MyCooperationProject my = new MyCooperationProject();
			my.fromSuper(info);
			jsonArray.add(JSONObject.parseObject(JSON.toJSONString(my)));
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyCooperationProject.class);
		closeSession();
	}
	
}