package com.zive.test;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.zive.pojo.Earn;
import com.zive.pojo.OneCooDetail;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class AndOneCooTest2 {
	public static void main(String[] args) throws IOException, ParseException {
		List<OneCooDetail> list = new ArrayList<>();
		File file = new File("C://22/统计后合作表格2.xlsx");
		
//		String old = "[1,2,3,4,6,40,42,43,44,45,46,48,50,53,54,55,56,57,58,59,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,77,78,81,82,83,85,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,103,106,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,149,150,152,154,156,157,159,160,161,163,168,169,170,171,173,174,176,177,178,180,181,182,183,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,218,219,220,221,222,223,224,225,226,227,228,246,247,248,249,250,251,252,253,260,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,280,281,282,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,320,321,322,323,324,325,327,328,329,330,331,332,333,334,335,336,337,339,341,342,343,344,345,346,347,348,349,350,351,352,354,355,356,357,359,360,361,362,363,364,366,367,368,369,370,371,372,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,400,401,402,403,404,405,406,408,409,410,411,412,413,414,415,416,418,419,420,421,422,423,424,425,427,428,429,430,431,432,435,437,438,439,440,441,443,444,445,446,447,448,449,450,451,452,453,456,458,459,460,461,463,464,465,466,467,468,469,470,471,472,473,475,476,477,479,480,481,483,484,485,486,488,489,490,491,492,493,494,495,496,498,499,500,501,504,505,507,508,509,510,511,512,513,516,518,519,520,521,522,523,524,525,527,528,529,530,531,532,533,534,535,536,538,539,541,542,543,544,545,546,547,548,549,550,551,552,556,557,558,559,560,561,562,564,565,566,567,568,569,570,573,575]";
		String old = "[]";
		String substring = old.substring(1);
		String substring2 = substring.substring(0, substring.length()-1);
		String[] oldSplit = substring2.split(",");
		List<String> oldList = Arrays.asList(oldSplit);
		
		List<Integer> index = new ArrayList<>();
		List<String> index2 = new ArrayList<>();
		Map<String,String> indexMap = new HashMap<>();
		
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession(false);
		
		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        //第一行为表头
		ExcelRow excelRow = null;
		List<ExcelCell> excelCells = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ExcelSheet excelSheet = excel.getSheets().get(0);
		int otherIndex = 0;
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			if(oldList.contains(i+"")){
				continue;
			}
			
			OneCooDetail one = new OneCooDetail();
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			Object value = excelCells.get(0+otherIndex).getValue();
			if(value != null) {
				one.setShopName(value.toString());
			}
			Object value2 = excelCells.get(1+otherIndex).getValue();
			if(value2 != null) {
				String phoneStr = value2.toString();
				one.setMember_card_phone(phoneStr.substring(1));
			}
			Object value3 = excelCells.get(3+otherIndex).getValue();
			if(value3 != null) {
				one.setConsumption_id(value3.toString());
			}
			Object value4 = excelCells.get(4+otherIndex).getValue();
			if(value4 != null) {
				one.setConsumption_date(sdf.parse(value4.toString()));
			}
			Object value5 = excelCells.get(5+otherIndex).getValue();
			if(value5 != null) {
				one.setProjectName(value5.toString());
			}
			Object value6 = excelCells.get(6+otherIndex).getValue();
			if(value6 != null) {
				one.setLeft_earn(Double.valueOf(value6.toString()));
			}
			Object value7 = excelCells.get(7+otherIndex).getValue();
			if(value7 != null) {
				one.setLeft_store_earn(Double.valueOf(value7.toString()));
			}
			Object value9 = excelCells.get(9+otherIndex).getValue();
			if(value9 != null) {
				System.out.println(one.getConsumption_id());
				one.setLeft_one_earn(Double.valueOf(value9.toString()));
			}
			one.setEarnList(new ArrayList<Earn>());
			
			if(one.getConsumption_id().equals("B1211560766481965")){
				System.out.println();
			}
			
			for(int jj=0;jj<5;jj++){
				boolean flag = false;
				boolean flag2 = false;
				boolean flag3 = false;
				Object value10 = excelCells.get(10+jj*3+otherIndex).getValue();
				Earn earn = new Earn();
				if(value10 != null) {
					if(value10.toString().trim().length()>0){
						flag = true;
						earn.setShop_no(value10.toString());
					}
				}
				Object value11 = excelCells.get(11+jj*3+otherIndex).getValue();
				if(value11 != null) {
					if(value11.toString().trim().length()>0){
						flag2 = true;
						earn.setShoper_name(value11.toString());
					}
				}
				Object value12 = excelCells.get(12+jj*3+otherIndex).getValue();
				if(value12 != null) {
					if(value12.toString().trim().length()>0){
						flag3 = true;
						earn.setEarn(Double.valueOf(value12.toString()));
					}
				}
				if(flag && flag2 && flag3){
					List<Earn> earnList = one.getEarnList();
					earnList.add(earn);
					one.setEarnList(earnList);
				}else if(!flag && !flag2 && !flag3){
					
				}else{
					throw new RuntimeException();
				}
			}
			list.add(one);
			
			OneCooDetail coo = checkData(session,one);
			
			if(coo!=null){
				boolean addEarn = addEarn(session,one,coo);
				
				if(addEarn){
					System.out.println(i+"--"+one.getConsumption_id());
					indexMap.put(i+"",one.getConsumption_id());
					index.add(i);
					index2.add(one.getConsumption_id());
				}
			}
		}
		System.out.println(list.size());
		session.commit();
		
		Integer[] hhhh = new Integer[index.size()];
		Integer[] array = index.toArray(hhhh);
		System.out.println(Arrays.toString(array));
		
		String[] hhhh2 = new String[index.size()];
		String[] array2 = index2.toArray(hhhh2);
		System.out.println(Arrays.toString(array2));
		
		Set<Entry<String,String>> entrySet = indexMap.entrySet();
		StringBuilder sb = new StringBuilder("");
		if(entrySet.size()>0){
			for(Entry<String,String> e : entrySet){
				sb.append("{"+e.getKey()+","+e.getValue()+"},");
			}
			System.out.println(sb.substring(0, sb.length()-1));
		}
		System.out.println(index.size());
	}
	




	private static OneCooDetail checkData(SqlSession session, OneCooDetail one) throws ParseException {
		String beginDate = "2018-05-01 00:00:00";
		String endDate = "2019-08-31 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> shopmap = new HashMap<String,Object>();
		shopmap.put("shopName", one.getShopName());
		String consumptionShopId = session.selectOne("com.zive.getShop",shopmap);
		if(consumptionShopId==null || consumptionShopId.length()==0){
			session.rollback();
			throw new RuntimeException();
		}else{
			one.setShopId(consumptionShopId);
		}
		
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginConsumeDate", sdf.parse(beginDate));
		map.put("endConsumeDate", sdf.parse(endDate));
		map.put("consumptionId", one.getConsumption_id());
		map.put("projectName", one.getProjectName());
//		map.put("shopName", one.getShopName());
		
		List<String> list = new ArrayList<>();
		
		//获取门店消耗统计
		List<OneCooDetail> cooProjectList = session.selectList("com.zive.getCooProject",map);
		
		if(cooProjectList.size()!=1){
			return null;
		}
		OneCooDetail coo = cooProjectList.get(0);
		
		Map<String,Object> mem = new HashMap<>();
		mem.put("phone", one.getMember_card_phone());
		
		Map<String,Object> selectOne = session.selectOne("com.zive.getMember",mem);
		if(selectOne==null){
			return null;
		}
		String cardId = selectOne.get("id").toString();
		String cardName = selectOne.get("name").toString();
		if(!cardId.equals(coo.getMember_card_id())){
			return null;
		}
		
//		if(one.getLeft_earn()!=coo.getLeft_earn()){
//			return null;
//		}
//		BigDecimal data1 = new BigDecimal(one.getLeft_store_earn()); 
//		BigDecimal data2 = new BigDecimal(coo.getLeft_store_earn()); 
//		if(data1.compareTo(data2)!=0){
//			return null;
//		}
		if(one.getEarnList().size()==0){
			return null;
		}
//		BigDecimal data11 = new BigDecimal(one.getLeft_one_earn()); 
//		BigDecimal data22 = new BigDecimal(one.getLeft_earn()-one.getLeft_store_earn()); 
//		if(data11.subtract(data22).abs().compareTo(BigDecimal.valueOf(1)) == 1 ){
//			return null;
//		}
		return coo;
	}


	private static boolean addEarn(SqlSession session, OneCooDetail one, OneCooDetail coo){
		
		Double allEarn = 0d;
		System.out.println("##"+one.getEarnList().size()+"##");
		
		for(Earn excelEarn : one.getEarnList()){
			allEarn += excelEarn.getEarn();
//			Map<String,String> map2 = new HashMap<>();
//			map2.put("name", excelEarn.getShoper_name());
//			List<Map<String,Object>> shoperList = session.selectList("com.zive.getEmployee",map2);
//			if(shoperList==null || shoperList.size()==0){
//				return false;
//			}
		}
		BigDecimal vv = new BigDecimal(allEarn);
		BigDecimal ww = new BigDecimal(one.getLeft_one_earn());
		if(vv.subtract(ww).abs().compareTo(BigDecimal.valueOf(1)) == 1 ){
			return false;
		}
		
		for(Earn excelEarn : one.getEarnList()){
			Earn earn = new Earn();
			Map<String,String> map = new HashMap<>();
			if(excelEarn.getShop_no().contains(".")){
				excelEarn.setShop_no(excelEarn.getShop_no().substring(0,excelEarn.getShop_no().indexOf(".")));
			}
			
			if(excelEarn.getShop_no().length()<3){
				excelEarn.setShop_no("0"+excelEarn.getShop_no());
			}
			map.put("no", excelEarn.getShop_no());
			
			String shopId = session.selectOne("com.zive.getShop",map);
			if(shopId==null || shopId.length()==0){
				return false;
			}else{
				earn.setShop_id(shopId);
			}
			
			Map<String,String> map2 = new HashMap<>();
			map2.put("name", excelEarn.getShoper_name());
			List<Map<String,Object>> shoperList = session.selectList("com.zive.getEmployee",map2);
			if(shoperList==null || shoperList.size()==0){
				System.out.println(excelEarn.getShoper_name());
				if(excelEarn.getShoper_name().equals("卢凤娟") || excelEarn.getShoper_name().equals("陈慧珍") || excelEarn.getShoper_name().equals("康小凤") || excelEarn.getShoper_name().equals("欧雨萍") || excelEarn.getShoper_name().equals("吕琼练") || excelEarn.getShoper_name().equals("李明凤") || excelEarn.getShoper_name().equals("林小青") || excelEarn.getShoper_name().equals("陈精慧") || excelEarn.getShoper_name().equals("魏璐") || excelEarn.getShoper_name().equals("洗玉萍") || excelEarn.getShoper_name().equals("林艳春") || excelEarn.getShoper_name().equals("汤佳萍")){
					return false;
				}else{
					throw new RuntimeException();
				}
			}else{
				if(shoperList.size()>1){
					for(Map<String,Object> shoper : shoperList){
						if(shoper.get("no").toString().equals(excelEarn.getShop_no())){
							earn.setShoper_id(shoper.get("id").toString());
							break;
						}
					}
				}else{
					earn.setShoper_id(shoperList.get(0).get("id").toString());
				}
			}
			
			if(earn.getShoper_id()==null || earn.getShoper_id().trim().length()==0){
				return false;
			}
			
			Map<String,Object> kxi = new HashMap<>();
			kxi.put("consumption_type_id", one.getConsumption_id()+"_addOnePercent");
			kxi.put("shoper_id", earn.getShoper_id());
			Integer oldCount = session.selectOne("com.zive.getEarnCount",kxi);
			if(oldCount>0){
				continue;
			}
			
			earn.setEarn(excelEarn.getEarn());
			
			earn.setId(UUID.randomUUID().toString());
//			earn.setBuy_type(buy_type);
			earn.setConsumption_date(coo.getConsumption_date());
			earn.setConsumption_id(coo.getConsumption_id());
			earn.setConsumption_shop_id(one.getShopId());
			earn.setConsumption_type_id(coo.getConsumption_id()+"_addOnePercent");
			earn.setCreate_date(new Date());
			earn.setDetail_id(coo.getId());
//			earn.setType_id(type_id);
			earn.setStore_earn(0D);
			earn.setType(14);
			earn.setIs_fail(0);
			earn.setReturn_product_earn(0D);
			earn.setReturn_project_earn(0D);
			
			if(earn.getConsumption_shop_id()==null || earn.getConsumption_shop_id().trim().length()==0){
				session.rollback();
				throw new RuntimeException();
			}
			
			System.out.println(earn.toString());
			int update = session.update("com.zive.insertEarn", earn);
			if(update==0){
				return false;
			}
		}
		return true;
	}


	private static List<String> getNoList(List<String> excelList, List<String> cooList) {
		List<String> noList = new ArrayList<>();
		for(int i=0;i<excelList.size();i++){
			String excel=excelList.get(i);
			for(int j=0;j<cooList.size();j++){
				String coo = cooList.get(j);
				if(excel.equals(coo)){
					break;
				}
				if(i==cooList.size()-1){
					noList.add(coo);
				}
			}
		}
		return noList;
	}
	
}
