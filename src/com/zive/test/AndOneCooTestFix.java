package com.zive.test;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;
import com.zive.pojo.ConsumptionCooperationProjectDone;
import com.zive.pojo.Earn;
import com.zive.pojo.OneCooDetail;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class AndOneCooTestFix {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession(false);
		Map<String,Object> kxi = new HashMap<>();
		List<Earn> selectList = session.selectList("com.bjsxt.getOnePercentEarn",kxi);
		
		Map<String,Boolean> same = new HashMap<>();
		for (Earn earnBig : selectList) {
			String consumptionId = earnBig.getConsumption_id();
			if(!same.containsKey(consumptionId)){
				
				Map<String,String> map = new HashMap<>();
				map.put("id", earnBig.getConsumption_shop_id());
				String shopNo = session.selectOne("com.bjsxt.getShopNo",map);
				String shopName = session.selectOne("com.bjsxt.getShopName",map);
				
				String doneId = "S"+shopNo+System.currentTimeMillis()+"_addOnePercent";
				
				ConsumptionCooperationProjectDone done = new ConsumptionCooperationProjectDone();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = df.parse("2020-01-01 00:00:00");
				done.setId(doneId);
				done.setDoctor("管理员");
				done.setNurse("管理员");
				done.setDetailId(earnBig.getDetail_id());
				done.setConsumptionId(earnBig.getConsumption_id());
				done.setServiceDate(date);
				done.setServiceNumber(0);
				done.setServiceShopId(earnBig.getConsumption_shop_id());
				done.setServiceCompany(shopName);
				done.setFirstEarn(1D);
				done.setPrice(0D);
				done.setPercent(1D);
				done.setIsCountComsume(1);
				done.setRemark("9月份之前合作项目剩余的一成业绩分配");
				done.setShareLeftEarn(0D);
				done.setCreateDate(date);
				int update = session.update("com.bjsxt.insertDone", done);
				if(update<=0){
					throw new RuntimeException();
				}
				
				same.put(consumptionId,true);
				for (Earn earn : selectList) {
					String consumptionIdSmall = earn.getConsumption_id();
					if(same.containsKey(consumptionIdSmall) && same.get(consumptionIdSmall)){
						
						String servceId = "service_"+earn.getConsumption_id();
						
						Earn earnUpdate = new Earn();
						earnUpdate.setId(earn.getId());
						earnUpdate.setConsumption_id(servceId);
						earnUpdate.setType(18);
						earnUpdate.setConsumption_type_id(doneId);
						earnUpdate.setConsumption_date(date);
						earnUpdate.setCreate_date(date);
						int updateEarn = session.update("com.bjsxt.updateEarn", earnUpdate);
						if(updateEarn<=0){
							throw new RuntimeException();
						}
					}
				}
				same.put(consumptionId,false);
			}
		}
		session.commit();
	}
}
