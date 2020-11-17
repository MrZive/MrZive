package com.zive.member386Plan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MyProjectDone;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.member386Plan.entity.MemberCard386Plan;



public class MemberCard386PlanDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		MemberCard386Plan plan = new MemberCard386Plan();
		List<MemberCard386Plan> getList = getList(plan);
		
		for (MemberCard386Plan p : getList) {
			System.out.println(p.getMemberCardId());
		}
		
		closeSession();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public List<MemberCard386Plan> getList(MemberCard386Plan plan) {
		return getSession().selectList("com.zive.member386Plan.MemberCard386PlanDao.getList", plan);
	}
	static public MemberCard386Plan getByMemberCardId(String memberCardId) {
		return getSession().selectOne("com.zive.member386Plan.MemberCard386PlanDao.getByMemberCardId", memberCardId);
	}
	static public int add(MemberCard386Plan plan) {
		return getSession().insert("com.zive.member386Plan.MemberCard386PlanDao.add", plan);
	}
	static public int update(MemberCard386Plan plan) {
		return getSession().update("com.zive.member386Plan.MemberCard386PlanDao.update", plan);
	}
}