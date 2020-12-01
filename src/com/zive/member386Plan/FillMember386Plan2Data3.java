package com.zive.member386Plan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductGet;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.member386Plan.entity.MemberCard386Plan;
import com.zive.member386Plan.entity.MemberPassStatus;
import com.zive.member386Plan.entity.MemberPassStatus.EnumType;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 修改项目手工工时
 * @author Administrator
 *
 */
public class FillMember386Plan2Data3 extends BaseDao{

	public static void main(String[] args) throws IOException, ParseException {
		MemberCard386Plan planCon = new MemberCard386Plan();
		planCon.setIs386Pass(1);
		List<MemberCard386Plan> list = MemberCard386PlanDao.getList(planCon);
		
		for(MemberCard386Plan plan : list){
			MemberPassStatus statusCon = new MemberPassStatus();
			statusCon.setType(EnumType.is386Pass);
			statusCon.setMemberCardId(plan.getMemberCardId());
			List<MemberPassStatus> list2 = MemberStatusDao.getList(statusCon);
			
			
			MemberCard memberCard = BaseDao.getMemberCardById(plan.getMemberCardId());
			memberCard.setMemberCard386Plan(plan);
			if(list2.size()==0){
				MemberStatusDao.add(memberCard, EnumType.is386Pass);
			}
		}
		getSession().commit();
	}
	
}