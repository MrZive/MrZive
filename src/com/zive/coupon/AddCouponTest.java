package com.zive.coupon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.zive.dataOut.entity.Activity;
import com.zive.dataOut.java.BaseDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 *  品牌冬艳需求，修改优惠券码
 * @author Administrator
 *
 */
public class AddCouponTest extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\优惠券\\补优惠券码.xlsx");
		
		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        //第一行为表头
		ExcelRow excelRow = null;
		List<ExcelCell> excelCells = null;
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		List<String> list = new ArrayList<>();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String coupon = excelCells.get(0).getValue().toString();
			String activityName = excelCells.get(1).getValue().toString();
			
			Activity activity = new Activity();
			activity.setName(activityName);
			activity = BaseDao.getActivity(activity).get(0);
			String couponId = UpdateCouponTest.getCouponIdByActivityId(activity.getId()).get("id").toString();
			
			Map<String,Object> con = new HashMap<>();
			con.put("id", UUID.randomUUID().toString());
			con.put("couponId", couponId);
			con.put("coupon", coupon);
			con.put("value", 0);
			con.put("isUse", 0);
			con.put("type", 0);
			con.put("useConsumptionId", "ready");
			con.put("beginTime", "2021-03-08 00:00:00");
			con.put("endTime", "2021-03-31 00:00:00");
			int add = addCoupon(con);
			if(add==0){
				throw new RuntimeException("xcv");
			}
		}
		
		getSession().commit();
	}
	
	static public int addCoupon(Map<String,Object> map){
		int insert = getSession().insert("com.zive.coupon.addCoupon", map);
		return insert;
	}

}
