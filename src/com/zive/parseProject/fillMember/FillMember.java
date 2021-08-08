package com.zive.parseProject.fillMember;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.dataOut.entity.HistoryConsumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProductSellDao;

public class FillMember extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		HistoryConsumption historyCon = new HistoryConsumption();
		historyCon.setBeginDate("2021-07-23 10:00:00");
		historyCon.setEndDate("2021-07-23 11:00:00");
		historyCon.setKeyword("Lc");
		List<HistoryConsumption> historyList = getHistoryConsumption(historyCon);
		System.out.println(historyList.size());
		
		int index = 0;
		
		for (HistoryConsumption history : historyList) {
			
			index++;
			
			MemberCard memberCard = getMemberCardById(history.getMemberCardId());
			
			HistoryConsumption con = new HistoryConsumption();
			con.setMemberCardId(history.getMemberCardId());
			List<HistoryConsumption> list = getHistoryConsumption(con);
			if(list.size() <= 1){
				System.out.println("没有历史记录："+memberCard.getId()+"手机号："+memberCard.getPhone());
				continue;
			}
			
			HistoryConsumption hi1 = list.get(0);
			HistoryConsumption hi2 = list.get(1);
			
//			System.out.println(memberCard.getId());
			
			if(hi1.getConsumptionId().startsWith("Lc") && hi2.getStoreBefore() != null && hi2.getOweBefore() != null && hi2.getTreatmentAfter() != null){
				MemberCard change = new MemberCard();
				change.setId(memberCard.getId());
				change.setTreatmentBalance(hi2.getTreatmentAfter());
				updateMemberCard(change);
				
				HistoryConsumption parseObject = JSON.parseObject(JSON.toJSONString(hi2),HistoryConsumption.class);
				
				parseObject.setId(UUID.randomUUID().toString());
				parseObject.setIsFail(0);
				parseObject.setCreateDate(new Date());
				parseObject.setConsumptionId("FixLcError"+memberCard.getPhone());
				addHistoryConsumption(parseObject);
			}else{
				System.out.println("已经被改过："+memberCard.getId()+"手机号："+memberCard.getPhone());
			}
		}
		
		System.out.println(index);
		
		getSession().commit();
		getSession().close();
	}
}


