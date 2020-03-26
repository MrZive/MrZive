package com.bjsxt.test;

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
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.util.HSSFColor;

import com.bjsxt.pojo.CustomProductSales;
import com.bjsxt.pojo.CustomProductSalesEmployeeEarn;
import com.bjsxt.pojo.People;
import com.bjsxt.pojo.SatisfactionRatio;
import com.bjsxt.pojo.SatisfactionShopRatio;
import com.bjsxt.pub.Excel;
import com.bjsxt.pub.ExcelCell;
import com.bjsxt.pub.ExcelRow;
import com.bjsxt.pub.ExcelSheet;
import com.bjsxt.pub.OfficeUtil;

public class SearchProductHengTest {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		String beginDate = "2019-10-01 00:00:00";
		String endDate = "2019-12-31 23:59:59";
		String productName = "好身材";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("beginDate", sdf.parse(beginDate));
		map.put("endDate", sdf.parse(endDate));
		map.put("productName", productName);
		map.put("status", "1");
//		map.put("shopIds", "110105");
		BigDecimal code=new BigDecimal(0);
		
//		List<CustomProductSales> product = session.selectList("com.bjsxt.search.getCustomProductSalesAnalyzes",map);
//		check(product,1);
//		System.out.println(product.size());
//		file(product);
//		for(CustomProductSales cu : product){
//			code=code.add(cu.getCashPay().add(cu.getBankcardPay()));
//		}
		
		List<CustomProductSales> productReturn = session.selectList("com.bjsxt.search.getCustomProductSalesAnalyzesReturn",map);
//		check(productReturn,2);
//		System.out.println(productReturn.size());
//		file(productReturn);
//		for(CustomProductSales cu : productReturn){
//			code=code.add(cu.getCashPay().add(cu.getBankcardPay()));
//		}
		
//		List<CustomProductSales> productRefund = session.selectList("com.bjsxt.search.getCustomProductSalesAnalyzesRefund",map);
//		List<CustomProductSales> productRefund = session.selectList("com.bjsxt.search.getCustomProductSalesAnalyzesRefund2",map);
//		check(productRefund,3);
//		System.out.println(productRefund.size());
//		file(productRefund);
//		for(CustomProductSales cu : productRefund){
//			code=code.add(cu.getCashPay().add(cu.getBankcardPay()));
//		}
		
//		System.out.println(code);
		
//		for(CustomProductSales cu : product){
//			if(cu.getEmployeeEarnList()!=null&&cu.getEmployeeEarnList().size()>0){
//				List<CustomProductSalesEmployeeEarn> earnList = cu.getEmployeeEarnList();
//				for(int i=0;i<earnList.size();i++){
//					if(earnList.get(i).getOrderType().equals("refund")){
//						System.out.println(earnList.get(i).getEmployeeName());
//					}
//				}
//			}
//		}
		
		List<CustomProductSales> all = new ArrayList<>();
//		all.addAll(product);
		all.addAll(productReturn);
//		all.addAll(productRefund);
		Collections.sort(all);
		file(productName,all);
	}
	
	
	private static void check(List<CustomProductSales> product,int type) {
		Map<String,Integer> map = new HashMap<>();
		
		if(type==1){
			for(CustomProductSales s:product){
				if(map.containsKey(s.getConsumptionId())){
					System.out.println("发现重复单:"+s.getConsumptionId());
				}else{
					if(s.getConsumptionId().length()>0){
						map.put(s.getConsumptionId(), 1);
					}
				}
			}
		}else if(type==2){
			for(CustomProductSales s:product){
				if(map.containsKey(s.getReturnConsumptionId())){
					System.out.println("发现重复单:"+s.getReturnConsumptionId());
				}else{
					if(s.getConsumptionId().length()>0){
						map.put(s.getReturnConsumptionId(), 1);
					}
				}
			}
		}else{
			for(CustomProductSales s:product){
				if(map.containsKey(s.getRefundConsumptionId())){
					System.out.println("发现重复单:"+s.getRefundConsumptionId());
				}else{
					if(s.getConsumptionId().length()>0){
						map.put(s.getRefundConsumptionId(), 1);
					}
				}
			}
		}
		
	}
	
	private static int findSizeOfSetEarn(List<CustomProductSales> productBuys){
		int max=0;
		for(CustomProductSales p : productBuys){
			List<CustomProductSalesEmployeeEarn> shoperAndEarn = p.getEmployeeEarnList();
			if(shoperAndEarn!=null&&shoperAndEarn.size()>0){
				int length = shoperAndEarn.size();
				max=length>max?length:max;
			}
		}
		return max;
	}


	public static void file(String fileNameStore,final List<CustomProductSales> customProductSalesAnalyzes) throws IOException{
		final Map<String,String> orderType = new HashMap<String,String>();
		orderType.put("buy", "消费单");
		orderType.put("refund", "退款单");
		orderType.put("return", "还款单");
		
		Excel excel = new Excel();
		if(customProductSalesAnalyzes != null && customProductSalesAnalyzes.size() > 0){
			int earnSize = findSizeOfSetEarn(customProductSalesAnalyzes);
			excel.setSheets(new ArrayList<ExcelSheet>(){{
				add(new ExcelSheet(){{
					setName("销售情况报表");
					setFreezePane(new int[]{0,1,0,1});
					setRows(new ArrayList<ExcelRow>(){{
						add(new ExcelRow(){{
							setBackground(HSSFColor.BLACK.index);
							setColor(HSSFColor.WHITE.index);
							setAutoSizeColumn(false);
							setColunmWidth(new HashMap<Integer, Integer>() {
								{
									put(0, 16*256);//125
									put(1, 10*256);//80像素--1：8
									put(2, 10*256);//80像素--1：8
									put(3, 20*256);//160
									put(4, 20*256);//160
									put(5, 20*256);//160
									put(6, 20*256);//160
									put(7, 8*256);//60
									put(8, 15*256);//120
									put(9, 10*256);//80
									put(10, 10*256);//80
									put(11, 10*256);//80
									put(12, 10*256);//80
									put(13, 10*256);//80
									put(14, 10*256);//80
									put(15, 15*256);//120
									put(16, 11*256);//90
									put(17, 9*256);//70
									put(18, 31*256);//250
									put(19, 9*256);//70
									put(20, 9*256);//70
									put(21, 9*256);//70
									put(22, 9*256);//70
									put(23, 9*256);//70
									put(24, 9*256);//70
									put(25, 9*256);//70
									put(26, 9*256);//70
									put(27, 15*256);//120
									put(28, 11*256);//90
									put(29, 15*256);//120
									put(30, 11*256);//90
									put(31, 11*256);//90
									put(32, 15*256);//120
								}
							});
							setCells(new ArrayList<ExcelCell>(){{
								add(new ExcelCell(){{setValue("消费门店");}});
								add(new ExcelCell(){{setValue("门店地址");}});
								add(new ExcelCell(){{setValue("单类型");}});
								add(new ExcelCell(){{setValue("业务编号");}});
								add(new ExcelCell(){{setValue("单据编号");}});
								add(new ExcelCell(){{setValue("还款单编号");}});
								add(new ExcelCell(){{setValue("退款单编号");}});
								add(new ExcelCell(){{setValue("做单人");}});
//								add(new ExcelCell(){{setValue("做单人编号");}});
								add(new ExcelCell(){{setValue("产品名");}});
								add(new ExcelCell(){{setValue("产品编号");}});
//								add(new ExcelCell(){{setValue("散装单位");}});
//								add(new ExcelCell(){{setValue("套盒单位");}});
								add(new ExcelCell(){{setValue("规格");}});
								add(new ExcelCell(){{setValue("价格");}});
								add(new ExcelCell(){{setValue("体验价");}});
								add(new ExcelCell(){{setValue("促销价");}});
								add(new ExcelCell(){{setValue("市场价");}});
//								add(new ExcelCell(){{setValue("实际单位");}});
//								add(new ExcelCell(){{setValue("是否已支付");}});
								
								/*会员*/
								add(new ExcelCell(){{setValue("会员卡号");}});
								add(new ExcelCell(){{setValue("会员姓名");}});
								add(new ExcelCell(){{setValue("会员性别");}});
								
								/*活动*/
								add(new ExcelCell(){{setValue("活动名称");}});
								add(new ExcelCell(){{setValue("活动状态");}});
								
//								add(new ExcelCell(){{setValue("实际数量");}});
//								add(new ExcelCell(){{setValue("实际价格");}});
//								add(new ExcelCell(){{setValue("实际单位");}});
								add(new ExcelCell(){{setValue("购买数量");}});
								add(new ExcelCell(){{setValue("退款数量");}});
//								add(new ExcelCell(){{setValue("是否提货");}});
//								add(new ExcelCell(){{setValue("已提数量");}});
								add(new ExcelCell(){{setValue("购买价格");}});
								add(new ExcelCell(){{setValue("退款金额");}});
								add(new ExcelCell(){{setValue("储值支付");}});
								add(new ExcelCell(){{setValue("刷卡支付");}});
								add(new ExcelCell(){{setValue("现金支付");}});
								add(new ExcelCell(){{setValue("购买日期");}});
			
								/*业绩*/
////								add(new ExcelCell(){{setValue("单号");}});
////								add(new ExcelCell(){{setValue("单号类型");}});
////								add(new ExcelCell(){{setValue("储值支付");}});
////								add(new ExcelCell(){{setValue("刷卡支付");}});
////								add(new ExcelCell(){{setValue("现金支付");}});
////								add(new ExcelCell(){{setValue("有效业绩");}});
//								add(new ExcelCell(){{setValue("业绩人员");}});
//								add(new ExcelCell(){{setValue("业绩类型");}});
////								add(new ExcelCell(){{setValue("人员编号");}});
//								add(new ExcelCell(){{setValue("业绩");}});
//								add(new ExcelCell(){{setValue("储值业绩");}});
//								add(new ExcelCell(){{setValue("人员手机");}});
								for(int ii=1;ii<=earnSize;ii++){
									int iii=ii;
									add(new ExcelCell() {{setValue("养生顾问"+iii);}});
									add(new ExcelCell() {{setValue("业绩类型"+iii);}});
									add(new ExcelCell() {{setValue("业绩"+iii);}});
									add(new ExcelCell() {{setValue("储值业绩"+iii);}});
									add(new ExcelCell() {{setValue("手机号"+iii);}});
								}
							}});
						}});
						String pattern = "yyyy-MM-dd HH:mm:ss";
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						for(int num=0;num<customProductSalesAnalyzes.size();num++){
							final CustomProductSales sales = customProductSalesAnalyzes.get(num);
							final int colorNum=num;
								add(new ExcelRow(){{
									setCells(new ArrayList<ExcelCell>(){{
										add(new ExcelCell(){{setValue(sales.getShopName());}});
										add(new ExcelCell(){{setValue(sales.getShopRegion());}});
										add(new ExcelCell(){{setValue(orderType.get(sales.getOrderType()));}});
										add(new ExcelCell(){{setValue(sales.getConsumptionId());}});
										add(new ExcelCell(){{setValue(sales.getConsumptionProductId());}});
										add(new ExcelCell(){{setValue(sales.getReturnConsumptionId());}});
										add(new ExcelCell(){{setValue(sales.getRefundConsumptionId());}});
										add(new ExcelCell(){{setValue(sales.getCreator());}});
//										add(new ExcelCell(){{setValue(sales.getCreatorNo());}});
										add(new ExcelCell(){{setValue(sales.getProductName());}});
										add(new ExcelCell(){{setValue(sales.getProductNo());}});
//										add(new ExcelCell(){{setValue(sales.getBulkUnit());}});
//										add(new ExcelCell(){{setValue(sales.getBoxesUnit());}});
										add(new ExcelCell(){{setValue(sales.getStandard());}});
										add(new ExcelCell(){{setValue(sales.getPrice());}});
										add(new ExcelCell(){{setValue(sales.getExperiencePrice());}});
										add(new ExcelCell(){{setValue(sales.getPromotoinPrice());}});
										add(new ExcelCell(){{setValue(sales.getMarketPrice());}});
//										add(new ExcelCell(){{setValue(sales.getLeftUnit());}});
//										add(new ExcelCell(){{setValue(sales.getIsPay()==1?"是":"否");}});
										/*会员信息*/
										add(new ExcelCell(){{setValue(sales.getMemberPhone());}});
										add(new ExcelCell(){{setValue(sales.getMemberName());}});
										add(new ExcelCell(){{setValue(sales.getMemberGender()==1?"男":"女");}});
											
										/*活动*/
										add(new ExcelCell(){{setValue(sales.getActivityName()==null?"":sales.getActivityName());}});
										add(new ExcelCell(){{setValue(sales.getActivityStatus()==null?"":sales.getActivityStatus()==0?"过期":sales.getActivityStatus()==1?"进行中":sales.getActivityStatus()==2?"暂停":"删除");}});
										
//										add(new ExcelCell(){{setValue(sales.getActualNumber());}});
//										add(new ExcelCell(){{setValue(sales.getActualPrice());}});
//										add(new ExcelCell(){{setValue(sales.getActualUnit());}});
										add(new ExcelCell(){{setValue(sales.getBuyNumber());}});
										add(new ExcelCell(){{setValue(sales.getRefundNumber());}});
//										add(new ExcelCell(){{setValue(sales.getBuyNumber().equals(sales.getLeftNumber())?"未提货":"已提货");}});
//										add(new ExcelCell(){{setValue(sales.getBuyNumber()-sales.getLeftNumber());}});
										add(new ExcelCell(){{setValue(sales.getPayment());}});
										add(new ExcelCell(){{setValue(sales.getRefundMoney());}});
										add(new ExcelCell(){{setValue(sales.getStorePay());}});
										add(new ExcelCell(){{setValue(sales.getBankcardPay());}});
										add(new ExcelCell(){{setValue(sales.getCashPay());}});
										add(new ExcelCell(){{setValue(sales.getCreateDate(),"yyyy-MM-dd");}});
										/*养生顾问*/
										List<CustomProductSalesEmployeeEarn> employeeEarnList = sales.getEmployeeEarnList();
										if(employeeEarnList!=null && employeeEarnList.size()>0){
											for(CustomProductSalesEmployeeEarn employee : employeeEarnList){
												final String type = CustomProductSalesEmployeeEarn.getEarnType.getOrDefault(employee.getEarnType(),employee.getEarnType()+"");
												add(new ExcelCell() {{setValue(employee.getEmployeeName());}});
												add(new ExcelCell() {{setValue(type);}});
												add(new ExcelCell() {{setValue(employee.getEarn());}});
												add(new ExcelCell() {{setValue(employee.getStoreEarn());}});
												add(new ExcelCell() {{setValue(employee.getPhone());}});
											}
										}
								}});
							}});
						}
					}});
				}});
			}});
			String fileName = fileNameStore+"销售情况报表"+new Date().getTime()+".xlsx";
			String virPath = "c:/download/" + fileName;
			byte[] bytes = OfficeUtil.createExcel(OfficeUtil.ExcelVersion.Version2007, excel);
			
			//确定写出文件的位置
			File file = new File(virPath);
			//建立输出字节流
			FileOutputStream fos = new FileOutputStream(file);
			//用FileOutputStream 的write方法写入字节数组
			fos.write(bytes);
			System.out.println("写入成功");
			//为了节省IO流的开销，需要关闭
			fos.close();
		}
	}
}
