package com.zive.parseProject.fillMember;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.dataOut.entity.HistoryConsumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.java.BaseDao;

public class FillMember2 extends BaseDao{

	public static void main(String[] args) throws IOException {
		String path = new FillMember2().getClass().getResource("/com/zive/parseProject/fillMember/changeTest.txt").getPath();
		List<String> readTxtFile = readTxtFile(path);
		
		LinkedHashSet<String> setList = new LinkedHashSet<>(readTxtFile); 
	    List<String> orderList = new ArrayList<>(setList); 

		int index = 0;
		
		for (String order : orderList) {
			String memberCardId = null;
			if(order.contains("已经被改过：")){
				memberCardId = order.substring(6, order.indexOf("手机号"));
			}else{
//				没有历史记录：
				memberCardId = order.substring(7, order.indexOf("手机号"));
				continue;
			}
			
			MemberCard memberCard = getMemberCardById(memberCardId);
			
			HistoryConsumption historyCon = new HistoryConsumption();
			historyCon.setMemberCardId(memberCardId);
			historyCon.setBeginDate("2021-07-23 10:00:00");
			List<HistoryConsumption> historyList = getHistoryConsumption(historyCon);
			
			index++;
			for (int i=0;i<historyList.size();i++) {
				HistoryConsumption history = historyList.get(i);
				
				if(!history.getConsumptionId().startsWith("Lc")){
					continue;
				}
				
				boolean checkNotChange = checkNotChange(historyList, history, i+1);
				
				if(!checkNotChange){
					System.out.println("已经被改过："+memberCard.getId()+"手机号："+memberCard.getPhone());
					break;
				}
				
				BigDecimal num = history.getTreatmentBefore().subtract(history.getTreatmentAfter());
//					double number = num.doubleValue();

//					if(num.compareTo(BigDecimal.ZERO) > 0){
//						number = setDoubleScale(number, 4);
//					}else if(num.compareTo(BigDecimal.ZERO) < 0){
//						number = setDoubleScaleROUND_DOWN(number, 4);
//					}
				
				BigDecimal add = new BigDecimal(memberCard.getTreatmentBalance()).add(num);
				
				double doubleValue = add.doubleValue();
				
				MemberCard change = new MemberCard();
				change.setId(memberCard.getId());
				change.setTreatmentBalance(doubleValue);
				updateMemberCard(change);
				
				
				
				HistoryConsumption last = new HistoryConsumption();
				last.setId(UUID.randomUUID().toString());
				last.setIsFail(0);
				last.setCreateDate(new Date());
				last.setConsumptionId("FixLcError"+memberCard.getPhone());
				last.setMemberCardId(memberCardId);
				
				last.setOweAfter(new BigDecimal(memberCard.getOweBalance()));
				last.setOweBefore(new BigDecimal(memberCard.getOweBalance()));
				last.setPointAfter(new BigDecimal(memberCard.getPointBalance()));
				last.setPointBefore(new BigDecimal(memberCard.getPointBalance()));
				last.setStockAfter(new BigDecimal(memberCard.getStockBalance()));
				last.setStockBefore(new BigDecimal(memberCard.getStockBalance()));
				last.setStoreAfter(new BigDecimal(memberCard.getStoreBalance()));
				last.setStoreBefore(new BigDecimal(memberCard.getStoreBalance()));
				last.setTreatmentAfter(new BigDecimal(change.getTreatmentBalance()));
				last.setTreatmentBefore(new BigDecimal(memberCard.getTreatmentBalance()));
				last.setWenxiuAfter(new BigDecimal(memberCard.getWenxiuBalance()));
				last.setWenxiuBefore(new BigDecimal(memberCard.getWenxiuBalance()));
				
				addHistoryConsumption(last);
				break;
			}
		}
		
//		System.out.println(index);
		getSession().commit();
		getSession().close();
	}
	
	
	static boolean checkNotChange(List<HistoryConsumption> list,HistoryConsumption history,int i){
		if(i >= list.size()){
			return true;
		}
		HistoryConsumption next = list.get(i);
		if(next.getConsumptionId().startsWith("FixLcError")){
			System.out.println("已经修复："+next.getMemberCardId());
			return false;
		}
//		double bigDecimal = setDoubleScale(history.getTreatmentAfter());
//		double bigDecima2 = setDoubleScale(next.getTreatmentBefore());
		
		if(history.getTreatmentAfter().compareTo(next.getTreatmentBefore()) == 0){
			return checkNotChange(list, next, i + 1);
		}
		return false;
	}
}


