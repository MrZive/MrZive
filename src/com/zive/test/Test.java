package com.zive.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zive.pojo.People;
import com.zive.pojo.SatisfactionShopRatio;
import com.zive.util.CommonUtil;

public class Test {
	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		People peo =new People();
		peo.setId(1);
//		显示几个
		int pageSize = 2;
//		第几页
		int pageNumber = 1;
//		如果希望传递多个参数,可以使用对象或map
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", pageSize);
		map.put("pageStart", pageSize*(pageNumber-1));
		List<People> p = session.selectList("a.b.page",map);
		System.out.println(p);

		
		List<SatisfactionShopRatio> ratio = session.selectList("a.b.getSatisfactionShopRatio",new HashMap<String,Object>());
		if(ratio!=null&&ratio.size()>0){
			for(SatisfactionShopRatio shop : ratio){
				shop.setRatio(CommonUtil.calculateAccuracy(shop.getGood().longValue(), shop.getCount().longValue(), 2));
			}
		}
		Collections.reverse(ratio);
		for(SatisfactionShopRatio r : ratio){
			System.out.println(r.getRatio());
		}
		
	}
}
