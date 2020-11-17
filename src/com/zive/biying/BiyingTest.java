package com.zive.biying;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.bi.entity.ItemInfo;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pojo.CustomProductSales;
import com.zive.pojo.CustomProductSalesEmployeeEarn;
import com.zive.pojo.People;
import com.zive.pojo.SatisfactionRatio;
import com.zive.pojo.SatisfactionShopRatio;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class BiyingTest extends BaseDao{
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		String beginDate = "2019-07-01 00:00:00";
		String endDate = "2019-07-31 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		MemberCard memberCard = new MemberCard();
		List<MemberCard> memberCard2 = BaseDao.getMemberCard(memberCard);
		
		
		JSONArray list = new JSONArray();
		
		for (MemberCard member : memberCard2) {
			
			JSONObject info = new JSONObject();
			info.put("itemId",member.getId());
			info.put("itemName",member.getName());
			info.put("itemPhone",member.getPhone());
			
//			if(member.getPassShopId()!=null && member.getPassShopId().trim().length()>0){
//				Shop shopById = BaseDao.getShopById(memberCard.getPassShopId());
//				info.put("itemPassShopName",shopById.getName());
//			}
//			if(member.getNewPassShopId()!=null && member.getNewPassShopId().trim().length()>0){
//				Shop shopById = BaseDao.getShopById(memberCard.getNewPassShopId());
//				info.put("itemNewPassShopName",member.getName());
//			}
			
			List<Map<String, Object>> canDoneMemberProjectNumber = getCanDoneMemberProjectNumber(member.getId());
			
			if(canDoneMemberProjectNumber==null || canDoneMemberProjectNumber.size()==0){
				continue;
			}
			info.put("number", canDoneMemberProjectNumber==null?0:canDoneMemberProjectNumber.size());
			
			list.add(info);
		}
		ExcelUtilForDO.toFile(list, BiYingInfo.class);
	}
	
	
	
	
	public static List<Map<String,Object>> getCanDoneMemberProjectNumber(String memberCardId){
		List<Map<String, Object>> memberProjectNumber = getSession().selectList("com.zive.biying.getCanDoneMemberProjectNumber",memberCardId);
		
		Iterator<Map<String, Object>> iterator = memberProjectNumber.iterator();
		
		while(iterator.hasNext()){
			Map<String, Object> map = iterator.next();
			int isBook = map.get("isBook")==null?0:Integer.valueOf(map.get("isBook").toString());
			double owe = map.get("owe")==null?0d:Double.valueOf(map.get("owe").toString());
			int leftNumber = map.get("leftNumber")==null?0:Integer.valueOf(map.get("leftNumber").toString());
			int buyNumber = map.get("buyNumber")==null?0:Integer.valueOf(map.get("buyNumber").toString());
			int doneNumebr = buyNumber - leftNumber;
			double price = map.get("price")==null?0d:Double.valueOf(map.get("price").toString());
			double realPayment = map.get("realPayment")==null?0d:Double.valueOf(map.get("realPayment").toString());
			
			if(isBook>0 && owe>0){
				double leftDonePrice = realPayment - (doneNumebr * price);
				double number = leftDonePrice/price;
				
				if(number<1){
					iterator.remove();
				}
			}
		}
		
		return memberProjectNumber;
	}

}

@TableName("必盈会员剩余可销耗数据")
class BiYingInfo {
	
	@FieldName("会员id")
	private String itemId;
	
	@FieldName("会员手机号")
	private String itemPhone;
	
	@FieldName("会员名称")
	private String itemName;
	
	@FieldName("旧归属门店")
	private String itemPassName;
	
	@FieldName("新归属门店")
	private String itemNewPassName;
	
	@FieldName("剩余可销耗次数")
	private Integer number;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemPhone() {
		return itemPhone;
	}

	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getItemPassName() {
		return itemPassName;
	}

	public void setItemPassName(String itemPassName) {
		this.itemPassName = itemPassName;
	}

	public String getItemNewPassName() {
		return itemNewPassName;
	}

	public void setItemNewPassName(String itemNewPassName) {
		this.itemNewPassName = itemNewPassName;
	}
}