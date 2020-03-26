package com.bjsxt.satisfaction;

import java.util.List;
import java.util.Map;

import com.bjsxt.pojo.SatisfactionRatio;
import com.bjsxt.pojo.SatisfactionShopRatio;

public interface ISatisfactionResultDao {

	List<Map<String, Object>> getSatisfactionResultRatio();

	List<SatisfactionShopRatio> getSatisfactionResultShopRatio();
	
	SatisfactionRatio getSatisfactionRatio(Map<String,Object> con);
}
