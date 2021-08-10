package com.zive.dataOut.java;

import com.zive.dataOut.entity.SetConsumption;

public class SetSellDao extends BaseDao{
	
	static public SetConsumption getSetConsumption(String id,String consumptionId){
		SetConsumption detail = new SetConsumption();
		detail.setId(id);
		detail.setConsumptionId(consumptionId);
		SetConsumption one = getSession().selectOne("com.zive.dataOut.set.getSetConsumption", detail);
		return one;
	}
	
}