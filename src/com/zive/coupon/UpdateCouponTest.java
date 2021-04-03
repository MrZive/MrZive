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
public class UpdateCouponTest extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\优惠券\\38优惠券.xlsx");
		
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
		
		Map<String,List<String>> map = new LinkedHashMap<>();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String coupon = excelCells.get(0).getValue().toString();
			String activityName = excelCells.get(1).getValue().toString();
			
			if(map.containsKey(activityName)){
				map.get(activityName).add(coupon);
			}else{
				List<String> subList = new ArrayList<>();
				subList.add(coupon);
				map.put(activityName, subList);
			}
		}

		Iterator<Entry<String, List<String>>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Entry<String, List<String>> next = iterator.next();
			System.out.println(next.getKey()+"长度"+next.getValue().size());
			
			String activityName = next.getKey();
			List<String> couponList = next.getValue();
			
			Activity activity = new Activity();
			activity.setName(activityName);
			activity = BaseDao.getActivity(activity).get(0);
			String couponId = getCouponIdByActivityId(activity.getId()).get("id").toString();
			
			List<Map<String,Object>> couponDetailIdByCouponId = getCouponDetailIdByCouponId(couponId);
			for (Map<String, Object> map2 : couponDetailIdByCouponId) {
				
				String coupon = couponList.remove(0);
				
				String subId = map2.get("id").toString();
				Map<String,Object> con = new HashMap<>();
				con.put("id", subId);
				con.put("coupon", coupon);
				con.put("couponId", couponId);
				int updateCouponNo = updateCouponNo(con);
				if(updateCouponNo==0){
					throw new RuntimeException("xcv");
				}
			}
			System.out.println("subList："+couponList.size());
			
		}
		
		getSession().commit();
	}
	
	static public Map<String,Object> getCouponIdByActivityId(String activityId){
		Map<String,Object> map = getSession().selectOne("com.zive.coupon.getCouponIdByActivityId", activityId);
		return map;
	}
	
	static public List<Map<String,Object>> getCouponDetailIdByCouponId(String couponId){
		List<Map<String,Object>> list = getSession().selectList("com.zive.coupon.getCouponDetailIdByCouponId", couponId);
		return list;
	}
	
	static public int updateCouponNo(Map<String,Object> map){
		int update = getSession().update("com.zive.coupon.updateCouponNo", map);
		return update;
	}

}
