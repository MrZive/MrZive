package com.zive.xiaogan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.bi.entity.ItemInfo;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.Activity;
import com.zive.dataOut.entity.ActivityShop;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectInfoDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.pojo.CustomProductSales;
import com.zive.pojo.CustomProductSalesEmployeeEarn;
import com.zive.pojo.People;
import com.zive.pojo.SatisfactionRatio;
import com.zive.pojo.SatisfactionShopRatio;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class XiaoGanTest4 extends BaseDao{
	public static void main(String[] args) throws IOException, ParseException {
		List<Map<String,Object>> errorProject = getErrorProject();
		
		Map<String,Integer> data = new HashMap<>();
		data.put("8c71872c-2667-476e-8215-185ce2711b6c",60);
		data.put("66253745-63bd-44cb-84e5-5b217c5c0193",30);
		data.put("dfcea37e-2aa4-43a8-acd8-5a60e4147c64",60);
		
		
		for (Map<String, Object> map : errorProject) {
			String detailId = map.get("id").toString();
			
			Map<String, String> info = getActivityIdAndProjectId(detailId);
			String activityId = info.get("activity_id");
			String projectId = info.get("project_id");
			String shopId = info.get("shop_id");
			
			
			if(data.containsKey(projectId)){
				updateProjectDetailDone(detailId, data.get(projectId));
				updateErrorProject(detailId);
			}
		}
		getSession().commit();
		getSession().close();
	}
	
	
	public static List<Map<String,Object>> getErrorProject(){
		List<Map<String, Object>> memberProjectNumber = getSession().selectList("com.zive.xiaogan.getErrorProject");
		
		return memberProjectNumber;
	}
	
	public static List<Integer> getDoneTimeFromDetailId(String detailId){
		List<Integer> memberProjectNumber = getSession().selectList("com.zive.xiaogan.getDoneTimeFromDetailId", detailId);
		
		return memberProjectNumber;
	}
	
	public static int updateProjectDetailDone(String detailId, Integer serviceTime){
		Map<String,Object> map = new HashMap<>();
		map.put("detailId", detailId);
		map.put("serviceTime", serviceTime);
		int update = getSession().update("com.zive.xiaogan.updateProjectDetailDone", map);
		
		return update;
	}
	
	public static int updateErrorProject(String detailId){
		Map<String,Object> map = new HashMap<>();
		map.put("detailId", detailId);
		int update = getSession().update("com.zive.xiaogan.updateErrorProject", map);
		return update;
	}
	
	public static Map<String,String> getActivityIdAndProjectId(String detailId){
		Map<String,String> memberProjectNumber = getSession().selectOne("com.zive.xiaogan.getActivityIdAndProjectId", detailId);
		return memberProjectNumber;
	}
	
	public static List<Integer> getDoneTimeFromActivityInfo(String activityId,String projectId){
		Map<String,Object> map = new HashMap<>();
		map.put("activityId", activityId);
		map.put("projectId", projectId);
		List<Integer> memberProjectNumber = getSession().selectList("com.zive.xiaogan.getDoneTimeFromActivityInfo", map);
		return memberProjectNumber;
	}
	
}