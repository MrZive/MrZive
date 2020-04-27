package com.zive.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.util.ArrayUtil;

import com.zive.pojo.Consume;
import com.zive.pojo.ShopKPISort;

public class BiInfoShopTest {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		String beginDate = "2019-09-01 00:00:00";
		String endDate = "2019-09-30 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginConsumeDate", sdf.parse(beginDate));
		map.put("endConsumeDate", sdf.parse(endDate));
		map.put("status", 1);
		map.put("shopIds", null);
		
		//获取门店消耗统计
		List<Consume> shopConsume = session.selectList("com.bjsxt.bi.shop.getShopConsume",map);
		show(shopConsume,map.get("shopIds"));
	}
	
	static public void show(List<Consume> shopConsume,Object shopIdO){
		
//		String[] shopIds = {"110309"};
		String[] shopIds = {"30947a4d-c015-4b4a-831f-75c48596024b"};
		
//		if(shopIdO!=null){
//			String string = shopIdO.toString();
//			shopIds = string.split(",");
////			shopIds = Arrays.asList(string.split(","));
//		}else{
//			Set<String> set = new HashSet<>();
//			for(Consume info : shopConsume){
//				String shopId = info.getShopId();
//				set.add(shopId);
//			}
//			shopIds = set.toArray(new String[0]);
//		}
		
		ShopKPISort shopKPISort = new ShopKPISort();  
		shopKPISort.setCooperationConsumeWenXiu(BigDecimal.ZERO);
		shopKPISort.setConsumeCooperation(BigDecimal.ZERO);
		shopKPISort.setCooperationConsumeNotWenXiuOther(BigDecimal.ZERO);
		shopKPISort.setConsumeProject(BigDecimal.ZERO);
		shopKPISort.setConsumeProduct(BigDecimal.ZERO);
		shopKPISort.setCooperationConsumeNotWenXiu(BigDecimal.ZERO);
		
		
		/*--------------------------------------------消耗相关----------------------------------------------------*/
		if(shopConsume != null)
		for(int i=0;i<shopIds.length;i++){
			String shopId = shopIds[i];
			shopConsume.stream().filter(p -> p.getShopId().equals(shopId)).collect(Collectors.groupingBy( p -> p.getType(),Collectors.toList())).forEach((k,v) -> {
				if(v != null)
					switch (k) {
					case "project":
						//项目消耗金额
						v.forEach(p -> {
							shopKPISort.setConsumeProject(p.getConsumeMoney());
						});
						break;
					case "projectNegative":
						//项目负数冲正金额
						v.forEach(p -> {
							shopKPISort.setConsumeProject(shopKPISort.getConsumeProject().add(p.getConsumeMoney()));
						});
						break;
					case "projectWxNegative":
						//纹绣项目负数冲正金额
						v.forEach(p -> {
							shopKPISort.setCooperationConsumeWenXiu(shopKPISort.getCooperationConsumeWenXiu().add(p.getConsumeMoney()));
							shopKPISort.setConsumeCooperation(shopKPISort.getConsumeCooperation().add(p.getConsumeMoney()));
						});
						break;
					case "product":
						//产品消耗金额
						v.forEach(p -> {
							shopKPISort.setConsumeProduct(p.getConsumeMoney());
						});
						break;
					case "productNegative":
						//产品负数冲正金额
						v.forEach(p -> {
							shopKPISort.setConsumeProduct(shopKPISort.getConsumeProduct().add(p.getConsumeMoney()));
						});
						break;
					case "cooperation"://**##
						//合作消耗金额 18年12月份新增其它合作项目消耗
						v.forEach(p -> {
							//201909后直接判断是否计入消耗
							if((int)p.getData2() == 1){//如果计入消耗
								shopKPISort.setConsumeCooperation(shopKPISort.getConsumeCooperation().add(p.getConsumeMoney()));
								if((int)p.getData() == 1){//是纹绣的消耗
									shopKPISort.setCooperationConsumeWenXiu(shopKPISort.getCooperationConsumeWenXiu().add(p.getConsumeMoney()));
								}else{//是否非纹绣的消耗
									shopKPISort.setCooperationConsumeNotWenXiu(p.getConsumeMoney());
								}
							}else {
								if((int)p.getData() == 1){//纹绣计入消耗
//								shopKPISort.setCooperationConsumeWenXiu(shopKPISort.getCooperationConsumeWenXiu().add(p.getConsumeMoney()));
								}else{//非纹绣非计入消耗的计入其它非纹绣消耗
									shopKPISort.setCooperationConsumeNotWenXiuOther(shopKPISort.getCooperationConsumeNotWenXiuOther().add(p.getConsumeMoney()));
								}
							}
						});
						break;
					default:
						break;
					}
			});
		}
		
		
		System.out.println("标准消耗："+shopKPISort.getConsumeProject());
		System.out.println("产品消耗："+shopKPISort.getConsumeProduct());
		System.out.println("合作消耗："+shopKPISort.getConsumeCooperation());
		System.out.println("纹绣消耗："+shopKPISort.getCooperationConsumeWenXiu());
		System.out.println("合作消耗："+shopKPISort.getCooperationConsumeNotWenXiu());
//		System.out.println(shopKPISort.getCooperationConsumeNotWenXiuOther());
		
	}
}
