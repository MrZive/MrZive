package com.zive.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zive.pojo.Consume;
import com.zive.pojo.ShopKPISort;

public class CooTest {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		String beginDate = "2019-08-01 00:00:00";
		String endDate = "2019-08-31 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginConsumeDate", sdf.parse(beginDate));
		map.put("endConsumeDate", sdf.parse(endDate));
		map.put("status", 1);
		map.put("shopIds", null);
		
		//获取门店消耗统计
		List<Consume> shopConsume = session.selectList("com.bjsxt.getShopConsume",map);
		List<Consume> shopConsume2 = session.selectList("com.bjsxt.getShopConsume2",map);
		show(shopConsume);
		show(shopConsume2);
	}
	
	static public void show(List<Consume> shopConsume){
		ShopKPISort shopKPISort = new ShopKPISort();  
		shopKPISort.setCooperationConsumeWenXiu(BigDecimal.ZERO);
		shopKPISort.setConsumeCooperation(BigDecimal.ZERO);
		shopKPISort.setCooperationConsumeNotWenXiuOther(BigDecimal.ZERO);
		
		BigDecimal dian = BigDecimal.ZERO;
		BigDecimal is = BigDecimal.ZERO;
		BigDecimal wen = BigDecimal.ZERO;
		
		for(Consume p : shopConsume){
			dian=dian.add(p.getConsumeMoney());
			is=is.add(new BigDecimal(String.valueOf(p.getData())));
			wen=wen.add(new BigDecimal(String.valueOf(p.getData2())));
			if((int)p.getData() == 1){
				shopKPISort.setCooperationConsumeWenXiu(shopKPISort.getCooperationConsumeWenXiu().add(p.getConsumeMoney()));
				//纹绣加入消耗
				shopKPISort.setConsumeCooperation(shopKPISort.getConsumeCooperation().add(p.getConsumeMoney()));
			}else{
				//非纹绣项目且标记为计入合作消耗的则加入统计
				if((int)p.getData2() == 1){
					shopKPISort.setCooperationConsumeNotWenXiu(p.getConsumeMoney());
					//非纹绣项目且基础资料标记为计入合作消耗的加入消耗统计
					shopKPISort.setConsumeCooperation(shopKPISort.getConsumeCooperation().add(p.getConsumeMoney()));
				}else{
					shopKPISort.setCooperationConsumeNotWenXiuOther(shopKPISort.getCooperationConsumeNotWenXiuOther().add(p.getConsumeMoney()));
				}
			}
//			if((int)p.getData2() == 1){//如果计入消耗
//			shopKPISort.setConsumeCooperation(shopKPISort.getConsumeCooperation().add(p.getConsumeMoney()));
//			if((int)p.getData() == 1){//是纹绣的消耗
//				shopKPISort.setCooperationConsumeWenXiu(shopKPISort.getCooperationConsumeWenXiu().add(p.getConsumeMoney()));
//			}else{//是否非纹绣的消耗
//				shopKPISort.setCooperationConsumeNotWenXiu(p.getConsumeMoney());
//			}
//		}else {
//			if((int)p.getData() == 1){//纹绣计入消耗
//				shopKPISort.setCooperationConsumeWenXiu(shopKPISort.getCooperationConsumeWenXiu().add(p.getConsumeMoney()));
//			}else{//非纹绣非计入消耗的计入其它非纹绣消耗
//				shopKPISort.setCooperationConsumeNotWenXiuOther(shopKPISort.getCooperationConsumeNotWenXiuOther().add(p.getConsumeMoney()));
//			}
//		}
		}
		
		System.out.println("========总金额"+dian);
		System.out.println("========是否是纹绣"+is);
		System.out.println("========是否计入消耗"+wen);
		System.out.println("++++++++"+shopConsume.size());
		
		System.out.println(shopKPISort.getConsumeCooperation());
		System.out.println(shopKPISort.getCooperationConsumeWenXiu());
		System.out.println(shopKPISort.getCooperationConsumeNotWenXiu());
		System.out.println(shopKPISort.getCooperationConsumeNotWenXiuOther());
	}
}
