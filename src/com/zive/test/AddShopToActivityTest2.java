package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zive.dataOut.entity.Activity;
import com.zive.dataOut.entity.ActivityShop;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 修改项目手工工时
 * @author Administrator
 *
 */
public class AddShopToActivityTest2 extends BaseDao{

	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		List<String> list = session.selectList("com.zive.common.getShopIdFromLeader","曾丽花");
		
		Activity activity = new Activity();
		activity.setName("轻龄康养-微净化卡");
		Activity info = getActivity(activity).get(0);
		
		for (String shopId : list) {
			ActivityShop activityShop = new ActivityShop();
			activityShop.setId(UUID.randomUUID().toString());
			activityShop.setActivityId(info.getId());
			activityShop.setShopId(shopId);
			addActivityShop(activityShop);
		}
		
		getSession().commit();
		session.close();
	}
	
	
}