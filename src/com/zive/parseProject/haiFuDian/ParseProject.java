package com.zive.parseProject.haiFuDian;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectInfoDao;
import com.zive.parseProject.Parse;
import com.zive.shop.entity.ProjectConsumption;
import com.zive.shop.entity.ProjectDetailConsumption;

public class ParseProject extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		String memberIds = "15975630353-13622760900";
		
		String[] memberids = memberIds.split("-");
		
		
		for (String string : memberids) {
			MemberCard memberCard = getMemberCardByPhone(string);
			System.out.println(memberCard.getId());
			
			ProjectCooperationDetailConsumption cooDetail = new ProjectCooperationDetailConsumption();
			cooDetail.setActivityId("93195c18-59ae-409d-b908-34d7e0570c67");
			cooDetail.setCooperationId("7990c5d6-b731-4511-87f6-51f470933026");
			cooDetail.setMemberCardId(memberCard.getId());
			cooDetail.setIsFail(0);
			List<ProjectCooperationDetailConsumption> list = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(cooDetail);
			if(list.size() == 0){
				cooDetail.setActivityId("31cc4f46-7937-4cd5-add8-e549d665e5f4");
				cooDetail.setCooperationId("da1b3427-1043-4b94-bed5-abb1e170237a");
				list = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(cooDetail);
			}
			if(list.size() == 0){
				cooDetail.setActivityId(null);
				cooDetail.setCooperationId("7990c5d6-b731-4511-87f6-51f470933026");
				list = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(cooDetail);
			}
			if(list.size() == 0){
				cooDetail.setActivityId(null);
				cooDetail.setCooperationId("da1b3427-1043-4b94-bed5-abb1e170237a");
				list = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(cooDetail);
			}
			System.out.println(list.size());
			
			ProjectCooperationDetailConsumption detail = list.get(0);
			System.out.println(detail.getLeftNumber());
			if(detail.getLeftNumber() == 0){
				cooDetail.setActivityId(null);
				cooDetail.setCooperationId("7990c5d6-b731-4511-87f6-51f470933026");
				list = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(cooDetail);
			}
			if(detail.getLeftNumber() == 0){
				cooDetail.setActivityId(null);
				cooDetail.setCooperationId("da1b3427-1043-4b94-bed5-abb1e170237a");
				list = ProjectCooperationSellDao.getProjectCooperationDetailConsumption(cooDetail);
			}
			detail = list.get(0);
			
			System.out.println(detail.getLeftNumber());
			
			ProjectCooperationDetailConsumption change = new ProjectCooperationDetailConsumption();
			change.setId(detail.getId());
//			change.setCooperationId("56267694-3e45-4d90-ab89-dab6cd4544dc");//调Q
			change.setCooperationId("4e16b5fc-28e8-4be6-9e93-d333d66af5d8");//素颜光
			ProjectCooperationSellDao.updateProjectCooperationDetailConsumption(change);
		}
		getSession().commit();
		getSession().close();
	}
}


