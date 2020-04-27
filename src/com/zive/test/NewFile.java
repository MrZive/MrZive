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
import com.zive.pojo.NewSetBuy;
import com.zive.pojo.ShopKPISort;

public class NewFile {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		String beginDate = "2019-08-30 00:00:00";
		String endDate = "2019-08-31 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginConsumeDate", sdf.parse(beginDate));
		map.put("endConsumeDate", sdf.parse(endDate));
		
		//获取门店消耗统计
		List<NewSetBuy> shopConsume = session.selectList("a.b.getNewBuySetOrderDetail",map);
		System.out.println(shopConsume);
	}
}
