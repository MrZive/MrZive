package com.zive.satisfaction;

import java.util.List;
import java.util.Map;

import com.zive.pojo.SatisfactionRatio;
import com.zive.pojo.SatisfactionShopRatio;

public interface ISatisfactionResultDao {

	List<Map<String, Object>> getSatisfactionResultRatio();

	List<SatisfactionShopRatio> getSatisfactionResultShopRatio();
	
	SatisfactionRatio getSatisfactionRatio(Map<String,Object> con);
}
