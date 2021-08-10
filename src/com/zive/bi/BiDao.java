package com.zive.bi;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.apt.model.IElementInfo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.bi.entity.CooperationBuy;
import com.zive.bi.entity.ItemInfo;
import com.zive.bi.entity.NewSetBuy;
import com.zive.bi.entity.ProductBuy;
import com.zive.bi.entity.ProjectBuy;
import com.zive.bi.entity.RefundOrderDetail;
import com.zive.bi.entity.ReturnOrderDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pojo.EmployeeEarn;
import com.zive.util.CommonUtil;

/**
 * 导出bi所有项目收款
 * @author Administrator
 *
 */
public class BiDao extends BaseDao{
	
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws ParseException, IOException {
		String beginDateStr = "2021-07-12 00:00:00";
		String endDateStr = "2021-07-31 23:59:59";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = format.parse(beginDateStr);
        Date endDate = format.parse(endDateStr);
        
        boolean isDay = false;
        
        
		String shopId = null;
		JSONArray itemInfo = new JSONArray();
		
//		List<EmployeeEarn> employeeEarnList = getEmployeeEarnList(earnStructureId, employeeStructureId, beginDate, endDate);
//		System.out.println(employeeEarnList.size());
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String initYear = sf.format(beginDate);
		
		//获取考核的门店
		List<String> monthOfTargetList = getMonthOfTarget(initYear);
		System.out.println(monthOfTargetList.size());
		
		Set<String> monthOfTarget = new HashSet<String>();
		monthOfTarget.addAll(Arrays.asList("110173"));
//		for (String string : monthOfTargetList) {
//			monthOfTarget.add(string);
//		}
		System.out.println(monthOfTarget);
		
		//项目收款
		List<ProjectBuy> buyProjectOrderDetail = getBuyProjectOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoProject = getItemInfoForProject(buyProjectOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoProject);
		
		//产品收款
		List<ProductBuy> buyProductOrderDetail = getBuyProductOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoProduct = getItemInfoForProduct(buyProductOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoProduct);
		
		//套餐收款
		List<NewSetBuy> buySetOrderDetail = getNewBuySetOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoSet = getItemInfoForSet(buySetOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoSet);
		
		//合作收款
		List<CooperationBuy> buyCooOrderDetail = getBuyCooperationOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoCoo = getItemInfoForCoo(buyCooOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoCoo);
		
		//退款[准确]
		List<RefundOrderDetail> buyRefundOrderDetail = getRefundOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoRefund = getItemInfoForRefund(buyRefundOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoRefund);
		
		//还款
		List<ReturnOrderDetail> buyRetrunOrderDetail = getBuyReturnOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoReturn = getItemInfoForReturn(buyRetrunOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoReturn);
		
		//新还款
		List<ReturnOrderDetail> buyNewRetrunOrderDetail = getBuyNewReturnOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoNewReturn = getItemInfoForNewReturn(buyNewRetrunOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoNewReturn);
		
		//纹绣项目收款
		List<ProjectBuy> buyProjectWxOrderDetail = getBuyWenXiuProjectOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoWxProject = getItemInfoForWxProject(buyProjectWxOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoWxProject);
		
		//纹绣还款
		List<ReturnOrderDetail> buyRetrunWxOrderDetail = getWenxiuResturnOrderDetail(shopId, beginDate, endDate);
		JSONArray itemInfoWxReturn = getItemInfoForWxReturn(buyRetrunWxOrderDetail,monthOfTarget,isDay);
		itemInfo.addAll(itemInfoWxReturn);
		
		//输出详情
		ExcelUtilForDO.toFile(itemInfo, ItemInfo.class);
		//合并门店
		itemInfo = passSameShopData(itemInfo,isDay);
		ExcelUtilForDO.toFile(itemInfo, ItemInfo.class);
		
		//去除重复项目后累加
		itemInfo = passSameData(itemInfo,isDay);
		ExcelUtilForDO.toFile(itemInfo, ItemInfo.class);
		
		if(isDay){
			JSONArray dayList = passDayData(itemInfo);
			ExcelUtilForDO.toFile(dayList, ItemInfo.class);
		}
	}
	
	private static JSONArray passDayData(JSONArray itemList) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < itemList.size(); i++) {
			JSONObject itemInfo = itemList.getJSONObject(i);
			String shopId = itemInfo.getString("shopId");
			String bigDate = itemInfo.getString("consumeDate");
			BigDecimal price = itemInfo.getBigDecimal("price");
			String key = shopId+bigDate;
			boolean flag = false;
			for (int j = 0; j < jsonArray.size(); j++) {
				JSONObject jsonObject = jsonArray.getJSONObject(j);
				String smallDate = jsonObject.getString("consumeDate");
				String smallShopId = jsonObject.getString("shopId");
				String smallKey = smallShopId+smallDate;
				if(key.equals(smallKey)){
					jsonObject.put("price", jsonObject.getBigDecimal("price").add(price));
					flag = true;
					break;
				}
			}
			if(!flag){
				itemInfo.remove("itemId");
				itemInfo.remove("itemName");
				jsonArray.add(itemInfo);
			}
		}
		return jsonArray;
	}

	private static JSONArray passSameShopData(JSONArray itemInfo, boolean isDay) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonShopMap = new JSONObject();
		for (int i = 0; i < itemInfo.size(); i++) {
			JSONObject object = itemInfo.getJSONObject(i);
			
			String shopId = object.getString("shopId");
			String string = object.getString("itemName");
			BigDecimal stringPrice = object.getBigDecimal("price");
			String key = isDay?shopId+object.getString("consumeDate"):shopId;
			
			if(jsonShopMap.containsKey(key)){
				JSONArray jsonsmallList = jsonShopMap.getJSONArray(key);
				
				boolean flagsmall = false;
				for (int j = 0; j < jsonsmallList.size(); j++) {
					JSONObject jsonObject = jsonsmallList.getJSONObject(j);
					Shop shopById = getShopById(shopId);
					object.put("shopName", shopById.getName());
					
					if(jsonObject.getString("itemName").equals(string)){
						BigDecimal bigDecimal = jsonObject.getBigDecimal("price");
						jsonObject.put("price", bigDecimal.add(stringPrice));
						
						flagsmall = true;
						break;
					}
				}
				
				if(!flagsmall){
					jsonsmallList.add(object);
				}
			}else{
				JSONArray jsonShopMapList = new JSONArray();
				Shop shopById = getShopById(shopId);
				object.put("shopName", shopById.getName());
				jsonShopMapList.add(object);
				jsonShopMap.put(key, jsonShopMapList);
			}
		}
		
		//把所有门店的数据加入到新的list
		Iterator<Entry<String, Object>> iterator = jsonShopMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			JSONArray value =  (JSONArray)next.getValue();
			jsonArray.addAll(value);
		}
		return jsonArray;
	}

	private static JSONArray passSameData(JSONArray itemInfo,boolean isDay) {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < itemInfo.size(); i++) {
			JSONObject object = itemInfo.getJSONObject(i);
			String string = object.getString("shopId")==null?"":object.getString("shopId");
			String string2 = object.getString("itemId")==null?"":object.getString("itemId");
			BigDecimal stringPrice = object.getBigDecimal("price");
			String string3 = isDay?object.getString("consumeDate"):"";
			boolean flag = false;
			for (int j = 0; j < jsonArray.size(); j++) {
				JSONObject jsonObject = jsonArray.getJSONObject(j);
				if(jsonObject.getString("shopId").equals(string) && jsonObject.getString("itemId").equals(string2) && jsonObject.getString("consumeDate").equals(string3)){
					BigDecimal bigDecimal = jsonObject.getBigDecimal("price");
					jsonObject.put("price", bigDecimal.add(stringPrice));
					flag = true;
					break;
				}
			}
			if(!flag){
				object.put("type", "合并项目");
				object.put("itemId", string2);
				object.put("itemName", string);
				jsonArray.add(object);
			}
		}
		return jsonArray;
	}

	static public List<String> getMonthOfTarget(String month){
		List<String> monthOfShop = getSession().selectList("com.zive.bi.getMonthOfTarget",month);
		return monthOfShop;
	}
	
	/**
	 * [导出项目收款]
	 */
	static public List<ProjectBuy> getBuyProjectOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginConsumeDate", beginDate);
			put("endConsumeDate", endDate);
			put("status", 1);
			put("shopId", shopId);
		}};
		
		List<ProjectBuy> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getBuyProjectOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (ProjectBuy projectBuy : buyProjectOrderDetail) {
			realPay += projectBuy.getRealPay();
			point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForProject(List<ProjectBuy> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (ProjectBuy projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getProjectId();
			String itemName = projectBuy.getProjectName();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getConsumeDate()):"";
			
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("consumeDate", consumeDate);
				object.put("price", realPay);
				object.put("type", "项目收款");
				json.add(object);
			}
		}
		return json;
	}

	/**
	 * [导出产品收款]
	 */
	static public List<ProductBuy> getBuyProductOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginConsumeDate", beginDate);
			put("endConsumeDate", endDate);
			put("status", 1);
			put("shopId", shopId);
		}};
		
		List<ProductBuy> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getBuyProductOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (ProductBuy projectBuy : buyProjectOrderDetail) {
			realPay += projectBuy.getRealPay();
			point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForProduct(List<ProductBuy> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (ProductBuy projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getProductId();
			String itemName = projectBuy.getProductName();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getConsumeDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("price", realPay);
				object.put("consumeDate", consumeDate);
				object.put("type", "产品收款");
				json.add(object);
			}
		}
		return json;
	}
	
	/**
	 * [导出套餐收款]
	 */
	static public List<NewSetBuy> getNewBuySetOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginConsumeDate", beginDate);
			put("endConsumeDate", endDate);
			put("status", 1);
			put("shopId", shopId);
		}};
		
		List<NewSetBuy> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getNewBuySetOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (NewSetBuy projectBuy : buyProjectOrderDetail) {
			cash += projectBuy.getCashPay();
			pos += projectBuy.getBankcardPay();
			store += projectBuy.getStorePay();
		}
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForSet(List<NewSetBuy> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (NewSetBuy projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getItemId();
			String itemName = projectBuy.getItemName();
			String itemType = projectBuy.getType();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCashPay()).add(BigDecimal.valueOf(projectBuy.getBankcardPay()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getConsumptionDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("price", realPay);
				object.put("consumeDate", consumeDate);
				object.put("type", "套餐收款-"+itemType);
				json.add(object);
			}
		}
		return json;
	}

	/**
	 * [导出合作收款]
	 */
	static public List<CooperationBuy> getBuyCooperationOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginConsumeDate", beginDate);
			put("endConsumeDate", endDate);
			put("status", 1);
			put("shopId", shopId);
		}};
		
		List<CooperationBuy> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getBuyCooperationOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (CooperationBuy projectBuy : buyProjectOrderDetail) {
			realPay += projectBuy.getRealPay();
//				point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForCoo(List<CooperationBuy> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (CooperationBuy projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getCooperationId();
			String itemName = projectBuy.getCooperationName();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getConsumeDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string2 = object.getString("shopId");
				String string = object.getString("itemName");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("price", realPay);
				object.put("consumeDate", consumeDate);
				object.put("type", "合作收款");
				json.add(object);
			}
		}
		return json;
	}

	/**
	 * [导出退款]
	 */
	static public List<RefundOrderDetail> getRefundOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginCheckDate", beginDate);
			put("endCheckDate", endDate);
			put("status", 1);
			put("shopId", shopId);
		}};
		
		List<RefundOrderDetail> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getRefundOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double TotalMoney = 0d;
		Double ReturnTransferMoney = 0d;
		Double ReturnStoreBalance = 0d;
		Double ReturnPayMoney = 0d;
		Double ReturnMoneyStore = 0d;
		Double ReturnMoney = 0d;
		for (RefundOrderDetail projectBuy : buyProjectOrderDetail) {
			TotalMoney += projectBuy.getTotalMoney();
			ReturnTransferMoney += projectBuy.getReturnTransferMoney();
			ReturnStoreBalance += projectBuy.getReturnStoreBalance();
			ReturnPayMoney += projectBuy.getReturnPayMoney();
			ReturnMoneyStore += projectBuy.getReturnMoneyStore();
			ReturnMoney += projectBuy.getReturnMoney();
		}
		System.out.println("TotalMoney:"+TotalMoney);
		System.out.println("ReturnTransferMoney:"+ReturnTransferMoney);
		System.out.println("ReturnStoreBalance:"+ReturnStoreBalance);
		System.out.println("ReturnPayMoney:"+ReturnPayMoney);
		System.out.println("退掉储值ReturnMoneyStore:"+ReturnMoneyStore);
		System.out.println("项目/产品价值金额ReturnMoney:"+ReturnMoney);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForRefund(List<RefundOrderDetail> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (RefundOrderDetail projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			if(projectBuy.getCheckStatus()!=1){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getProId();
			String itemName = projectBuy.getProName();
			String type = projectBuy.getProType();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getReturnMoney()).negate();
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getCheckDate()):"";
			if (projectBuy.getIsReurnBank() == 0)  {
				type += "退至储值";
				continue;
			} else {
				type += "退至客户";
			}
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String stringType = object.getString("type");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && stringType.equals(projectBuy.getProType()) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("consumeDate", consumeDate);
				object.put("type", type);
				object.put("price", realPay);
				json.add(object);
			}
		}
		return json;
	}
	/**
	 * [导出还款]
	 */
	static public List<ReturnOrderDetail> getBuyReturnOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginReturnDate", beginDate);
			put("endReturnDate", endDate);
			put("isFail", 0);
			put("shopId", shopId);
		}};
		
		List<ReturnOrderDetail> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getResturnOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (ReturnOrderDetail projectBuy : buyProjectOrderDetail) {
//			realPay += projectBuy.getRealPay();
//			point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForReturn(List<ReturnOrderDetail> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (ReturnOrderDetail projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getItemId();
			String itemName = projectBuy.getItemName();
			String type = projectBuy.getType();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getReturnDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String stringType = object.getString("type");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && stringType.equals(type) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId==null?"旧未知id":itemId);
				object.put("itemName", itemName==null?"旧未知项目还款":itemName);
				object.put("consumeDate", consumeDate);
				object.put("type", type);
				object.put("price", realPay);
				json.add(object);
			}
		}
		return json;
	}

	/**
	 * [导出新还款]
	 */
	static public List<ReturnOrderDetail> getBuyNewReturnOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginReturnDate", beginDate);
			put("endReturnDate", endDate);
			put("isFail", 0);
			put("shopId", shopId);
		}};
		
		List<ReturnOrderDetail> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getNewResturnOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (ReturnOrderDetail projectBuy : buyProjectOrderDetail) {
//			realPay += projectBuy.getRealPay();
//			point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForNewReturn(List<ReturnOrderDetail> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (ReturnOrderDetail projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getItemId();
			String itemName = projectBuy.getItemName();
			String type = projectBuy.getType();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getReturnDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String stringType = object.getString("type");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && stringType.equals(type) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId==null?"新未知id":itemId);
				object.put("itemName", itemName==null?"新未知项目还款":itemName);
				object.put("type", type);
				object.put("consumeDate", consumeDate);
				object.put("price", realPay);
				json.add(object);
			}
		}
		return json;
	}

	/**
	 * [导出纹绣项目收款]
	 */
	static public List<ProjectBuy> getBuyWenXiuProjectOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginConsumeDate", beginDate);
			put("endConsumeDate", endDate);
			put("status", 1);
			put("shopId", shopId);
		}};
		
		List<ProjectBuy> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getBuyWenXiuProjectOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (ProjectBuy projectBuy : buyProjectOrderDetail) {
			realPay += projectBuy.getRealPay();
			point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetail;
	}
	static JSONArray getItemInfoForWxProject(List<ProjectBuy> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (ProjectBuy projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getProjectId();
			String itemName = projectBuy.getProjectName();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getConsumeDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("consumeDate", consumeDate);
				object.put("price", realPay);
				object.put("type", "纹绣项目收款");
				json.add(object);
			}
		}
		return json;
	}

	/**
	 * [导出还款]
	 */
	static public List<ReturnOrderDetail> getWenxiuResturnOrderDetail(String shopId,Date beginDate,Date endDate) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(){{
			put("beginReturnDate", beginDate);
			put("endReturnDate", endDate);
			put("isFail", 0);
			put("shopId", shopId);
		}};
		
		List<ReturnOrderDetail> buyProjectOrderDetail = getSession().selectList("com.zive.bi.getWenxiuResturnOrderDetail",map);
		System.out.println(buyProjectOrderDetail.size());
		
		List<ReturnOrderDetail> buyProjectOrderDetailList = new ArrayList<>();
		
		buyProjectOrderDetail.stream().collect(Collectors.groupingBy(p -> p.getReturnOrderId(), Collectors.toList())).forEach((orderId, list) -> {
			for (int i = 0, size = list.size(); i < size; i++) {
				ReturnOrderDetail returnOrderDetail = list.get(i);
				int index = i;
				
					double cash = returnOrderDetail.getCash();
					double pos = returnOrderDetail.getPos();
					double store = returnOrderDetail.getStore();
					returnOrderDetail.setCash(cash==0?0:cash/size);
					returnOrderDetail.setPos(pos==0?0:pos/size);
					returnOrderDetail.setStore(store==0?0:store/size);
					
				buyProjectOrderDetailList.add(returnOrderDetail);
			};
		});
		
		
		Double realPay = 0d;
		Double point = 0d;
		Double cash = 0d;
		Double pos = 0d;
		Double store = 0d;
		for (ReturnOrderDetail projectBuy : buyProjectOrderDetailList) {
//			realPay += projectBuy.getRealPay();
//			point += projectBuy.getPoint();
			cash += projectBuy.getCash();
			pos += projectBuy.getPos();
			store += projectBuy.getStore();
		}
		System.out.println("realPay:"+realPay);
		System.out.println("point:"+point);
		System.out.println("cash:"+cash);
		System.out.println("pos:"+pos);
		System.out.println("store:"+store);
		return buyProjectOrderDetailList;
	}
	static JSONArray getItemInfoForWxReturn(List<ReturnOrderDetail> buyProjectOrderDetail, Set<String> monthOfTarget, boolean isDay){
		JSONArray json = new JSONArray();
		for (ReturnOrderDetail projectBuy : buyProjectOrderDetail) {
			if(monthOfTarget.size()>0 && !monthOfTarget.contains(projectBuy.getShopId())){
				continue;
			}
			
			String shopId = projectBuy.getShopId();
			String itemId = projectBuy.getItemId();
			String itemName = projectBuy.getItemName();
			String type = projectBuy.getType();
			BigDecimal realPay = BigDecimal.valueOf(projectBuy.getCash()).add(BigDecimal.valueOf(projectBuy.getPos()));
			String consumeDate = isDay?CommonUtil.dateToShortDateStr(projectBuy.getReturnDate()):"";
			boolean flag = false;
			for (int i=0;i<json.size();i++) {
				JSONObject object = json.getJSONObject(i);
				String string = object.getString("itemName");
				String stringType = object.getString("type");
				String string2 = object.getString("shopId");
				String string3 = isDay?object.getString("consumeDate"):"";
				
				if(string2.equals(shopId) && string.equals(itemName) && stringType.equals(type) && string3.equals(consumeDate)){
					object.put("price", object.getBigDecimal("price").add(realPay));
					flag = true;
					break;
				}
			}
			if(!flag){
				JSONObject object = new JSONObject();
				object.put("shopId", shopId);
				object.put("itemId", itemId);
				object.put("itemName", itemName);
				object.put("type", type);
				object.put("consumeDate", consumeDate);
				object.put("price", realPay);
				json.add(object);
			}
		}
		return json;
	}

}
